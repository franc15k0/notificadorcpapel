package gob.pe.minam.notificadorcpapel.scheduler;

import gob.pe.minam.notificadorcpapel.runner.JobRunner;
import gob.pe.minam.notificadorcpapel.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class JobScheduler {



    private JobRunner jobRunner;

    public JobScheduler(JobRunner jobRunner){
        this.jobRunner = jobRunner;
    }

    //@Scheduled(cron="0 0/2 * 1/1 * ?")
    @Scheduled(cron="${cron.tarea}")
    public void jobSchduled(){
        System.out.println("Job triggered");
        jobRunner.runBatchJob();
    }

}
