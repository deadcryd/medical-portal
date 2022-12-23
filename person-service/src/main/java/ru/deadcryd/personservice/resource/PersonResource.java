package ru.deadcryd.personservice.resource;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import java.util.List;
import java.util.Optional;
import ru.deadcryd.personservice.dto.PersonDto;
import ru.deadcryd.personservice.service.PersonService;

@Controller("/person")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @Post
    public Optional<Long> createPerson(@Body PersonDto person) {
        return personService.createPerson(person);
    }

    @Put("/{id}")
    public void editPerson(
        @PathVariable Long id,
        @Body PersonDto person
    ) {
        personService.editPerson(id, person);
    }

    @Get("{?region}")
    public List<PersonDto> getAllPersons(
        @Nullable @QueryValue String region
    ) {
        return personService.findAllPerson();
    }

    @Get("/{id}")
    public Optional<PersonDto> getPerson(@PathVariable Long id) {
        return personService.findPerson(id);
    }

    @Get("/verify{?name,passport}")
    public String verifyPerson(
        @Nullable @QueryValue String name,
        @Nullable @QueryValue String passport
    ) {
        return "";
    }
}
