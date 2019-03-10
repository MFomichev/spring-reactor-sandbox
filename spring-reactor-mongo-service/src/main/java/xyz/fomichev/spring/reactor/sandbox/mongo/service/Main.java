package xyz.fomichev.spring.reactor.sandbox.mongo.service;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import xyz.fomichev.spring.reactor.sandbox.mongo.service.domain.Person;
import xyz.fomichev.spring.reactor.sandbox.mongo.service.repository.PersonRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

@Configuration
class Initializer {

    private final PersonRepository personRepository;

    public Initializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(ApplicationReadyEvent event) {
        personRepository.deleteAll()
                .thenMany(
                        personRepository.saveAll(List.of(
                                new Person("Ivanov", "Ivan"),
                                new Person("Petrov", "Alex"))))
                .blockLast();
    }
}
