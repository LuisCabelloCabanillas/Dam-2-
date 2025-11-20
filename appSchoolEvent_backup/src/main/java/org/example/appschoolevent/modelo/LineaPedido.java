package org.example.appschoolevent.modelo;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; /*Esto es de una tabla que tenga*/

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; /* Esto es de una tabla que tenga */
}
