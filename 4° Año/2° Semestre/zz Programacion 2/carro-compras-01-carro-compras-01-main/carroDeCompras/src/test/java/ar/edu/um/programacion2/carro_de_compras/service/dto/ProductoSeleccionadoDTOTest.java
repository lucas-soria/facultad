package ar.edu.um.programacion2.carro_de_compras.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.carro_de_compras.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductoSeleccionadoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductoSeleccionadoDTO.class);
        ProductoSeleccionadoDTO productoSeleccionadoDTO1 = new ProductoSeleccionadoDTO();
        productoSeleccionadoDTO1.setId(1L);
        ProductoSeleccionadoDTO productoSeleccionadoDTO2 = new ProductoSeleccionadoDTO();
        assertThat(productoSeleccionadoDTO1).isNotEqualTo(productoSeleccionadoDTO2);
        productoSeleccionadoDTO2.setId(productoSeleccionadoDTO1.getId());
        assertThat(productoSeleccionadoDTO1).isEqualTo(productoSeleccionadoDTO2);
        productoSeleccionadoDTO2.setId(2L);
        assertThat(productoSeleccionadoDTO1).isNotEqualTo(productoSeleccionadoDTO2);
        productoSeleccionadoDTO1.setId(null);
        assertThat(productoSeleccionadoDTO1).isNotEqualTo(productoSeleccionadoDTO2);
    }
}
