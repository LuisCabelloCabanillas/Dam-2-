package org.example.appschoolevent.servicios;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.enums.TipoCategoria;
import org.example.appschoolevent.mappers.EventoMapper;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.repositorio.EventoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventoService {

    private EventoRepository eventoRepository;
    private EventoMapper eventoMapper;

    public EventoDTO crearEvento(EventoDTO dto) {
        Evento evento = eventoMapper.toEntity(dto);
        Evento guardado = eventoRepository.save(evento);

        return eventoMapper.toDTO(guardado);
    }

    public List<EventoDTO> filtarEventos(LocalDate fecha, TipoCategoria categoria) {
        return eventoRepository.filtrarEventos(fecha, categoria)
                .stream().map(eventoMapper::toDTO).toList();
    }

    public EventoDTO obtenerEventoPorId(Integer id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
        return eventoMapper.toDTO(evento);
    }

}
