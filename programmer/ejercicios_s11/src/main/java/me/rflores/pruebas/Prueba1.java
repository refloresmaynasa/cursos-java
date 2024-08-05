package me.rflores.pruebas;

import reactor.core.publisher.Flux;

public class Prueba1 {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("hola", "world", "welcome")
                .filter(s -> s.length() > 4)
                .map(String::toUpperCase);

        flux.subscribe(System.out::println, System.err::println, () -> System.out.println("OK"));
    }
}
