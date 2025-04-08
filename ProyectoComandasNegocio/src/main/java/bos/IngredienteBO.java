/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorNombreException;
import excepciones.BuscarPorMedidaException;
import excepciones.NegocioException;
import interfaces.IIngredienteBO;
import interfaces.IIngredienteDAO;
import java.util.List;
import mappers.IngredienteMapper;
import excepciones.BuscarIngredienteException;
import excepciones.ActualizarStockException;


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
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException{
        if (nombre==null || nombre.trim().isEmpty()){
            throw new NegocioException("Ingredientes con el nombre "+nombre+ " no encontrados");
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
    
    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException{
        if(medida==null || medida.trim().isEmpty()){
            throw new NegocioException("Ingredientes con unidad de medida "+medida+ " no encontrados");
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
    
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException{
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
            throw new NegocioException("Error al agregar ingrediente" +e.getMessage(), e );
            
        } catch(Exception e){
            throw new NegocioException("Error al agregar ingrediente" + e.getMessage());
        }
    }

    
    public IngredienteDTO buscarPorNombreUnico(String nombreIngrediente) throws NegocioException {
        if (nombreIngrediente == null || nombreIngrediente.trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede ser nulo o vacío.");
        }
        try{
            return IngredienteMapper.toDTO(ingredienteDAO.buscarPorNombreUnico(nombreIngrediente));
        }catch(Exception e){
            throw new NegocioException("Error al buscar el ingrediente");
        }
    }
    
    public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws NegocioException{
        if(idIngrediente==null){
            throw new NegocioException("El ID del ingrediente no puede ser nulo");
        }
        if(stock<0){
            throw new NegocioException("El nuevo stock no puede ser menor a 0");
        }
        try{
            Ingrediente ingredienteActualizado= ingredienteDAO.actualizarStock(idIngrediente, stock);
            //checar el mapper
            return IngredienteMapper.toDTO(ingredienteActualizado);
        } catch(Exception e){
            throw new NegocioException("Error al actualizar stock"+e.getMessage());
        }
    }
    
    public List<IngredienteDTO> buscarIngredientes(String nombre, String medida) throws NegocioException{
        if((nombre==null || nombre.trim().isEmpty()) && (medida==null || medida.trim().isEmpty())){
            throw new NegocioException("Se debe agregar minimo un criterio de busqueda");
        } try{
            
            List<Ingrediente> ingredientesEncontrados= ingredienteDAO.buscarIngrediente(nombre, medida);
            if(ingredientesEncontrados==null || ingredientesEncontrados.isEmpty()){
                throw new NegocioException("Ingredientes no encotrados con los filtros aplicados");
            } return IngredienteMapper.toListDTO(ingredientesEncontrados);
            
        } catch(Exception e){
            throw new NegocioException("Error en la busqueda de ingredientes "+e.getMessage());
        }
    }
    
    
    
    
}
