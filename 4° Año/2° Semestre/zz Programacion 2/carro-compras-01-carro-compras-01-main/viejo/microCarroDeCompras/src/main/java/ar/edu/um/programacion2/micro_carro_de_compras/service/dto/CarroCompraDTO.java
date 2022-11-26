package ar.edu.um.programacion2.micro_carro_de_compras.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra} entity.
 */
public class CarroCompraDTO implements Serializable {

    private Long id;

    private String cliente;

    private Float total;

    private Boolean finalizado;

    private Boolean vendido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarroCompraDTO)) {
            return false;
        }

        CarroCompraDTO carroCompraDTO = (CarroCompraDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, carroCompraDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarroCompraDTO{" +
            "id=" + getId() +
            ", cliente='" + getCliente() + "'" +
            ", total=" + getTotal() +
            ", finalizado='" + getFinalizado() + "'" +
            ", vendido='" + getVendido() + "'" +
            "}";
    }
}
