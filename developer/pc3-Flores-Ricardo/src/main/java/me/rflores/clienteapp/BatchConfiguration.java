package me.rflores.clienteapp;

import me.rflores.clienteapp.models.daos.TarjetaCreditoRepository;
import me.rflores.clienteapp.models.daos.TarjetaCreditoRiesgoRepository;
import me.rflores.clienteapp.models.entities.TarjetaCredito;
import me.rflores.clienteapp.models.entities.TarjetaCreditoRiesgo;
import me.rflores.clienteapp.services.JobCompletionNotificationListener;
import me.rflores.clienteapp.services.TarjetaCreditoProcessor;
import me.rflores.clienteapp.services.TarjetaCreditoReader;
import me.rflores.clienteapp.services.TarjetaCreditoWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    //@Autowired
    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    //@Autowired
    private final TarjetaCreditoRiesgoRepository tarjetaCreditoRiesgoRepository;

    private final DataSource dataSource;

    public BatchConfiguration(TarjetaCreditoRepository tarjetaCreditoRepository, TarjetaCreditoRiesgoRepository tarjetaCreditoRiesgoRepository, DataSource dataSource) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
        this.tarjetaCreditoRiesgoRepository = tarjetaCreditoRiesgoRepository;
        this.dataSource = dataSource;
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
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      TarjetaCreditoReader reader, TarjetaCreditoProcessor processor, TarjetaCreditoWriter writer) {
        return new StepBuilder("step1", jobRepository)
                .<TarjetaCredito, TarjetaCreditoRiesgo>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}