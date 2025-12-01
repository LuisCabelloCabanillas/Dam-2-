package org.example.appschoolevent.servicios;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.EstadisticaDTO;
import org.example.appschoolevent.repositorio.EventoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadisticaService {

    private final EventoRepository eventoRepository;

    public List<EstadisticaDTO> obtenerEstadisticas() {
        PageRequest top5 = PageRequest.of(0, 5);
        return eventoRepository.obtenerEventosIns(top5);
    }

}
