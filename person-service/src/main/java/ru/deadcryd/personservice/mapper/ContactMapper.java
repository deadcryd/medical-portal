package ru.deadcryd.personservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.deadcryd.personservice.dto.ContactDto;
import ru.deadcryd.personservice.entity.Contact;
import ru.deadcryd.personservice.entity.ContactType;

@Mapper(componentModel = "jakarta")
public interface ContactMapper {

    @Mapping(target = "contactType", expression = "java(contactType(contactDto))")
    Contact contactDtoToContact(ContactDto contactDto);

    @Mapping(target = "contactType", source = "contact.contactType.typeName")
    ContactDto contactToContactDto(Contact contact);

    default ContactType contactType(ContactDto contactDto) {
        ContactType contactType = new ContactType();
        contactType.setTypeName(contactDto.getContactType());
        return contactType;
    }
}
