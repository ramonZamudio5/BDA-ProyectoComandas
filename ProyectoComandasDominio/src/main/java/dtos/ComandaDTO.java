/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.Cliente;
import entidades.DetalleComanda;
import entidades.Mesa;
import enums.EstadoComanda;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ComandaDTO {
    private String folio;
    private LocalDateTime fechaHoraCreacion;
    private Double totalVenta;
    private EstadoComanda estado;
    private Cliente cliente; 
    private List<DetalleComanda> detalles;
    private Mesa mesa;

    public ComandaDTO() {
    }
    
    public ComandaDTO(String folio, LocalDateTime fechaHoraCreacion, Double totalVenta, EstadoComanda estado, Cliente cliente, List<DetalleComanda> detalles, Mesa mesa) {
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.cliente = cliente;
        this.detalles = detalles;
        this.mesa = mesa;
    }

    public ComandaDTO(String folio, LocalDateTime fechaHoraCreacion, Double totalVenta, EstadoComanda estado, Cliente cliente, Mesa mesa) {
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "ComandaDTO{" + "folio=" + folio + ", fechaHoraCreacion=" + fechaHoraCreacion + ", totalVenta=" + totalVenta + ", estado=" + estado + ", cliente=" + cliente + ", detalles=" + detalles + ", mesa=" + mesa + '}';
    }

    

    
}
