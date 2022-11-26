package ar.edu.um.programacion2.productos.service.mapper;

import ar.edu.um.programacion2.productos.domain.Producto;
import ar.edu.um.programacion2.productos.service.dto.ProductoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Producto} and its DTO {@link ProductoDTO}.
 */
@Mapper(componentModel = "spring", uses = { DistribuidorMapper.class })
public interface ProductoMapper extends EntityMapper<ProductoDTO, Producto> {
    @Mapping(target = "distribuidor", source = "distribuidor", qualifiedByName = "id")
    ProductoDTO toDto(Producto s);
}
