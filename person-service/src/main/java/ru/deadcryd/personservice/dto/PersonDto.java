package ru.deadcryd.personservice.dto;

import java.util.Collections;
import java.util.List;
import lombok.Data;

@Data
public class PersonDto {

    private String name;
    private String surname;
    private String patronymic;
    private List<ContactDto> contacts = Collections.emptyList();
    private List<DocumentDto> documents = Collections.emptyList();
    private List<AddressDto> addresses = Collections.emptyList();
}
