package ru.deadcryd.personservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.deadcryd.personservice.entity.base.AbstractBaseEntity;

@Entity
@Table(name = "document_type")
@Setter
@Getter
@ToString
public class DocumentType extends AbstractBaseEntity {

    private String typeName;
}
