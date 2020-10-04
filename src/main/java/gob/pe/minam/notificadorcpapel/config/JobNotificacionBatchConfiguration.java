package gob.pe.minam.notificadorcpapel.config;

import gob.pe.minam.notificadorcpapel.listener.NotificacionJobExecutionListener;
import gob.pe.minam.notificadorcpapel.model.*;
import gob.pe.minam.notificadorcpapel.processor.NotificacionItemProcess;
import gob.pe.minam.notificadorcpapel.service.IConsultaService;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;


@Configuration
public class JobNotificacionBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private IConsultaService serviceConsultas;

    @Bean
    public JdbcCursorItemReader<Notificacion> reader() {
        Consulta consulta = serviceConsultas.getConsulta();
        String sql =    consulta.getSqlNotificado();
        return new JdbcCursorItemReaderBuilder<Notificacion>()
                .name("notificacionItemReader")
                .dataSource(dataSource)
                .rowMapper(new NotificacionRowMapper())
                .sql(sql)
                .build();
    }


    @Bean
    public NotificacionItemProcess processor2() {
        return new NotificacionItemProcess();
    }

    @Bean
    public JdbcBatchItemWriter<Registro> writer2(ItemPreparedStatementSetter<Registro> setter) {
        Consulta consulta = serviceConsultas.getConsulta();
        return new JdbcBatchItemWriterBuilder<Registro>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .itemPreparedStatementSetter(setter)
                .sql(consulta.getSqlActualizar()).dataSource(dataSource).build();
    }
    @Bean
    public ItemPreparedStatementSetter<Registro> setter2() {
        return (item, ps) -> {
            ps.setString(1, item.getCodEestaTrami());
            ps.setLong(2, item.getIdRegistro());
        };
    }


   @Bean
   @Primary
   @Qualifier(value = "notificarJob")
    public Job notificarJob(NotificacionJobExecutionListener listener, Step stepNotificados) {
        return jobBuilderFactory.get("notificarJob").incrementer(new RunIdIncrementer()).listener(listener).flow(stepNotificados)
                .end().build();
    }

    @Bean
    public Step stepNotificados(JdbcBatchItemWriter<Registro> writer, ItemReader<Notificacion> itemReader) {
        return stepBuilderFactory.get("stepNotificar").<Notificacion, Registro>chunk(10).reader(itemReader).processor(processor2())
                .writer(writer).build();
    }


}
