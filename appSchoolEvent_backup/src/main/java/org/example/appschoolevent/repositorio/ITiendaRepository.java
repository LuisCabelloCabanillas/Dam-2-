package org.example.appschoolevent.repositorio;

import org.example.appschoolevent.modelo.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITiendaRepository extends JpaRepository<Tienda,Integer> {
}
