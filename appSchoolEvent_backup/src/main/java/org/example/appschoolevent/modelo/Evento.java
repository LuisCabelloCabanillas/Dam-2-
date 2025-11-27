package org.example.appschoolevent.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "requisitos")
    private String requisitos;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "consiste")
    private String consiste;
}
