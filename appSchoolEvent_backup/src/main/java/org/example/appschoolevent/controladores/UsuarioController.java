package org.example.appschoolevent.controladores;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.UsuarioDTO;
import org.example.appschoolevent.servicios.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioServicio;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO dto) {
        String resultado = usuarioServicio.registrarUsuario(dto);
        if (resultado.equals("Usuario registrado correctamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }
}
