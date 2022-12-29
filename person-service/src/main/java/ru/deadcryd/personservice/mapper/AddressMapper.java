package ru.deadcryd.personservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.deadcryd.personservice.dto.AddressDto;
import ru.deadcryd.personservice.entity.Address;
import ru.deadcryd.personservice.entity.Region;

@Mapper(componentModel = "jakarta")
public interface AddressMapper {

    @Mapping(target = "city", expression = "java(city(addressDto))")
    Address addressDtoToAddress(AddressDto addressDto);

    @Mapping(target = "city", source = "address.city.cityName")
    AddressDto addressToAddressDto(Address address);

    default Region city(AddressDto addressDto) {
        Region region = new Region();
        region.setCityName(addressDto.getCity());
        return region;
    }
}
