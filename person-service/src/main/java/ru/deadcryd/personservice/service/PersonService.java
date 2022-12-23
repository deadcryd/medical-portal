package ru.deadcryd.personservice.service;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;
import ru.deadcryd.personservice.dto.PersonDto;
import ru.deadcryd.personservice.entity.Person;
import ru.deadcryd.personservice.entity.base.AbstractBaseEntity;
import ru.deadcryd.personservice.mapper.PersonMapper;
import ru.deadcryd.personservice.repository.PersonRepository;

@Singleton
public class PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper;

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
                personRepository.save(person);
            });
    }

    public List<PersonDto> findAllPerson() {
        return personRepository.findAll()
            .stream()
            .map(personMapper::personToPersonDto)
            .toList();
    }

    public Optional<PersonDto> findPerson(Long id) {
        return personRepository.findById(id).map(personMapper::personToPersonDto);
    }
}
