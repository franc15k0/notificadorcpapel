package gob.pe.minam.notificadorcpapel.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    private Long idExpediente;
    private String numero;
    private String estado;
    private String estadoProceso;
    private int seleccionado;
    private int enviado;
    private String numeroIdentificacion;
    private String numeroExpediente;
    private String descripcion;
    private String fechaSalida;
    private int estacionActual;
    private int estacionPrevia;

}
