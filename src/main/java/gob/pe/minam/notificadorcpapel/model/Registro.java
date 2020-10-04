package gob.pe.minam.notificadorcpapel.model;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registro {
    private Long idRegistro;
    private String codEestaTrami;
    private String fecEnvioNotificacion;
}
