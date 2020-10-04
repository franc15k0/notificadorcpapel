package gob.pe.minam.notificadorcpapel.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    private String sqlNotificado;
    private String sqlRechazado;
    private String sqlActualizar;
    private String sqlBuscarCiudadano;
}
