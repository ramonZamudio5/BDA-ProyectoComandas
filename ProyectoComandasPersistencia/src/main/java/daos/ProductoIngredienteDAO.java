/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Ingrediente;
import entidades.ProductoIngrediente;
import excepciones.AgregarProductoIngredienteException;
import excepciones.EliminarProductoIngredienteException;
import interfaces.IProductoIngrediente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoIngredienteDAO implements IProductoIngrediente{
    public static ProductoIngredienteDAO productoIngredienteDAO;

    public ProductoIngredienteDAO() {
    }
    
    public static ProductoIngredienteDAO getInstance(){
        if(productoIngredienteDAO == null){
            productoIngredienteDAO = new ProductoIngredienteDAO();
        }
        return productoIngredienteDAO;
    }
    @Override
    public ProductoIngrediente agregarProductoIngrediente(ProductoIngrediente productoIngrediente) throws AgregarProductoIngredienteException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            if (productoIngrediente.getIngrediente().getId() == null) {
                IngredienteDAO ingredienteDAO = new IngredienteDAO();
                Ingrediente ingredientePersistido = ingredienteDAO.agregarIngrediente(productoIngrediente.getIngrediente());
                productoIngrediente.setIngrediente(ingredientePersistido);
            }
            em.persist(productoIngrediente);
            em.getTransaction().commit();
            return productoIngrediente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AgregarProductoIngredienteException("Error al agregar ProductoIngrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    @Override
    public boolean eliminarProductoIngrediente(Long id)throws EliminarProductoIngredienteException{
        EntityManager em = Conexion.crearConexion();
        try{
            ProductoIngrediente productoEliminar = em.find(ProductoIngrediente.class, id);
            if(productoEliminar == null){
                throw new EliminarProductoIngredienteException("El producto con el id: "+id+" no existe");
            }
            em.getTransaction().begin();
            em.remove(productoEliminar);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new EliminarProductoIngredienteException("Error al eliminar el productoIngrediente");
        }finally{
            em.close();
        }
    }
    
    @Override
    public boolean eliminarVariosProductosIngredientes(List<ProductoIngrediente> listaProductos) throws EliminarProductoIngredienteException{
        EntityManager em = Conexion.crearConexion();
        try{
            for(ProductoIngrediente productoIngrediente : listaProductos){
               ProductoIngrediente productoEliminar = em.find(ProductoIngrediente.class, productoIngrediente.getId());
               if(productoEliminar == null){
                   throw new EliminarProductoIngredienteException("El producto con el id: "+productoIngrediente.getId()+" no existe");
               }
               em.getTransaction().begin();
               em.remove(productoEliminar);
            }
            em.getTransaction().commit();   
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new EliminarProductoIngredienteException("Error al eliminar el productoIngrediente");
        }finally{
            em.close();
        }
    }
    
    @Override
    public boolean existeIngredienteEnProducto(Long productoId, Long ingredienteId) {
    EntityManager em = Conexion.crearConexion();
    try {
        Query query = em.createQuery("SELECT pi FROM ProductoIngrediente pi WHERE pi.producto.id = :productoId AND pi.ingrediente.id = :ingredienteId");
        query.setParameter("productoId", productoId);
        query.setParameter("ingredienteId", ingredienteId);
        
        List<ProductoIngrediente> resultado = query.getResultList();
        return !resultado.isEmpty(); 
    } finally {
        em.close();
    }
}
}
    

