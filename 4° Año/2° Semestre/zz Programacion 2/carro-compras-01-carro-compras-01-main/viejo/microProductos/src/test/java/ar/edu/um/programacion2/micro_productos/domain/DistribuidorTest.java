package ar.edu.um.programacion2.micro_productos.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.micro_productos.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DistribuidorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Distribuidor.class);
        Distribuidor distribuidor1 = new Distribuidor();
        distribuidor1.setId(1L);
        Distribuidor distribuidor2 = new Distribuidor();
        distribuidor2.setId(distribuidor1.getId());
        assertThat(distribuidor1).isEqualTo(distribuidor2);
        distribuidor2.setId(2L);
        assertThat(distribuidor1).isNotEqualTo(distribuidor2);
        distribuidor1.setId(null);
        assertThat(distribuidor1).isNotEqualTo(distribuidor2);
    }
}
