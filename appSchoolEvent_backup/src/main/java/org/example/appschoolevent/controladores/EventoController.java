package org.example.appschoolevent.controladores;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.servicios.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
