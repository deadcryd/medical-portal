package ru.deadcryd.personservice.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String city;
    private String street;
    private Integer houseNumber;
    private Integer apartmentNumber;
}
