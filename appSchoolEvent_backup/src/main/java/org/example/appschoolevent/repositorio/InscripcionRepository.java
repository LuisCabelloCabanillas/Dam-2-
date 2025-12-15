package org.example.appschoolevent.repositorio;

import org.example.appschoolevent.modelo.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    boolean existsByUsuarioIdAndEventoId(Integer idUsuario, Integer idEvento);
    List<Inscripcion> findByUsuarioId(Integer idUsuario);
    void deleteByEventoId(Integer idEvento);

}
