/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.ProductoIngrediente;
import enums.Tipo;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Tipo tipoProducto;
    private List<ProductoIngrediente> ingredientes;
    private boolean estado;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, Double precio, Tipo tipoProducto, List<ProductoIngrediente> ingredientes, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.ingredientes = ingredientes;
        this.estado = estado;
    }

    public ProductoDTO(String nombre, Double precio, Tipo tipoProducto, List<ProductoIngrediente> ingredientes, boolean estado) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.ingredientes = ingredientes;
        this.estado = estado;
    }

    public ProductoDTO(String nombre, Double precio, Tipo tipoProducto, boolean estado) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Tipo getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(Tipo tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public List<ProductoIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<ProductoIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    @Override
    public String toString() {
        return "AgregarProductoDTO{" + "nombre=" + nombre + ", precio=" + precio + ", tipoProducto=" + tipoProducto + ", ingredientes=" + ingredientes + '}';
    }
    
}
