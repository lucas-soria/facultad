package ar.edu.um.programacion2.carro_de_compras.service.mapper;

import ar.edu.um.programacion2.carro_de_compras.domain.ProductoComprado;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoCompradoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductoComprado} and its DTO {@link ProductoCompradoDTO}.
 */
@Mapper(componentModel = "spring", uses = { VentaMapper.class })
public interface ProductoCompradoMapper extends EntityMapper<ProductoCompradoDTO, ProductoComprado> {
    @Mapping(target = "venta", source = "venta", qualifiedByName = "id")
    ProductoCompradoDTO toDto(ProductoComprado s);
}
