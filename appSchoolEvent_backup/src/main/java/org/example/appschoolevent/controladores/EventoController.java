package org.example.appschoolevent.controladores;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.enums.TipoCategoria;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.servicios.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {

    private EventoService eventoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearEvento(@RequestBody EventoDTO dto) {
        String resultado = eventoService.crearEvento(dto);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Evento>> filtrarEventos(
            @RequestParam(required = false) LocalDate fecha,
            @RequestParam(required = false) TipoCategoria categoria) {
        List<Evento> eventos = eventoService.filtarEventos(fecha, categoria);
        return ResponseEntity.ok(eventos);
    }
}
