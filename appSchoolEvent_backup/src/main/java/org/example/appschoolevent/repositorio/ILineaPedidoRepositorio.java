package org.example.appschoolevent.repositorio;

import org.example.appschoolevent.modelo.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILineaPedidoRepositorio extends JpaRepository<LineaPedido, Integer> {
}
