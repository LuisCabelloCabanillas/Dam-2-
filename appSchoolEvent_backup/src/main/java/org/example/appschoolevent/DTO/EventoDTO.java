package org.example.appschoolevent.DTO;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class EventoDTO {
    private String nombre;
    private String lugar;
    private String requisitos;
    private LocalDate fecha;
    private String consiste;
    private String categoria;
}
