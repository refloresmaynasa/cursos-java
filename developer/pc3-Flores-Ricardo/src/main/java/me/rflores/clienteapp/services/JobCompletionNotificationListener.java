package me.rflores.clienteapp.services;

import me.rflores.clienteapp.models.daos.TarjetaCreditoRepository;
import me.rflores.clienteapp.models.daos.TarjetaCreditoRiesgoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private TarjetaCreditoRiesgoRepository tarjetaCreditoRiesgoRepository;

    @Autowired
    private TarjetaCreditoRepository tarjetaCreditoRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.setStartTime(LocalDateTime.now());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            log.info("Tarjetas de Alto Riesgo {}:", tarjetaCreditoRepository.count());
        }
    }
}