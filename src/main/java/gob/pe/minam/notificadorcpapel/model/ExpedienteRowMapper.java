package gob.pe.minam.notificadorcpapel.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpedienteRowMapper implements RowMapper<Expediente> {

    @Override
    public Expediente mapRow(ResultSet resultSet, int i) throws SQLException {
        return Expediente
                .builder()
                .numero(resultSet.getString(1))
                .idExpediente(resultSet.getLong(2))

                .build();
    }
}
