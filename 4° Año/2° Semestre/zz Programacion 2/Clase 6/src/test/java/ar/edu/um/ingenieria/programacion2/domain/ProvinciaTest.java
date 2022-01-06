package ar.edu.um.ingenieria.programacion2.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.ingenieria.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProvinciaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Provincia.class);
        Provincia provincia1 = new Provincia();
        provincia1.setId(1L);
        Provincia provincia2 = new Provincia();
        provincia2.setId(provincia1.getId());
        assertThat(provincia1).isEqualTo(provincia2);
        provincia2.setId(2L);
        assertThat(provincia1).isNotEqualTo(provincia2);
        provincia1.setId(null);
        assertThat(provincia1).isNotEqualTo(provincia2);
    }
}
