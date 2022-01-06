package ar.edu.um.ingenieria.programacion2.service.mapper;

import ar.edu.um.ingenieria.programacion2.domain.*;
import ar.edu.um.ingenieria.programacion2.service.dto.ProvinciaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Provincia} and its DTO {@link ProvinciaDTO}.
 */
@Mapper(componentModel = "spring", uses = { PaisMapper.class })
public interface ProvinciaMapper extends EntityMapper<ProvinciaDTO, Provincia> {
    @Mapping(target = "pais", source = "pais", qualifiedByName = "nombre")
    ProvinciaDTO toDto(Provincia s);
}
