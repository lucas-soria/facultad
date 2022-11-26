package ar.edu.um.programacion2.productos.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.productos.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DistribuidorDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistribuidorDTO.class);
        DistribuidorDTO distribuidorDTO1 = new DistribuidorDTO();
        distribuidorDTO1.setId(1L);
        DistribuidorDTO distribuidorDTO2 = new DistribuidorDTO();
        assertThat(distribuidorDTO1).isNotEqualTo(distribuidorDTO2);
        distribuidorDTO2.setId(distribuidorDTO1.getId());
        assertThat(distribuidorDTO1).isEqualTo(distribuidorDTO2);
        distribuidorDTO2.setId(2L);
        assertThat(distribuidorDTO1).isNotEqualTo(distribuidorDTO2);
        distribuidorDTO1.setId(null);
        assertThat(distribuidorDTO1).isNotEqualTo(distribuidorDTO2);
    }
}
