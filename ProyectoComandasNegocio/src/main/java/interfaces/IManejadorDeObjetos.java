/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import bos.ProductoBO;
import daos.ProductoDAO;
import dtos.ProductoDTO;
import excepciones.BuscarProductoException;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IManejadorDeObjetos {
    
    public abstract List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException;
    
    public abstract List<ProductoDTO> obtenerPorTipo(String tipo) throws NegocioException, BuscarProductoException;
    
    public ProductoDTO obtenerProducto(Long id) throws NegocioException, BuscarProductoException, BuscarProductoException;
    
    public ProductoDTO actualizarProducto(ProductoDTO producto) throws NegocioException;
    
    public boolean eliminarProducto(String nombreProducto) throws NegocioException, BuscarProductoException;
}
