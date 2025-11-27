package org.example.appschoolevent.servicios;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.repositorio.EventoRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventoService {

    private EventoRepository eventoRepository;

    public String crearEvento(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setNombre(dto.getNombre());
        evento.setFecha(dto.getFecha());
        evento.setLugar(dto.getLugar());
        evento.setConsiste(dto.getConsiste());
        evento.setRequisitos(dto.getRequisitos());

        eventoRepository.save(evento);

        return "Evento creado exitosamente";
    }
}
