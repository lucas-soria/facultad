package ar.edu.um.programacion2.micro_productos.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.micro_productos.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductoDTO.class);
        ProductoDTO productoDTO1 = new ProductoDTO();
        productoDTO1.setId(1L);
        ProductoDTO productoDTO2 = new ProductoDTO();
        assertThat(productoDTO1).isNotEqualTo(productoDTO2);
        productoDTO2.setId(productoDTO1.getId());
        assertThat(productoDTO1).isEqualTo(productoDTO2);
        productoDTO2.setId(2L);
        assertThat(productoDTO1).isNotEqualTo(productoDTO2);
        productoDTO1.setId(null);
        assertThat(productoDTO1).isNotEqualTo(productoDTO2);
    }
}
