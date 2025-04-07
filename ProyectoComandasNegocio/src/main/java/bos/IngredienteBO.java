/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarIngredienteException;
import excepciones.BuscarPorNombreException;
import excepciones.BuscarPorMedidaException;
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
    
    @Override
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException{
        if (nombre==null || nombre.trim().isEmpty()){
            throw new BuscarPorNombreException("Ingredientes con el nombre "+nombre+ " no encontrados");
        }
        try{
            List<Ingrediente> ingredientesPorNombre= ingredienteDAO.buscarPorNombre(nombre);
            if (ingredientesPorNombre==null || ingredientesPorNombre.isEmpty()){
                throw new BuscarPorNombreException("Ingredientes no encontrados con el nombre "+ nombre);
            }
            for(Ingrediente ingrediente: ingredientesPorNombre){
                if(ingrediente.getId()==null){
                    throw new NegocioException("El ingrediente no puede ser nulo");
                }
            }
            return IngredienteMapper.toListDTOConID(ingredientesPorNombre);
        } catch(Exception e){
            throw new NegocioException("Error al buscar ingrediente por nombre "+nombre);
        }
    }
    
    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException, BuscarPorMedidaException{
        if(medida==null || medida.trim().isEmpty()){
            throw new BuscarPorMedidaException("Ingredientes con unidad de medida "+medida+ " no encontrados");
        } try{
            List<Ingrediente> ingredientesPorMedida= ingredienteDAO.buscarPorMedida(medida);
            if(ingredientesPorMedida==null || ingredientesPorMedida.isEmpty()){
                throw new BuscarPorMedidaException("Ingredientes no encontrados con medida "+medida);
            }
            for(Ingrediente ingrediente: ingredientesPorMedida){
                if(ingrediente.getId()==null){
                    throw new NegocioException("El ingrediente no puede ser nulo");
                }
            } return IngredienteMapper.toListDTOConID(ingredientesPorMedida);
            
        } catch(Exception e){
            throw new NegocioException("Error al buscar ingredientes por "+medida);
        }
            
    }
    
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException, AgregarIngredienteException{
        if (ingredienteDTO==null){
            throw new NegocioException("El ingrediente no puede ser nulo");
        }
        if(ingredienteDTO.getNombre()==null || ingredienteDTO.getNombre().trim().isEmpty()){
            throw new NegocioException("El nombre del ingrediente es obligatorio");
        }
        if(ingredienteDTO.getStock()<0 ){
            throw new NegocioException("La cantidad de stock del ingrediente no puede ser negativa");
        }
        if(ingredienteDTO.getUnidadMedida()==null){
            throw new NegocioException("La unidad de medida es obligatoria");
        }
       
        Ingrediente ingredienteAgregar= IngredienteMapper.toEntity(ingredienteDTO);
        try{
            Ingrediente ingredienteGuardado= ingredienteDAO.agregarIngrediente(ingredienteAgregar);
            if (ingredienteGuardado==null){
                throw new NegocioException("Error al agregar ingrediente");
            }
            if (ingredienteGuardado.getId()==null){
                throw new NegocioException("Error al generar ID del ingrediente");
            }
            return IngredienteMapper.toDTO(ingredienteGuardado);
            
        } catch(AgregarIngredienteException e){
            throw new AgregarIngredienteException("Error al agregar ingrediente" +e.getMessage(), e );
            
        } catch(Exception e){
            throw new NegocioException("Error al agregar ingrediente" + e.getMessage());
        }
    }

    @Override
    public IngredienteDTO buscarPorNombreUnico(String nombreIngrediente) throws BuscarIngredienteException {
        if (nombreIngrediente == null || nombreIngrediente.trim().isEmpty()) {
            throw new BuscarIngredienteException("El nombre del ingrediente no puede ser nulo o vacío.");
        }
        try{
            return IngredienteMapper.toDTO(ingredienteDAO.buscarPorNombreUnico(nombreIngrediente));
        }catch(Exception e){
            throw new BuscarIngredienteException("Error al buscar el ingrediente");
        }
    }
    
    
    
    
    
}
