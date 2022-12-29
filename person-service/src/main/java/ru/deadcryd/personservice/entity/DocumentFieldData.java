package ru.deadcryd.personservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.deadcryd.personservice.entity.base.AbstractBaseEntity;

@Entity
@Table(name = "document_field_Data")
@Setter
@Getter
@ToString
public class DocumentFieldData extends AbstractBaseEntity {

    private String fieldName;
    private String fieldValue;
}
