/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectocomandaspersistencia;

import daos.IngredienteDAO;
import daos.MesaDAO;
import static daos.MesaDAO.mesaDAO;
import daos.ProductoDAO;
import entidades.Ingrediente;
import entidades.Mesa;
import entidades.Producto;
import entidades.ProductoIngrediente;
import enums.Tipo;
import enums.UnidadMedida;
import excepciones.AgregarIngredienteException;
import excepciones.AgregarProductoException;
import excepciones.BuscarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.InsercionMasivaException;
import excepciones.ObtenerMesasDispException;
import static java.util.Collections.list;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ProyectoComandasPersistencia {

    public static void main(String[] args) throws InsercionMasivaException, ObtenerMesasDispException, BuscarIngredienteException  {
        
        
        IngredienteDAO ingredienteDAO= IngredienteDAO.getInstance();
        MesaDAO mesaDAO= MesaDAO.getInstance();
        
        
        Ingrediente ingrediente= new Ingrediente("Lechuga", 5.0, UnidadMedida.PIEZA);
        try{
            Ingrediente ingredienteAgregado= ingredienteDAO.agregarIngrediente(ingrediente);
            System.out.println("Se ha agregado el ingrediente");
        } catch(AgregarIngredienteException e){
            System.out.println("Error al agregar ingrediente" +e.getMessage());
            
        }
        
        
        
        try{
            List<Ingrediente> resultados= ingredienteDAO.buscarPorNombre("Lechuga");
            for (Ingrediente i:resultados){
                System.out.println(i.getNombre());
            }
        } catch(BuscarPorNombreException e){
            System.out.println("Error al buscar por nombre" +e.getMessage());
            
        }
    
        
        try{
            List<Ingrediente> resultadosMedida= ingredienteDAO.buscarPorMedida("Pieza");
            for(Ingrediente i: resultadosMedida){
                System.out.println(i.getNombre());
            }
        } catch (BuscarPorMedidaException e){
                    System.out.println("Error al buscar por medida " +e.getMessage());
          }
        
        
        try{
          boolean resultado= mesaDAO.insercionMasiva();
          if(resultado){
              System.out.println("bien");
          } else{
              System.out.println("mal");
          }
        }catch(InsercionMasivaException e){
            System.out.println("error "+e.getMessage());
        }
        
        try{
            List<Mesa> mesasDisp= mesaDAO.obtenerMesasDisponibles();
             for(Mesa mesa: mesasDisp){
                 System.out.println(mesa);
             }
        } catch(ObtenerMesasDispException e){
            System.out.println("error "+e.getMessage());
          
        }
        
        List<Ingrediente> ingredientesEncontrados= ingredienteDAO.buscarIngrediente("Lechuga", null);
        
    }
}


        
        
    
     
       
        
    

