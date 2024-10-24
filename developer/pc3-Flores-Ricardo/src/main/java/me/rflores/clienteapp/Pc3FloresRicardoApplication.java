package me.rflores.clienteapp;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableBatchProcessing
@EnableAspectJAutoProxy
public class Pc3FloresRicardoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pc3FloresRicardoApplication.class, args);
    }

}
