package org.example.appschoolevent.controladores;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.UsuarioActividadDTO;
import org.example.appschoolevent.servicios.UsuarioActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://frontend-049h.onrender.com")
@RestController
@RequestMapping("estadisticasUsuario")
@AllArgsConstructor
public class UsuarioActividadController {

    private final UsuarioActividadService usuarioActividadService;

    @GetMapping("/usuarioMasActivo")
    public ResponseEntity<UsuarioActividadDTO> obtenerUsuarioMasActivo() {
        UsuarioActividadDTO usuarioMasActivo = usuarioActividadService.obtenerUsuarioMasActivo();
        return ResponseEntity.ok(usuarioMasActivo);
    }
}
