package ar.edu.um.programacion2.carro_de_compras.repository;

import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoCompradoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.VentaDTO;

import java.time.LocalDate;

public class CustomMapper {

    public static ProductoCompradoDTO fromSeleccionadoToComprado(ProductoSeleccionadoDTO productoSeleccionadoDTO){
        ProductoCompradoDTO productoCompradoDTO = new ProductoCompradoDTO();
        productoCompradoDTO.setProducto(productoSeleccionadoDTO.getProducto());
        productoCompradoDTO.setCantidad(productoSeleccionadoDTO.getCantidad());
        productoCompradoDTO.setPrecio(productoSeleccionadoDTO.getPrecio());
        return productoCompradoDTO;
    }

    public static VentaDTO fromCarroToVenta(CarroCompraDTO carroCompraDTO){
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setCliente(carroCompraDTO.getCliente());
        ventaDTO.setTotal(carroCompraDTO.getTotal());
        ventaDTO.setCarro(carroCompraDTO);
        ventaDTO.setFecha(LocalDate.now());
        return ventaDTO;
    }

}
