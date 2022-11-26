package ar.edu.um.programacion2.carro_de_compras.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.carro_de_compras.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CarroCompraDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarroCompraDTO.class);
        CarroCompraDTO carroCompraDTO1 = new CarroCompraDTO();
        carroCompraDTO1.setId(1L);
        CarroCompraDTO carroCompraDTO2 = new CarroCompraDTO();
        assertThat(carroCompraDTO1).isNotEqualTo(carroCompraDTO2);
        carroCompraDTO2.setId(carroCompraDTO1.getId());
        assertThat(carroCompraDTO1).isEqualTo(carroCompraDTO2);
        carroCompraDTO2.setId(2L);
        assertThat(carroCompraDTO1).isNotEqualTo(carroCompraDTO2);
        carroCompraDTO1.setId(null);
        assertThat(carroCompraDTO1).isNotEqualTo(carroCompraDTO2);
    }
}
