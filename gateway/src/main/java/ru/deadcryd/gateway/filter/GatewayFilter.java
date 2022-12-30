package ru.deadcryd.gateway.filter;

import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.discovery.ServiceInstance;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.client.LoadBalancer;
import io.micronaut.http.client.ProxyHttpClient;
import io.micronaut.http.filter.OncePerRequestHttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Named;
import java.util.Map;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Filter("/**")
public class GatewayFilter extends OncePerRequestHttpServerFilter {

    private static final Logger LOG = LoggerFactory.getLogger(GatewayFilter.class);
    @Named
    private final Map<String, LoadBalancer> loadBalancers;
    private final ProxyHttpClient proxyHttpClient;

    public GatewayFilter(Map<String, LoadBalancer> loadBalancers, ProxyHttpClient proxyHttpClient) {
        this.loadBalancers = loadBalancers;
        this.proxyHttpClient = proxyHttpClient;
    }

    @Override
    protected Publisher<MutableHttpResponse<?>> doFilterOnce(HttpRequest<?> request, ServerFilterChain chain) {
        String serviceName = request.getPath().replaceAll("^/([^/]+).*$", "$1");

        if (loadBalancers.containsKey(serviceName)) {

            LoadBalancer loadBalancer = loadBalancers.get(serviceName);
            return Flowable.fromPublisher(loadBalancer.select())
                .flatMap(serviceInstance -> {
                    MutableHttpRequest<?> finalRequest = prepareRequestForTarget(request, serviceInstance);

                    LOG.info("Proxying {} to service {} ({}:{}) as {}",
                             request.getPath(), serviceInstance.getId(), serviceInstance.getHost(),
                             serviceInstance.getPort(), finalRequest.getPath());

                    return proxyHttpClient.proxy(finalRequest);
                });
        }

        return Publishers.just(HttpResponse.notFound());
    }

    private MutableHttpRequest<?> prepareRequestForTarget(HttpRequest<?> request, ServiceInstance serviceInstance) {
        return request.mutate()
            .uri(uriBuilder -> uriBuilder
                .scheme("http")
                .host(serviceInstance.getHost())
                .port(serviceInstance.getPort())
                .replacePath(request.getPath().replace("/" + serviceInstance.getId(), ""))
            );
    }
}
