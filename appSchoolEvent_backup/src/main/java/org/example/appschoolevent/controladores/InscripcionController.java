package org.example.appschoolevent.controladores;


import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.InscripcionDTO;
import org.example.appschoolevent.servicios.InscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping("/{id}/inscripciones")
    public ResponseEntity<?> crearInscripcion(
            @PathVariable Integer id,
            @RequestParam Integer idUsuario) {
        try {
            InscripcionDTO dto = inscripcionService.inscribirUsuario(idUsuario, id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
