package ru.deadcryd.person;

import io.micronaut.runtime.Micronaut;

public class PersonServiceRunner {

    public static void main(String[] args) {
        Micronaut.run(PersonServiceRunner.class, args);
    }
}
