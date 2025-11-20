package org.example.appschoolevent.repositorio;

import org.example.appschoolevent.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoRepositorio extends JpaRepository<Pedido, Integer> {
}
