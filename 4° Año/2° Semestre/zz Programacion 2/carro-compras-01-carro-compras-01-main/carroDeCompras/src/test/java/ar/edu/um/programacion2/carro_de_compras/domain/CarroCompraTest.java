package ar.edu.um.programacion2.carro_de_compras.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.carro_de_compras.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CarroCompraTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarroCompra.class);
        CarroCompra carroCompra1 = new CarroCompra();
        carroCompra1.setId(1L);
        CarroCompra carroCompra2 = new CarroCompra();
        carroCompra2.setId(carroCompra1.getId());
        assertThat(carroCompra1).isEqualTo(carroCompra2);
        carroCompra2.setId(2L);
        assertThat(carroCompra1).isNotEqualTo(carroCompra2);
        carroCompra1.setId(null);
        assertThat(carroCompra1).isNotEqualTo(carroCompra2);
    }
}
