package org.example.appschoolevent.repositorio;

import org.example.appschoolevent.enums.TipoCategoria;
import org.example.appschoolevent.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    Optional<Evento> findById(Integer id);


    @Query(value = "select e from Evento e " +
            "where (:fecha is null or e.fecha = :fecha) " +
            "and (:categoria is null or e.categoria = :categoria)")
    List<Evento> filtrarEventos(@Param("fecha") LocalDate fecha,
                                @Param("categoria") TipoCategoria categoria);
}
