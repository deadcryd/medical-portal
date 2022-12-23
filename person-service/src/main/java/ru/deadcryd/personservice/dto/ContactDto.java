package ru.deadcryd.personservice.dto;

import lombok.Data;

@Data
public class ContactDto {
    private String contactType;
    private String value;
}
