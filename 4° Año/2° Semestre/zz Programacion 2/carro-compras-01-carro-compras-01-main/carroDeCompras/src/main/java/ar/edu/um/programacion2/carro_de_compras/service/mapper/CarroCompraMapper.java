package ar.edu.um.programacion2.carro_de_compras.service.mapper;

import ar.edu.um.programacion2.carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarroCompra} and its DTO {@link CarroCompraDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface CarroCompraMapper extends EntityMapper<CarroCompraDTO, CarroCompra> {
    @Mapping(target = "cliente", source = "cliente", qualifiedByName = "id")
    CarroCompraDTO toDto(CarroCompra s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CarroCompraDTO toDtoId(CarroCompra carroCompra);
}
