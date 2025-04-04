/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.IngredienteDTO;
import entidades.Ingrediente;
import enums.UnidadMedida;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class IngredienteMapper {
    
    public static IngredienteDTO toDTO(Ingrediente ingrediente){
        if(ingrediente==null){
            return null;
        }
        return new IngredienteDTO(
                ingrediente.getNombre(),
                ingrediente.getStock(),
                ingrediente.getUnidadMedida(),
                ingrediente.getFoto()
        );
        
    }
    
    public static List<IngredienteDTO> toListDTO(List<Ingrediente> ingredientes) {
        if (ingredientes == null || ingredientes.isEmpty()) {
            return null;
        }
        return ingredientes.stream()
                .map(IngredienteMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public static Ingrediente toEntity(IngredienteDTO ingredienteDTO){
        if (ingredienteDTO==null){
            return null;
        } return new Ingrediente(
                ingredienteDTO.getNombre(), 
                ingredienteDTO.getStock(), 
                ingredienteDTO.getUnidadMedida(),
                ingredienteDTO.getFoto());
        
    }
    
    
    
    
}
