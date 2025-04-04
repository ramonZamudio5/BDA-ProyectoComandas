/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Producto;
import entidades.ProductoIngrediente;
import enums.Tipo;
import excepciones.ActualizarProductoException;
import excepciones.AgregarProductoException;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import excepciones.ProductoNoEncontradoException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IProductoDAO {
    public Producto agregarProducto(Producto producto)throws AgregarProductoException;
    public Producto obtenerProducto(Long id)throws BuscarProductoException;
    public List<Producto> buscarPorNombre(String nombreProducto)throws BuscarProductoException;
    public List<Producto> obtenerTodos()throws BuscarProductoException;
    public Producto actualizarProducto(Producto producto)throws ActualizarProductoException;
    public boolean eliminarProducto(long id) throws EliminarProductoException;
    public boolean agregarIngredientes(String nombreProducto, List<ProductoIngrediente> nuevosIngredientes) throws ProductoNoEncontradoException, BuscarProductoException;
    public List<Producto> buscarPorTipo(Tipo tipo)throws BuscarProductoException;
    public boolean eliminarProductoPorNombre(String nombreProducto) throws EliminarProductoException;
}
