package ar.edu.um.programacion2.carro_de_compras.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductoSeleccionadoMapperTest {

    private ProductoSeleccionadoMapper productoSeleccionadoMapper;

    @BeforeEach
    public void setUp() {
        productoSeleccionadoMapper = new ProductoSeleccionadoMapperImpl();
    }
}
