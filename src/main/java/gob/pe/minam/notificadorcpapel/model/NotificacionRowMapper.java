package gob.pe.minam.notificadorcpapel.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificacionRowMapper implements RowMapper<Notificacion> {
    @Override
    public Notificacion mapRow(ResultSet resultSet, int i) throws SQLException {
        return Notificacion
                .builder()
                .numero(resultSet.getString(1))
                .idExpediente(resultSet.getLong(2))
                .numeroIdentificacion(resultSet.getString(3))
                .descripcion(resultSet.getString(4))
                .fechaSalida(resultSet.getString(5))
                .build();
    }
}
