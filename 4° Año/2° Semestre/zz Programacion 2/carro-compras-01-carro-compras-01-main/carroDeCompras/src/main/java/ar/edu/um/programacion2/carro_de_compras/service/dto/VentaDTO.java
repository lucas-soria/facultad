package ar.edu.um.programacion2.carro_de_compras.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link ar.edu.um.programacion2.carro_de_compras.domain.Venta} entity.
 */
public class VentaDTO implements Serializable {

    private Long id;

    private LocalDate fecha;

    private Float total;

    private CarroCompraDTO carro;

    private UserDTO cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public CarroCompraDTO getCarro() {
        return carro;
    }

    public void setCarro(CarroCompraDTO carro) {
        this.carro = carro;
    }

    public UserDTO getCliente() {
        return cliente;
    }

    public void setCliente(UserDTO cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VentaDTO)) {
            return false;
        }

        VentaDTO ventaDTO = (VentaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ventaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VentaDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", total=" + getTotal() +
            ", carro=" + getCarro() +
            ", cliente=" + getCliente() +
            "}";
    }
}
