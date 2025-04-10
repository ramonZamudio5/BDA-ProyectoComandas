/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoComanda;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Cricri
 */
@Entity
public class Comanda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String folio;
    @Column(nullable = false)
    private LocalDateTime fechaHoraCreacion;
    @Column(nullable = false)
    private Double totalVenta;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoComanda estado;
    @ManyToOne(optional=true)
    private Cliente cliente; 
    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;
    @OneToMany(mappedBy = "comanda", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)//cambiar cascada
    private List<DetalleComanda> detalles;

    public Comanda() {
    }

    public Comanda(String folio, LocalDateTime fechaHoraCreacion, Double totalVenta, EstadoComanda estado, Cliente cliente, Mesa mesa, List<DetalleComanda> detalles) {
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.cliente = cliente;
        this.mesa = mesa;
        this.detalles = detalles;
    }

    public Comanda( String folio, LocalDateTime fechaHoraCreacion, Double totalVenta, EstadoComanda estado, Mesa mesa, List<DetalleComanda> detalles) {
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.mesa = mesa;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", folio=" + folio + ", fechaHoraCreacion=" + fechaHoraCreacion + ", totalVenta=" + totalVenta + ", estado=" + estado + ", cliente=" + cliente + ", mesa=" + mesa + ", detalles=" + (detalles != null ? detalles.size() : 0) + " detalles}";
    }

   
    

}
