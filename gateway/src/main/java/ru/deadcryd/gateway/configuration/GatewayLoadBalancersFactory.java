package ru.deadcryd.gateway.configuration;

import io.micronaut.context.annotation.Factory;
import io.micronaut.http.client.LoadBalancer;
import io.micronaut.http.client.loadbalance.DiscoveryClientLoadBalancerFactory;
import jakarta.inject.Singleton;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import ru.deadcryd.gateway.configuration.properties.GatewayProperties;

@Factory
public class GatewayLoadBalancersFactory {

    @Singleton
    public Map<String, LoadBalancer> serviceLoadBalancers(GatewayProperties properties,
                                                          DiscoveryClientLoadBalancerFactory factory) {
        Set<String> services = properties.getServices();
        Map<String, LoadBalancer> loadBalancers = new HashMap<>();

        services.forEach(serviceName -> loadBalancers.put(serviceName, factory.create(serviceName)));
        return Collections.unmodifiableMap(loadBalancers);
    }
}
