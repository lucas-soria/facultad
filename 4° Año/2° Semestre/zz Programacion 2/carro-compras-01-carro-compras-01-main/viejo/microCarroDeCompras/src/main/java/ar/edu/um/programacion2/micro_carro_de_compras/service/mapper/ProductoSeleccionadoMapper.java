package ar.edu.um.programacion2.micro_carro_de_compras.service.mapper;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.*;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductoSeleccionado} and its DTO {@link ProductoSeleccionadoDTO}.
 */
@Mapper(componentModel = "spring", uses = { CarroCompraMapper.class })
public interface ProductoSeleccionadoMapper extends EntityMapper<ProductoSeleccionadoDTO, ProductoSeleccionado> {
    @Mapping(target = "carro", source = "carro", qualifiedByName = "id")
    ProductoSeleccionadoDTO toDto(ProductoSeleccionado s);
}
