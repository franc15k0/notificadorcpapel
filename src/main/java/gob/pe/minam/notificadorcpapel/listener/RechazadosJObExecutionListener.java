package gob.pe.minam.notificadorcpapel.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class RechazadosJObExecutionListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(RechazadosJObExecutionListener.class);
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            LOGGER.info("se finalizo el proceso de rechazados");
        }

    }

}
