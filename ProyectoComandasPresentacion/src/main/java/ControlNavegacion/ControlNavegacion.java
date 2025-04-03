/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlNavegacion;

import Interfaces.AgregarProductoFrame;
import Interfaces.BuscadorDeProductosFrame;
import Interfaces.SeleccionarOpccionProductos;
import dtos.ProductoDTO;
import excepciones.BuscarProductoException;
import excepciones.NegocioException;
import interfaces.IManejadorDeObjetos;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ControlNavegacion {
    IManejadorDeObjetos manejador;

    public ControlNavegacion(IManejadorDeObjetos manejador) {
        this.manejador = manejador;
    }
    
    public void openFormSeleccionarOpccionProducto(){
        new SeleccionarOpccionProductos(this).setVisible(true);
    }
    public void openFormAgregarProductoFrame(){
        new AgregarProductoFrame(this).setVisible(true);
    }
    public void openBuscadorProductosFrame(){
        new BuscadorDeProductosFrame(this).setVisible(true);
    }
    
    public List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException, BuscarProductoException{
        try{
            return manejador.obtenerPorNombre(nombre);
        }catch(Exception e){
            throw new BuscarProductoException("Error al buscar el producto", e);
        }   
    }
    
    public List<ProductoDTO> obtenerPorTipo(String tipo) throws BuscarProductoException{
        try{
            return manejador.obtenerPorTipo(tipo);
        }catch(Exception e){
            throw new BuscarProductoException("Error al buscar el producto", e);
        }   
    }
}
