package ru.deadcryd.personservice.mapper;

import jakarta.inject.Inject;
import java.util.List;
import java.util.function.Function;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.deadcryd.personservice.dto.PersonDto;
import ru.deadcryd.personservice.entity.Person;

@Mapper(componentModel = "jakarta")
public abstract class PersonMapper {

    @Inject
    protected ContactMapper contactMapper;
    @Inject
    protected DocumentMapper documentMapper;
    @Inject
    protected AddressMapper addressMapper;

    @Mapping(target = "contacts",
        expression = "java(mapList(personDto.getContacts(), contactMapper::contactDtoToContact))")
    @Mapping(target = "documents",
        expression = "java(mapList(personDto.getDocuments(), documentMapper::documentDtoToDocument))")
    @Mapping(target = "addresses",
        expression = "java(mapList(personDto.getAddresses(), addressMapper::addressDtoToAddress))")
    public abstract Person personDtoToPerson(PersonDto personDto);


    @Mapping(target = "contacts",
        expression = "java(mapList(person.getContacts(), contactMapper::contactToContactDto))")
    @Mapping(target = "documents",
        expression = "java(mapList(person.getDocuments(), documentMapper::documentToDocumentDto))")
    @Mapping(target = "addresses",
        expression = "java(mapList(person.getAddresses(), addressMapper::addressToAddressDto))")
    public abstract PersonDto personToPersonDto(Person person);

    public <T,R> List<R> mapList(List<T> objects, Function<T, R> mapper) {
        return objects
            .stream()
            .map(mapper)
            .toList();
    }
}
