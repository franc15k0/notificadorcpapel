package gob.pe.minam.notificadorcpapel.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CiudadanoRowMapper implements RowMapper<Ciudadano> {
    @Override
    public Ciudadano mapRow(ResultSet resultSet, int i) throws SQLException {
        return Ciudadano
                .builder()
                .idCiudadano(resultSet.getLong(1))
                .idRegistro(resultSet.getLong(2))
                .idNatural(resultSet.getLong(3))
                .idJuridica(resultSet.getLong(4))
                .txtCorreoElectronico(resultSet.getString(5))
                .build();
    }
}
