/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import bos.ProductoBO;
import daos.ProductoDAO;
import dtos.IngredienteDTO;
import dtos.ProductoDTO;
import enums.Tipo;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.BuscarProductoException;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IManejadorDeObjetos {
    
    public abstract List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException;
    
    public abstract List<ProductoDTO> obtenerPorTipo(Tipo tipo) throws NegocioException, BuscarProductoException;
    
    public ProductoDTO obtenerProducto(Long id) throws NegocioException, BuscarProductoException, BuscarProductoException;
    
    public ProductoDTO actualizarProducto(ProductoDTO producto) throws NegocioException;
    
    public boolean eliminarProducto(String nombreProducto) throws NegocioException, BuscarProductoException;
    
     public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException;
     
    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException, BuscarPorMedidaException;
    
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException, AgregarIngredienteException;
}
