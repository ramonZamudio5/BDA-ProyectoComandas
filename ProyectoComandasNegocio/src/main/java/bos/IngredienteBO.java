/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.BuscarPorNombreException;
import excepciones.NegocioException;
import interfaces.IIngredienteBO;
import interfaces.IIngredienteDAO;
import java.util.List;
import mappers.IngredienteMapper;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class IngredienteBO implements IIngredienteBO {
    
    private IIngredienteDAO ingredienteDAO;
    
    public IngredienteBO(IIngredienteDAO ingredienteDAO){
        this.ingredienteDAO= ingredienteDAO;
    }
    
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException{
        if (nombre==null || nombre.trim().isEmpty()){
            throw new BuscarPorNombreException("Ingredientes con el nombre "+nombre+ " no encontrados");
        }
        try{
            List<Ingrediente> ingredientes= ingredienteDAO.buscarPorNombre(nombre);
            if (ingredientes==null || ingredientes.isEmpty()){
                throw new BuscarPorNombreException("Ingredientes no encontrados con el nombre "+ nombre);
            }
            for(Ingrediente ingrediente: ingredientes){
                if(ingrediente.getId()==null){
                    throw new NegocioException("El ingrediente no puede ser nulo");
                }
            }
            return IngredienteMapper.toListDTO(ingredientes);
        } catch(Exception e){
            throw new NegocioException("Error al buscar ingrediente por nombre");
            
        }
    }
    
    
    
    
    
}
