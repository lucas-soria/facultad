package ar.edu.um.programacion2.micro_carro_de_compras.service.mapper;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.*;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.VentaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Venta} and its DTO {@link VentaDTO}.
 */
@Mapper(componentModel = "spring", uses = { CarroCompraMapper.class })
public interface VentaMapper extends EntityMapper<VentaDTO, Venta> {
    @Mapping(target = "carro", source = "carro", qualifiedByName = "id")
    VentaDTO toDto(Venta s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VentaDTO toDtoId(Venta venta);
}
