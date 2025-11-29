package org.example.appschoolevent.servicios;

import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.UsuarioDTO;
import org.example.appschoolevent.mappers.UsuarioMapper;
import org.example.appschoolevent.modelo.Usuario;
import org.example.appschoolevent.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public UsuarioDTO registrarUsuario(UsuarioDTO dto) {

        Usuario usuario = usuarioMapper.toEntity(dto);
        Usuario guardado= usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(guardado);
    }
}