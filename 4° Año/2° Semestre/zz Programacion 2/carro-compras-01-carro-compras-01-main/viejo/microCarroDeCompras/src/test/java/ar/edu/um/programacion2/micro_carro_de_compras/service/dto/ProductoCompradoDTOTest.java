package ar.edu.um.programacion2.micro_carro_de_compras.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.micro_carro_de_compras.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductoCompradoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductoCompradoDTO.class);
        ProductoCompradoDTO productoCompradoDTO1 = new ProductoCompradoDTO();
        productoCompradoDTO1.setId(1L);
        ProductoCompradoDTO productoCompradoDTO2 = new ProductoCompradoDTO();
        assertThat(productoCompradoDTO1).isNotEqualTo(productoCompradoDTO2);
        productoCompradoDTO2.setId(productoCompradoDTO1.getId());
        assertThat(productoCompradoDTO1).isEqualTo(productoCompradoDTO2);
        productoCompradoDTO2.setId(2L);
        assertThat(productoCompradoDTO1).isNotEqualTo(productoCompradoDTO2);
        productoCompradoDTO1.setId(null);
        assertThat(productoCompradoDTO1).isNotEqualTo(productoCompradoDTO2);
    }
}
