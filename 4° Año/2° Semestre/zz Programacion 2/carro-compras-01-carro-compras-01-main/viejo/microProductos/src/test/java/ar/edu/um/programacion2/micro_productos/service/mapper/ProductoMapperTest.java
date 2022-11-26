package ar.edu.um.programacion2.micro_productos.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductoMapperTest {

    private ProductoMapper productoMapper;

    @BeforeEach
    public void setUp() {
        productoMapper = new ProductoMapperImpl();
    }
}
