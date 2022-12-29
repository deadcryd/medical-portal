package ru.deadcryd.personservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import ru.deadcryd.personservice.entity.base.AbstractBaseEntity;

@Entity
@Table(name = "address")
@Setter
@Getter
@ToString
public class Address extends AbstractBaseEntity {

    @Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Region city;
    private String street;
    private Integer houseNumber;
    private Integer apartmentNumber;
}
