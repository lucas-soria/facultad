package ar.edu.um.ingenieria.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.ingenieria.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProvinciaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvinciaDTO.class);
        ProvinciaDTO provinciaDTO1 = new ProvinciaDTO();
        provinciaDTO1.setId(1L);
        ProvinciaDTO provinciaDTO2 = new ProvinciaDTO();
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
        provinciaDTO2.setId(provinciaDTO1.getId());
        assertThat(provinciaDTO1).isEqualTo(provinciaDTO2);
        provinciaDTO2.setId(2L);
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
        provinciaDTO1.setId(null);
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
    }
}
