package gob.pe.minam.notificadorcpapel.model;

import lombok.*;





@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ciudadano {
    private Long idCiudadano;
    private Long idRegistro;
    private Long idNatural;
    private Long idJuridica;
    private String txtCorreoElectronico;
}

