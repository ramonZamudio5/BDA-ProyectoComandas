/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorObjetoNegocio;

import bos.IngredienteBO;
import bos.ProductoBO;
import daos.IngredienteDAO;
import dtos.IngredienteDTO;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.NegocioException;
import interfaces.IIngredienteBO;
import interfaces.IIngredienteDAO;
import interfaces.IManejadorObjetoNegocio;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ManejadorObjetoNegocio implements IManejadorObjetoNegocio {
    
    private final IIngredienteBO ingredienteBO;
    
    
    public ManejadorObjetoNegocio(){
        IngredienteDAO ingredienteDAO= IngredienteDAO.getInstance();
        this.ingredienteBO= new IngredienteBO(ingredienteDAO);
    }
    
     public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException{
         return ingredienteBO.buscarPorNombre(nombre);
     }
    
     public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException, BuscarPorMedidaException{
         return ingredienteBO.buscarPorMedida(medida);
     }
             
             
     public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException, AgregarIngredienteException{
         return ingredienteBO.agregarIngrediente(ingredienteDTO);
     }        
             
    
}
