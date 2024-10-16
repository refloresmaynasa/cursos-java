package me.rflores.clienteapp.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try {
            jobLauncher.run(job, new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) // Use a unique parameter for job execution
                    .toJobParameters());
            return "Batch job started successfully!";
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
            return "Error starting batch job: " + e.getMessage();
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}