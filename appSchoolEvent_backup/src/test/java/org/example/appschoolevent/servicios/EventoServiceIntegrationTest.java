package org.example.appschoolevent.servicios;


import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.mappers.EventoMapper;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.repositorio.EventoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class EventoServiceIntegrationTest {

    @InjectMocks
    private EventoService eventoService;


    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private EventoMapper mapper;


    @Test
    @DisplayName("Teste de Integración -> BuscarPorID()")
    public void buscarPorIdIntegrationTest(){

        //GIVEN
        Mockito.when(this.eventoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Evento()));
        Mockito.when(this.mapper.toDTO(Mockito.any(Evento.class))).thenReturn(new EventoDTO());

        //WHEN
        this.eventoService.obtenerEventoPorId(1);


        //THEN
        Mockito.verify(this.eventoRepository).findById(Mockito.anyInt());
        Mockito.verify(this.mapper).toDTO(Mockito.any());


    }








}
