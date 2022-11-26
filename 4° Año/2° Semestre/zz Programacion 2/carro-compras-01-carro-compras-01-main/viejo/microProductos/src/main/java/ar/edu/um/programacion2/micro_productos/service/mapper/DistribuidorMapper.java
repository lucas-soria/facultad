package ar.edu.um.programacion2.micro_productos.service.mapper;

import ar.edu.um.programacion2.micro_productos.domain.*;
import ar.edu.um.programacion2.micro_productos.service.dto.DistribuidorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Distribuidor} and its DTO {@link DistribuidorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DistribuidorMapper extends EntityMapper<DistribuidorDTO, Distribuidor> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DistribuidorDTO toDtoId(Distribuidor distribuidor);
}
