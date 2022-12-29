package ru.deadcryd.personservice.service;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import ru.deadcryd.personservice.dto.PersonDto;
import ru.deadcryd.personservice.entity.Person;
import ru.deadcryd.personservice.entity.base.AbstractBaseEntity;
import ru.deadcryd.personservice.mapper.PersonMapper;
import ru.deadcryd.personservice.repository.PersonRepository;

@Singleton
@TransactionalAdvice
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public Optional<Long> createPerson(PersonDto personDto) {
        return personRepository.save(personMapper.personDtoToPerson(personDto)).map(AbstractBaseEntity::getId);
    }

    public void editPerson(Long id, PersonDto personDto) {
        Person person = personMapper.personDtoToPerson(personDto);

        personRepository.findById(id)
            .ifPresent(oldPerson -> {
                person.setId(oldPerson.getId());
                personRepository.update(person);
            });
    }

    public List<PersonDto> findAllPerson(@Nullable String region) {
        Stream<PersonDto> persons = personRepository.findAll()
            .stream()
            .map(personMapper::personToPersonDto);

        if (region != null) {
            persons = persons.filter(person -> person.getAddresses()
                .stream()
                .anyMatch(address -> Objects.equals(address.getCity(), region))
            );
        }

        return persons.toList();
    }

    public Optional<PersonDto> findPerson(Long id) {
        return personRepository.findById(id).map(personMapper::personToPersonDto);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
