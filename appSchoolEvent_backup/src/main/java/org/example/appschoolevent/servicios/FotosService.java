package org.example.appschoolevent.servicios;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.FotosDTO;
import org.example.appschoolevent.mappers.FotosMapper;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.modelo.Fotos;
import org.example.appschoolevent.repositorio.FotosRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FotosService {

    private final FotosRepository fotosRepository;
    private final EventoService eventoService;
    private final FotosMapper fotosMapper;

    public FotosDTO guardarFoto(Integer idEvento, String url){

        Evento evento = eventoService.obtenerEventoPorIdEntidad(idEvento);

        Fotos foto = new Fotos();
        foto.setEvento(evento);
        foto.setFoto(url);

        foto = fotosRepository.save(foto);
        return fotosMapper.toDto(foto);
    }

}
