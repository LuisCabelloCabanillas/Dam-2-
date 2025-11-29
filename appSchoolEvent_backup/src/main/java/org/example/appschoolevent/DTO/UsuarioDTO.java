package org.example.appschoolevent.DTO;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

@Data
public class UsuarioDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String contrasena;
    private LocalDate fecha_de_nacimiento;
    private String correo;
    private String tipo;
}
