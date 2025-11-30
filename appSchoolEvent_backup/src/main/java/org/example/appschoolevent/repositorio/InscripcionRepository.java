package org.example.appschoolevent.repositorio;

import org.example.appschoolevent.modelo.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    boolean existsByUsuarioIdAndEventoId(Integer idUsuario, Integer idEvento);
}
