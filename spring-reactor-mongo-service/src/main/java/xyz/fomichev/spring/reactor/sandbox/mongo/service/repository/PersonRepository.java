package xyz.fomichev.spring.reactor.sandbox.mongo.service.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import xyz.fomichev.spring.reactor.sandbox.mongo.service.domain.Person;

/**
 * @author Mikhail Fomichev (MFomichev)
 */
public interface PersonRepository extends ReactiveCrudRepository<Person, String> {

}
