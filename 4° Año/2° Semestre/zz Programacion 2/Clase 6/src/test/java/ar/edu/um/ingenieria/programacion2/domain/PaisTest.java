package ar.edu.um.ingenieria.programacion2.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.ingenieria.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaisTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pais.class);
        Pais pais1 = new Pais();
        pais1.setId(1L);
        Pais pais2 = new Pais();
        pais2.setId(pais1.getId());
        assertThat(pais1).isEqualTo(pais2);
        pais2.setId(2L);
        assertThat(pais1).isNotEqualTo(pais2);
        pais1.setId(null);
        assertThat(pais1).isNotEqualTo(pais2);
    }
}
