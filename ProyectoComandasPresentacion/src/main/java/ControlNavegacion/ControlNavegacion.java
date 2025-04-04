/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlNavegacion;

import Interfaces.AgregarProductoFrame;
import Interfaces.BuscadorDeProductosFrame;
import Interfaces.EditarProductoFrame;
import Interfaces.EliminarProducto;
import Interfaces.PantallaAdministrador;
import Interfaces.PantallaMeseroComandas;
import Interfaces.PantallaPrincipalRol;
import Interfaces.SeleccionarOpccionProductos;
import dtos.ProductoDTO;
import excepciones.ActualizarProductoException;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import excepciones.NegocioException;
import interfaces.IManejadorDeObjetos;
import java.util.List;
import javax.swing.JOptionPane;

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
    public void openFormBuscadorProductosFrame(){
        new BuscadorDeProductosFrame(this).setVisible(true);
    }
    public void openFormEditarProdutoFrame(ProductoDTO producto){
        new EditarProductoFrame(this,producto).setVisible(true);
    }
    public void openFormEliminarProductoFrame(){
        new EliminarProducto(this).setVisible(true);
    }
    public void openFormPantallaPrincipalRol(){
        new PantallaPrincipalRol(this).setVisible(true);
    }
    public void openFormPantallaMeseroComandas(){
        new PantallaMeseroComandas(this).setVisible(true);
    }
    public void openFormPantallaAdministrador(){
        new PantallaAdministrador(this).setVisible(true);
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
    
    public ProductoDTO obtenerProducto(Long id) throws BuscarProductoException{
        try{
            return manejador.obtenerProducto(id);
        }catch(Exception e){
            throw new BuscarProductoException("Error al buscar el producto", e);
        }
    }
    
    public ProductoDTO actualiarProduto(ProductoDTO producto) throws NegocioException, ActualizarProductoException{
        try{
            return manejador.actualizarProducto(producto);
        }catch(Exception e){
            throw new ActualizarProductoException("Error al actualizar el producto");
        }
    }

    public void MostrarMensajeParaEliminar(String mensaje,String nombreProducto) {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            try {
                boolean eliminado = eliminarProducto(nombreProducto);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
        }
    }
    
    public boolean eliminarProducto(String nombreProducto) throws NegocioException, BuscarProductoException, EliminarProductoException{
        try{
            return manejador.eliminarProducto(nombreProducto);
        }catch(Exception e){
            throw new EliminarProductoException("Error al actualizar el producto");
        }
    }
}
