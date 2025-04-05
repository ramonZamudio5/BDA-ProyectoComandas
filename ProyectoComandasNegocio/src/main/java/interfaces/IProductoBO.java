/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import dtos.ProductoDTO;
import entidades.Producto;
import enums.Tipo;
import excepciones.BuscarProductoException;
import excepciones.NegocioException;
import java.util.List;
import mappers.ProductoMapper;

/**
 *
 * @author Ramón Zamudio
 */
public interface IProductoBO {
    public ProductoDTO agregarProducto(ProductoDTO productoDTO)throws NegocioException;
    public ProductoDTO obtenerProducto(Long id)throws NegocioException, BuscarProductoException;
    public List<ProductoDTO> obtenerPorNombre(String nombre)throws NegocioException;
    public List<ProductoDTO> obtenerTodos()throws NegocioException;
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO)throws NegocioException;
    public boolean eliminarProducto(Long id)throws NegocioException, BuscarProductoException;
    public List<ProductoDTO> buscarPorTipo(Tipo tipo)throws BuscarProductoException;
}
