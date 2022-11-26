package ar.edu.um.programacion2.productos.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DistribuidorMapperTest {

    private DistribuidorMapper distribuidorMapper;

    @BeforeEach
    public void setUp() {
        distribuidorMapper = new DistribuidorMapperImpl();
    }
}
