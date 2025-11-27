package org.example.appschoolevent.DTO;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

@Data
public class UsuarioDTO {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "fecha_de_nacimiento")
    private LocalDate fecha_de_nacimiento;

    @Column(name = "correo")
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private String tipo;

    public enum TipoUsuario {
        Admin, User
    }

}
