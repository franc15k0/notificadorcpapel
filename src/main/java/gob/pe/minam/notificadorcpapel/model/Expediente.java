package gob.pe.minam.notificadorcpapel.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expediente {
    private Long idExpediente;
    private String numero;
}
