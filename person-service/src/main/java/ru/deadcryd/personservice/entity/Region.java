package ru.deadcryd.personservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.deadcryd.personservice.entity.base.AbstractBaseEntity;

@Entity
@Table(name = "region")
@Getter
@Setter
@ToString
public class Region extends AbstractBaseEntity {

    private String cityName;
}
