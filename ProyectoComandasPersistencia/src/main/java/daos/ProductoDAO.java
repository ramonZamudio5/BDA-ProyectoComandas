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
import interfaces.IProductoDAO;
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
        try{
            em.getTransaction().begin();
            em.persist(producto);
            if (producto.getIngredientes() != null) {
                for (ProductoIngrediente pi : producto.getIngredientes()) {
                    pi.setProducto(producto); // Asegurar la relación
                    em.persist(pi);
                }
            }
            em.getTransaction().commit();
            if(producto.getId()==null){
                throw new AgregarProductoException("No se genero ID");
            }
            return producto;
        }catch(AgregarProductoException e){
            em.getTransaction().rollback();
            throw new AgregarProductoException("Error al agregar producto");
        }
        finally{
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
    
     
    
    
}
