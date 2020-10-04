package gob.pe.minam.notificadorcpapel.config;

import gob.pe.minam.notificadorcpapel.listener.NotificacionJobExecutionListener;
import gob.pe.minam.notificadorcpapel.model.Consulta;
import gob.pe.minam.notificadorcpapel.model.Expediente;
import gob.pe.minam.notificadorcpapel.model.ExpedienteRowMapper;
import gob.pe.minam.notificadorcpapel.model.Registro;
import gob.pe.minam.notificadorcpapel.processor.NotRechazadoItemProcess;
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
import javax.sql.DataSource;


@Configuration
public class JobRechazadosBatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private IConsultaService serviceConsultas;
    @Bean
    public JdbcCursorItemReader<Expediente> readerNot() {
        Consulta consulta = serviceConsultas.getConsulta();
        String sql = consulta.getSqlRechazado() ;

        return new JdbcCursorItemReaderBuilder<Expediente>()
                .name("expedienteItemReader")
                .dataSource(dataSource)
                .rowMapper(new ExpedienteRowMapper())
                .sql(sql)
                //.preparedStatementSetter(preparedStatementSetter)
                .build();
    }
    @Bean
    public NotRechazadoItemProcess processor() {
        return new NotRechazadoItemProcess();
    }
    @Bean
    public JdbcBatchItemWriter<Registro> writer(ItemPreparedStatementSetter<Registro> setter) {
        Consulta consulta = serviceConsultas.getConsulta();
        return new JdbcBatchItemWriterBuilder<Registro>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .itemPreparedStatementSetter(setter)
                .sql(consulta.getSqlActualizar()).dataSource(dataSource).build();
    }
    @Bean
    public ItemPreparedStatementSetter<Registro> setter() {
        return (item, ps) -> {
            ps.setString(1, item.getCodEestaTrami());
            ps.setLong(2, item.getIdRegistro());
        };
    }
    @Bean

    @Qualifier(value = "rechazarJob")
    public Job rechazarJob(NotificacionJobExecutionListener listener, Step stepRechazar) {
        return jobBuilderFactory.get("rechazarJob").incrementer(new RunIdIncrementer()).listener(listener).flow(stepRechazar)
                .end().build();
    }
    @Bean
    public Step stepRechazar(JdbcBatchItemWriter<Registro> writer, ItemReader<Expediente> itemReader) {
        return stepBuilderFactory.get("stepRechazar").<Expediente, Registro>chunk(10).reader(itemReader).processor(processor())
                .writer(writer).build();
    }
}
