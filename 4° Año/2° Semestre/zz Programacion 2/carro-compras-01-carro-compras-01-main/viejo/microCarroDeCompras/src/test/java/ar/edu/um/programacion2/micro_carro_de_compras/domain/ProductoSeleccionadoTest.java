package ar.edu.um.programacion2.micro_carro_de_compras.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.micro_carro_de_compras.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductoSeleccionadoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductoSeleccionado.class);
        ProductoSeleccionado productoSeleccionado1 = new ProductoSeleccionado();
        productoSeleccionado1.setId(1L);
        ProductoSeleccionado productoSeleccionado2 = new ProductoSeleccionado();
        productoSeleccionado2.setId(productoSeleccionado1.getId());
        assertThat(productoSeleccionado1).isEqualTo(productoSeleccionado2);
        productoSeleccionado2.setId(2L);
        assertThat(productoSeleccionado1).isNotEqualTo(productoSeleccionado2);
        productoSeleccionado1.setId(null);
        assertThat(productoSeleccionado1).isNotEqualTo(productoSeleccionado2);
    }
}
