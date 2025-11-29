package org.example.appschoolevent.mappers;

import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.enums.TipoCategoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoMapper {


    @Mapping(target = "categoria", expression = "java(org.example.appschoolevent.enums.TipoCategoria.valueOf(dto.getCategoria()))")
    Evento toEntity(EventoDTO dto);

    @Mapping(target = "categoria", expression = "java(entity.getCategoria().name())")
    EventoDTO toDTO(Evento entity);

}
