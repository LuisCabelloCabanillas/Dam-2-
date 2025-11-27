package org.example.appschoolevent.servicios;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.UsuarioDTO;
import org.example.appschoolevent.modelo.Usuario;
import org.example.appschoolevent.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public String registrarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            return "Correo ya registrado";
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setFecha_de_nacimiento(dto.getFecha_de_nacimiento());
        usuario.setContrasena(dto.getContrasena());

        try {
            usuario.setTipo(Usuario.TipoUsuario.valueOf(dto.getTipo()));
        } catch (IllegalArgumentException e) {
            return "Tipo de usuario inválido";
        }

        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente";
    }
}