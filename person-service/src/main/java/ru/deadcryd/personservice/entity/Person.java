package ru.deadcryd.personservice.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import ru.deadcryd.personservice.entity.base.AbstractBaseEntity;

@Entity
@Table(name = "person")
@Getter
@Setter
@ToString
public class Person extends AbstractBaseEntity {

    private String name;
    private String surname;
    private String patronymic;
    @Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contacts;
    @Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> documents;
    @Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;
}
