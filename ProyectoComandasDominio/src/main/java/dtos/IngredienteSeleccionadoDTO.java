/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class IngredienteSeleccionadoDTO {
    private Long id;
    private IngredienteDTO ingrediente;
    private Double cantidad;

    public IngredienteSeleccionadoDTO(IngredienteDTO ingrediente, Double cantidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public IngredienteSeleccionadoDTO(Long id, IngredienteDTO ingrediente, Double cantidad) {
        this.id = id;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public IngredienteDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTO ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "IngredienteSeleccionadoDTO{" + "ingrediente=" + ingrediente + ", cantidad=" + cantidad + '}';
    }
    
    
    
    
}
