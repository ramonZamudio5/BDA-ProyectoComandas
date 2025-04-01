/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Producto;
import excepciones.AgregarProductoException;
import interfaces.IProductoDAO;
import javax.persistence.EntityManager;

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
    
    public Producto obtenerProducto(long id)throws AgregarProductoException{
        EntityManager em = Conexion.crearConexion();
        try{
            return em.find(Producto.class, id);
        }catch(Exception e){
            throw new AgregarProductoException("error al buscar el producto");
        }finally{
            em.close();
        }
    }
    
    
}
