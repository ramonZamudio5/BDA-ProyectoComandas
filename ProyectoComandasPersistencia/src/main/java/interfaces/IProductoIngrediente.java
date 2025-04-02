/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import conexion.Conexion;
import daos.IngredienteDAO;
import entidades.Ingrediente;
import entidades.ProductoIngrediente;
import excepciones.AgregarProductoIngredienteException;
import excepciones.EliminarProductoIngredienteException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ramón Zamudio
 */
public interface IProductoIngrediente {
    public ProductoIngrediente agregarProductoIngrediente(ProductoIngrediente productoIngrediente) throws AgregarProductoIngredienteException;
    public boolean eliminarProductoIngrediente(Long id)throws EliminarProductoIngredienteException;
    public boolean eliminarVariosProductosIngredientes(List<ProductoIngrediente> listaProductos) throws EliminarProductoIngredienteException;
    public boolean existeIngredienteEnProducto(Long productoId, Long ingredienteId);
}
