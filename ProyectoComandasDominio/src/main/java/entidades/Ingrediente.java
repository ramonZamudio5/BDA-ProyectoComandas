/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ramón Zamudio
 */
@Entity
@Table(name = "ingredientes", uniqueConstraints= {@UniqueConstraint(columnNames= {"nombre", "unidadMedida"})})
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @Column(nullable = false)
    private Double stock;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private UnidadMedida unidadMedida;

    public Ingrediente() {
    }

    public Ingrediente(String nombre, Double cantidadDisponible) {
        this.nombre = nombre;
        this.stock = cantidadDisponible;
    }

    public Ingrediente(String nombre, Double stock, UnidadMedida unidadMedida) {
        this.nombre = nombre;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
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

    public Double getstock() {
        return stock;
    }

    public void setCantidadDisponible(Double stock) {
        this.stock = stock;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", unidadMedida=" + unidadMedida + '}';
    }
    
    
    


    
    
}
