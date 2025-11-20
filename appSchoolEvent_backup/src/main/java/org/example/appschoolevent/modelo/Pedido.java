package org.example.appschoolevent.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = {"lineas"})
@ToString(exclude = {"lineas"})
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; /* Esto es de una tabla que tenga*/

    @OneToMany(mappedBy = "pedido")
    private Set<LineaPedido> lineas;
}
