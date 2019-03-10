package xyz.fomichev.spring.reactor.sandbox.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public RouterFunction<ServerResponse> routes(WebClient webClient) {
        return RouterFunctions.route(RequestPredicates.GET("/persons"), request ->
                ServerResponse.ok().body(webClient
                                .get()
                                .uri("http://localhost:8080/persons")
                                .retrieve()
                                .bodyToFlux(Person.class),
                        Person.class
                ));
    }
}