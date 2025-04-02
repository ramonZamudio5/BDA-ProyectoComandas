/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.UnidadMedida;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class IngredienteDTO {
    private String nombre;
    Double stock;
    private UnidadMedida unidadMedida;

    public IngredienteDTO() {
    }

    public IngredienteDTO(String nombre, Double stock, UnidadMedida unidadMedida) {
        this.nombre = nombre;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "IngredienteDTO{" + "nombre=" + nombre + ", stock=" + stock + ", unidadMedida=" + unidadMedida + '}';
    }
    
    
    
}
