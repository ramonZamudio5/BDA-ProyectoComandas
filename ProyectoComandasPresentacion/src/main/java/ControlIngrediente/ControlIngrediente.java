/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlIngrediente;

import Interfaces.BuscarIngrediente;
import Interfaces.AgregarIngrediente;
import dtos.IngredienteDTO;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.NegocioException;
import interfaces.IManejadorObjetoNegocio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ControlIngrediente {
    
    
     IManejadorObjetoNegocio manejador;
    
    public ControlIngrediente(IManejadorObjetoNegocio manejador){
        this.manejador= manejador;
        
    }
    
    public void openFormBuscarIngrediente(){
        new BuscarIngrediente(this).setVisible(true);
    }
    
    public void openFormAgregarIngrediente(){
        new AgregarIngrediente(this).setVisible(true);
    }
    
    
     public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException, AgregarIngredienteException{
         try{
             IngredienteDTO ingredienteRegistrado= manejador.agregarIngrediente(ingredienteDTO);
             JOptionPane.showMessageDialog(null, "Ingrediente guardado correctamente");
             return ingredienteRegistrado;
         } catch(AgregarIngredienteException ex){
             Logger.getLogger(ControlIngrediente.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Error al guardar ingrediente: " +ex.getMessage(),
                     "ERROR", JOptionPane.WARNING_MESSAGE);
         } catch(NegocioException ex){
             Logger.getLogger(ControlIngrediente.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Error de negocio al guardar ingrediente: " + ex.getMessage(),
                "ERROR", JOptionPane.WARNING_MESSAGE);

         }
         return new IngredienteDTO();
     }
     
     public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException{
         try{
             return manejador.buscarPorNombre(nombre);
         } catch (Exception e){
             throw new BuscarPorNombreException("Error al buscar ingrediente");
         }
     }
     
      public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException, BuscarPorMedidaException{
          return manejador.buscarPorMedida(medida);
      }
     
     
}
