package ru.deadcryd.personservice.repository;

import io.micronaut.transaction.annotation.TransactionalAdvice;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import ru.deadcryd.personservice.entity.Person;

@Singleton
@TransactionalAdvice
public class PersonRepositoryImpl implements PersonRepository {

    private final EntityManager entityManager;

    public PersonRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Override
    public Optional<Person> save(Person person) {
        Long id = person.getId();
        if (id == null || entityManager.find(Person.class, id) == null) {
            entityManager.persist(person);
        }
        return Optional.of(person);
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @Override
    public void update(Person person) {
        Long id = person.getId();
        if (id != null && entityManager.find(Person.class, id) != null) {
            entityManager.merge(person);
        }
    }
}
