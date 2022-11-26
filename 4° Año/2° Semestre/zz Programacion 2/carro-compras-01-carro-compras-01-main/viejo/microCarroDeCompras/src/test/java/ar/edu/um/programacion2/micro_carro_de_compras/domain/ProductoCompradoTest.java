package ar.edu.um.programacion2.micro_carro_de_compras.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.micro_carro_de_compras.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductoCompradoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductoComprado.class);
        ProductoComprado productoComprado1 = new ProductoComprado();
        productoComprado1.setId(1L);
        ProductoComprado productoComprado2 = new ProductoComprado();
        productoComprado2.setId(productoComprado1.getId());
        assertThat(productoComprado1).isEqualTo(productoComprado2);
        productoComprado2.setId(2L);
        assertThat(productoComprado1).isNotEqualTo(productoComprado2);
        productoComprado1.setId(null);
        assertThat(productoComprado1).isNotEqualTo(productoComprado2);
    }
}
