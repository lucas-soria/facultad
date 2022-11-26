package ar.edu.um.programacion2.micro_carro_de_compras.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarroCompraMapperTest {

    private CarroCompraMapper carroCompraMapper;

    @BeforeEach
    public void setUp() {
        carroCompraMapper = new CarroCompraMapperImpl();
    }
}
