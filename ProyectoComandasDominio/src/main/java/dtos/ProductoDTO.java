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
    private String nombre;
    private Double precio;
    private Tipo tipoProducto;
    private List<ProductoIngrediente> ingredientes;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, Double precio, Tipo tipoProducto, List<ProductoIngrediente> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.ingredientes = ingredientes;
    }

    public ProductoDTO(String nombre, Double precio, Tipo tipoProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
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

    @Override
    public String toString() {
        return "AgregarProductoDTO{" + "nombre=" + nombre + ", precio=" + precio + ", tipoProducto=" + tipoProducto + ", ingredientes=" + ingredientes + '}';
    }
    
}
