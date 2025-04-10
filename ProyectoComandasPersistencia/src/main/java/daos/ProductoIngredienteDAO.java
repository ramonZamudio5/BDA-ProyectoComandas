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
    /**
     * metodo que obtiene la instancia de clase 
     * @return regresa la instancia de la clase
     */
    public static ProductoIngredienteDAO getInstance(){
        if(productoIngredienteDAO == null){
            productoIngredienteDAO = new ProductoIngredienteDAO();
        }
        return productoIngredienteDAO;
    }
    /** 
     * @param productoIngrediente metodo que agrega un productoingrediente
     * @return regresa productoIngrediente persistido
     * @throws AgregarProductoIngredienteException 
     */
    @Override
    public ProductoIngrediente agregarProductoIngrediente(ProductoIngrediente productoIngrediente) throws AgregarProductoIngredienteException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Ingrediente ingredienteExistente = em.find(Ingrediente.class, productoIngrediente.getIngrediente().getId());

            if (ingredienteExistente == null) {
//                IngredienteDAO ingredienteDAO = new IngredienteDAO();
//                Ingrediente ingredientePersistido = ingredienteDAO.agregarIngrediente(productoIngrediente.getIngrediente());
//                productoIngrediente.setIngrediente(ingredientePersistido);
            } else {
                productoIngrediente.setIngrediente(ingredienteExistente);
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
    /**
     * metodo que elimina un productoIngrediente de la base de datos
     * @param id id del productoIngrediente
     * @return regresa el estado de la transaccion
     * @throws EliminarProductoIngredienteException 
     */
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
    /**
     * metodo que elimina varios productosIngredientes
     * @param listaProductos lista de productos que se desean eliminar
     * @return regresa el estado de la transaccion
     * @throws EliminarProductoIngredienteException 
     */
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
    /**
     * metodo que revisa la existencia de un producto
     * @param productoId id del producto
     * @param ingredienteId id del productoIngrediente
     * @return regresa el resultado de la lista, true si esta no vacia false si esta vacia
     */
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
    

