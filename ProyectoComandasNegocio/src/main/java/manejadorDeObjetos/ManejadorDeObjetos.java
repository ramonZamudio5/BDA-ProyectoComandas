/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorDeObjetos;

import Interfaces.AgregarProductoFrame;
import Interfaces.BuscadorDeProductosFrame;
import Interfaces.SeleccionarOpccionProductos;
import bos.ProductoBO;
import daos.ProductoDAO;
import dtos.ProductoDTO;
import excepciones.BuscarProductoException;
import excepciones.NegocioException;
import interfaces.IManejadorDeObjetos;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ManejadorDeObjetos implements IManejadorDeObjetos{
   
    public List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException{
        ProductoDAO productoDAO = ProductoDAO.getInstance();
        ProductoBO productoBO = new ProductoBO(productoDAO);
        return productoBO.obtenerPorNombre(nombre);
    }
    
    public List<ProductoDTO> obtenerPorTipo(String tipo) throws NegocioException, BuscarProductoException{
        ProductoDAO productoDAO = ProductoDAO.getInstance();
        ProductoBO productoBO = new ProductoBO(productoDAO);
        return productoBO.buscarPorTipo(tipo);
    }
    
    
}
