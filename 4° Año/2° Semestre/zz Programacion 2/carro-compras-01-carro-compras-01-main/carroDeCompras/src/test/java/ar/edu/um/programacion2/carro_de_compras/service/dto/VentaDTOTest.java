package ar.edu.um.programacion2.carro_de_compras.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.carro_de_compras.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VentaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VentaDTO.class);
        VentaDTO ventaDTO1 = new VentaDTO();
        ventaDTO1.setId(1L);
        VentaDTO ventaDTO2 = new VentaDTO();
        assertThat(ventaDTO1).isNotEqualTo(ventaDTO2);
        ventaDTO2.setId(ventaDTO1.getId());
        assertThat(ventaDTO1).isEqualTo(ventaDTO2);
        ventaDTO2.setId(2L);
        assertThat(ventaDTO1).isNotEqualTo(ventaDTO2);
        ventaDTO1.setId(null);
        assertThat(ventaDTO1).isNotEqualTo(ventaDTO2);
    }
}
