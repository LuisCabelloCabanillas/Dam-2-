package org.example.appschoolevent.repositorio;

import org.example.appschoolevent.modelo.Fotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotosRepository extends JpaRepository<Fotos, Integer> {

}
