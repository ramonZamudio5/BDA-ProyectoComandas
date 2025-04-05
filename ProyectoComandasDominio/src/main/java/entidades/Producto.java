/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.Tipo;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ramón Zamudio
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private Double precio;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipoProducto;
    @OneToMany(mappedBy = "producto", cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE}, orphanRemoval = true)
    private List<ProductoIngrediente> ingredientes = new ArrayList<>();
    @Column(nullable = false)
    private boolean estado;

    public Producto() {
    }

    public Producto(String nombre, Double precio, Tipo tipoProducto, boolean estado) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.estado = estado;
    }

    public Producto(String nombre, Double precio, boolean estado) {
        this.nombre = nombre;
        this.precio = precio;
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
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", tipoProducto=" + tipoProducto + '}';
    }

    

    
    
    
    
}
