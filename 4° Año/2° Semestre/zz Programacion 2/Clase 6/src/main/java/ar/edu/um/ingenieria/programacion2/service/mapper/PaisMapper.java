package ar.edu.um.ingenieria.programacion2.service.mapper;

import ar.edu.um.ingenieria.programacion2.domain.*;
import ar.edu.um.ingenieria.programacion2.service.dto.PaisDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pais} and its DTO {@link PaisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaisMapper extends EntityMapper<PaisDTO, Pais> {
    @Named("nombre")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    PaisDTO toDtoNombre(Pais pais);
}
