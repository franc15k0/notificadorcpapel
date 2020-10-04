package gob.pe.minam.notificadorcpapel.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensajeCorreo {
    private String hacia;
    private String contenido;
}
