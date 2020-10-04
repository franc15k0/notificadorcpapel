package gob.pe.minam.notificadorcpapel.runner;

import gob.pe.minam.notificadorcpapel.listener.NotificacionJobExecutionListener;
import gob.pe.minam.notificadorcpapel.util.Constante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobRunner {
    private static final Logger logger = LoggerFactory.getLogger(NotificacionJobExecutionListener.class);


    private JobLauncher simpleJobLauncher;
    private Job jobNotificacionBatchConfiguration;

    @Autowired
    public JobRunner(Job jobNotificacionBatchConfiguration, JobLauncher jobLauncher) {
        this.simpleJobLauncher = jobLauncher;
        this.jobNotificacionBatchConfiguration = jobNotificacionBatchConfiguration;
    }

    @Async
    public void runBatchJob() {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", new Date(), true);
        runJob(jobNotificacionBatchConfiguration, jobParametersBuilder.toJobParameters());
    }


    public void runJob(Job job, JobParameters parameters) {
        try {
            System.out.println("Hello world from Command Line runJob");
            JobExecution jobExecution = simpleJobLauncher.run(job, parameters);

        } catch (JobExecutionAlreadyRunningException e) {
            logger.info("Job with fileName={} is already running.", parameters.getParameters().get(Constante.FILE_NAME_CONTEXT_KEY));
        } catch (JobRestartException e) {
            logger.info("Job with fileName={} was not restarted.", parameters.getParameters().get(Constante.FILE_NAME_CONTEXT_KEY));
        } catch (JobInstanceAlreadyCompleteException e) {
            logger.info("Job with fileName={} already completed.", parameters.getParameters().get(Constante.FILE_NAME_CONTEXT_KEY));
        } catch (JobParametersInvalidException e) {
            logger.info("Invalid job parameters.", parameters.getParameters().get(Constante.FILE_NAME_CONTEXT_KEY));
        }
    }

}
