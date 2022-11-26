package ar.edu.um.programacion2.carro_de_compras.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VentaMapperTest {

    private VentaMapper ventaMapper;

    @BeforeEach
    public void setUp() {
        ventaMapper = new VentaMapperImpl();
    }
}
