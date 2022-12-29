package ru.deadcryd.personservice.repository;

import java.util.List;
import java.util.Optional;
import ru.deadcryd.personservice.entity.Person;

public interface PersonRepository {

    Optional<Person> findById(Long id);
    List<Person> findAll();
    Optional<Person> save(Person person);
    void deleteById(Long id);

    void update(Person person);
}
