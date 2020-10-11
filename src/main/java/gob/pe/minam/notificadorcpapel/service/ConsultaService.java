package gob.pe.minam.notificadorcpapel.service;

import gob.pe.minam.notificadorcpapel.config.StaticConsultaMinam;
import gob.pe.minam.notificadorcpapel.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService implements  IConsultaService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Consulta getConsulta(){
        StaticConsultaMinam instancia = StaticConsultaMinam.getInstancia(null);
        Consulta consultas = instancia.getConsulta();
        if(consultas==null) {
            consultas = (Consulta) jdbcTemplate.queryForObject("SELECT SQL_NOTIFICADO, SQL_RECHAZADO, SQL_ACTUALIZAR, SQL_BUSCAR_CIUDADANO" +
                    " FROM CP_TBL_CONSULTA_JOB  WHERE ID_CONSULTA = 1", new BeanPropertyRowMapper(Consulta.class));
            instancia.setConsulta(consultas);
        }
       return consultas;
    }
}
