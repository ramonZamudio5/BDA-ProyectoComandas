/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.Producto;

/**
 *
 * @author Ramón Zamudio
 */
public class DetalleComandaDTO {
     private Long id;
    
     private Producto producto;
     
     private int cantidad;
     
     private double precioUnitario;
     
     private double importe;
     
     private String notas;

    public DetalleComandaDTO() {
    }

    public DetalleComandaDTO(Long id, Producto producto, int cantidad, double precioUnitario, double importe, String notas) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.notas = notas;
    }

    public DetalleComandaDTO(Producto producto, int cantidad, double precioUnitario, double importe, String notas) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.notas = notas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "DetalleComandaDTO{" + "id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", importe=" + importe + ", notas=" + notas + '}';
    }
     
     
     
     
}
