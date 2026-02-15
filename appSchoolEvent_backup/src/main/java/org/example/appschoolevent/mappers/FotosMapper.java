package org.example.appschoolevent.mappers;

import org.example.appschoolevent.DTO.FotosDTO;
import org.example.appschoolevent.modelo.Fotos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FotosMapper {

    @Mapping(target = "idEvento", source = "evento.id")
    FotosDTO toDTO(Fotos foto);
}