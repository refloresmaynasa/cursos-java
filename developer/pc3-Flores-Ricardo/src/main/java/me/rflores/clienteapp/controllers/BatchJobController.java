package me.rflores.clienteapp.controllers;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RequestMapping("api/batch")
@RestController
public class BatchJobController {

    private final JobLauncher jobLauncher;
    private final Job job;

    public BatchJobController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping()
    public String test() {
        return "ok";
    }

    @PostMapping("")
    public String runBatchJob() {
        JobParameters parameters = new JobParametersBuilder()
                .addDate("time", new Date()) // Optional: just to have a unique parameter
                .toJobParameters();

        try {
            JobExecution jobExecution = jobLauncher.run(job, parameters);
            jobExecution.setStartTime(LocalDateTime.now());
            return "Batch job started successfully!";
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
            return "Error starting batch job: " + e.getMessage();
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}