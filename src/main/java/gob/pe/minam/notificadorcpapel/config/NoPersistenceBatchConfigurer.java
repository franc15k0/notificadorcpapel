package gob.pe.minam.notificadorcpapel.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Configuration
@EnableAutoConfiguration
public class NoPersistenceBatchConfigurer extends DefaultBatchConfigurer {
    @Override
    public void setDataSource(DataSource dataSource) {

    }
}
