/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Producto;
import entidades.ProductoIngrediente;
import excepciones.ActualizarProductoException;
import excepciones.AgregarProductoException;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoDAO implements IProductoDAO{
    
    public static ProductoDAO productoDAO;

    public ProductoDAO() {
    }
    
    public static ProductoDAO getInstance(){
        if(productoDAO == null){
            productoDAO = new ProductoDAO();
        }
        return productoDAO;
    }
    
    @Override
    public Producto agregarProducto(Producto producto) throws AgregarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            if (producto.getIngredientes() == null) {
            producto.setIngredientes(new ArrayList<>());
            }
                for (ProductoIngrediente pi : producto.getIngredientes()) {
                    if (pi.getIngrediente().getId() == null) {
                        em.persist(pi.getIngrediente());
                    }
            }
            em.persist(producto);
            em.getTransaction().commit();

            if (producto.getId() == null) {
                throw new AgregarProductoException("No se generó ID para el producto.");
            }

            return producto;
        }catch (Exception e) {
            em.getTransaction().rollback();
            throw new AgregarProductoException("Error al agregar producto: " + e.getMessage());
        }finally {
            em.close();
        }
    }
    
    public Producto obtenerProducto(long id)throws BuscarProductoException{
        EntityManager em = Conexion.crearConexion();
        try{
            return em.find(Producto.class, id);
        }catch(Exception e){
            throw new BuscarProductoException("Error al buscar productos");
        }finally{
            em.close();
        }
    }
    
    public List<Producto> buscarPorNombre(String nombreProducto)throws BuscarProductoException{
        EntityManager em = Conexion.crearConexion();
        try{
            Query query = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombreProducto");
            query.setParameter("nombreProducto", "%" + nombreProducto + "%");
            List<Producto> productos = query.getResultList();
            return productos;
        }catch(Exception e){
            throw new BuscarProductoException("Error al buscar productos");
        }finally{
            em.close();
        }
    }
    
    public List<Producto> obtenerTodos()throws BuscarProductoException{
        EntityManager em = Conexion.crearConexion();
        try{
            return em.createQuery("SELECT p FROM Producto p").getResultList();
        }catch(Exception e){
            throw new BuscarProductoException("Error al buscar productos");
        }finally{
            em.close();
        }
    } 
    
    public Producto actualizarProducto(Producto producto)throws ActualizarProductoException{
        EntityManager em = Conexion.crearConexion();
        try{
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
            return producto;
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new ActualizarProductoException("Error al actualizar el producto");
        }finally{
            em.close();
        }
    }
    
    public boolean eliminarProducto(long id) throws EliminarProductoException{
        EntityManager em = Conexion.crearConexion();
        try{
            em.getTransaction().begin();
            Producto productoEliminar = em.find(Producto.class, id);
            if (productoEliminar == null) {
            em.getTransaction().rollback();
            throw new EliminarProductoException("No se encontró el producto con ID: " + id);
            }
            em.remove(productoEliminar);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new EliminarProductoException("Error al eliminar el producto");
        }finally{
            em.close();
        }
    }
    
     
    
    
}
