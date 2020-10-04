package gob.pe.minam.notificadorcpapel.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class NotificacionJobExecutionListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificacionJobExecutionListener.class);


    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            LOGGER.info("se finalizo el proceso de notificados");
        }

    }
}
