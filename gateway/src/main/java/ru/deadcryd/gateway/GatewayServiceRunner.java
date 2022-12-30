package ru.deadcryd.gateway;

import io.micronaut.runtime.Micronaut;

public class GatewayServiceRunner {

    public static void main(String[] args) {
        Micronaut.run(GatewayServiceRunner.class, args);
    }
}