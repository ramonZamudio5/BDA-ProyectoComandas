/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectocomandaspersistencia;

import daos.IngredienteDAO;
import daos.ProductoDAO;
import entidades.Ingrediente;
import entidades.Producto;
import entidades.ProductoIngrediente;
import entidades.Tipo;
import entidades.UnidadMedida;
import excepciones.AgregarIngredienteException;
import excepciones.AgregarProductoException;
import static java.util.Collections.list;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ProyectoComandasPersistencia {

    public static void main(String[] args)  {
        
        
        IngredienteDAO ingredienteDAO= IngredienteDAO.getInstance();
        Ingrediente ingrediente= new Ingrediente("Lechuga", 5.0, UnidadMedida.PIEZA);
        try{
            Ingrediente ingredienteAgregado= ingredienteDAO.agregarIngrediente(ingrediente);
            System.out.println("Se ha agregado el ingrediente");
        } catch(AgregarIngredienteException e){
            System.out.println("Error al agregar ingrediente" +e.getMessage());
            
        }
        
    }
}
