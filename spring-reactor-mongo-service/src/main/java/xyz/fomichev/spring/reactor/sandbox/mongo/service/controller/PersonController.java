package xyz.fomichev.spring.reactor.sandbox.mongo.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import xyz.fomichev.spring.reactor.sandbox.mongo.service.domain.Person;
import xyz.fomichev.spring.reactor.sandbox.mongo.service.repository.PersonRepository;

/**
 * @author Mikhail Fomichev (MFomichev)
 */
@RestController
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("persons")
    public Flux<Person> getPersons() {
        return personRepository.findAll();
    }

}
