package ar.edu.um.programacion2.micro_carro_de_compras.service.mapper;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.*;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.CarroCompraDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarroCompra} and its DTO {@link CarroCompraDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarroCompraMapper extends EntityMapper<CarroCompraDTO, CarroCompra> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CarroCompraDTO toDtoId(CarroCompra carroCompra);
}
