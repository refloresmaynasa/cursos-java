package me.rflores.pruebas;

import me.rflores.modelo.Curso;
import reactor.core.publisher.Flux;

public class Prueba2 {
    public static void main(String[] args) {
        Flux<String> scursos = Flux.just("c01 programmer 5",
                "c02 developer 2",
                "c03 architect 5",
                "c04 expert 2");
        Flux<Curso> cursos = scursos.map(curso -> new Curso(curso.split(" ")[0],
                        curso.split(" ")[1],
                        Integer.parseInt(curso.split(" ")[2])))
                .filter(curso -> curso.creditos() >= 3)
                .doOnNext(curso -> {
                    if(curso == null) {
                        throw new RuntimeException("Nombres no pueden ser vacíos");
                    }
                });
        cursos.subscribe(e -> System.out.println(e.toString()),
                error -> System.out.println(error.getMessage()),
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Ha finalizado la ejecución del observable con éxito!");
                    }
                });
    }
}
