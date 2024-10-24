package me.rflores.clienteapp;

import me.rflores.clienteapp.models.daos.TarjetaCreditoRepository;
import me.rflores.clienteapp.models.daos.TarjetaCreditoRiesgoRepository;
import me.rflores.clienteapp.models.entities.TarjetaCredito;
import me.rflores.clienteapp.models.entities.TarjetaCreditoRiesgo;
import me.rflores.clienteapp.services.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    private final TarjetaCreditoRiesgoRepository tarjetaCreditoRiesgoRepository;

    public BatchConfiguration(TarjetaCreditoRepository tarjetaCreditoRepository, TarjetaCreditoRiesgoRepository tarjetaCreditoRiesgoRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
        this.tarjetaCreditoRiesgoRepository = tarjetaCreditoRiesgoRepository;
    }


    @Bean
    public TarjetaCreditoReader reader() {
        return new TarjetaCreditoReader(tarjetaCreditoRepository);
    }

    @Bean
    public TarjetaCreditoProcessor processor() {
        return new TarjetaCreditoProcessor();
    }

    @Bean
    public TarjetaCreditoWriter writer() {
        return new TarjetaCreditoWriter(tarjetaCreditoRiesgoRepository);
    }

    @Bean
    public Job myJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("myJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TarjetaCreditoReader reader, TarjetaCreditoProcessor processor, TarjetaCreditoWriter writer) {
        return new StepBuilder("step1", jobRepository)
                .<TarjetaCredito, TarjetaCreditoRiesgo>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new CustomStepListener())
                .build();
    }
}