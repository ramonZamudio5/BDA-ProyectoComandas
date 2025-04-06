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
    
    private IngredienteDTO ingrediente;
    private int cantidad;

    public IngredienteSeleccionadoDTO(IngredienteDTO ingrediente, int cantidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public IngredienteDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTO ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "IngredienteSeleccionadoDTO{" + "ingrediente=" + ingrediente + ", cantidad=" + cantidad + '}';
    }
    
    
    
    
}
