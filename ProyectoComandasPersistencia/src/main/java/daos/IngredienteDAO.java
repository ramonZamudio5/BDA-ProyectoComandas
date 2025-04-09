/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import excepciones.BuscarIngredienteException;
import conexion.Conexion;
import entidades.Ingrediente;
import enums.UnidadMedida;
import excepciones.ActualizarStockException;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.ConvertirTextoAUnidadException;
import excepciones.EliminarIngredienteException;
import interfaces.IIngredienteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class IngredienteDAO implements IIngredienteDAO {

    public static IngredienteDAO ingredienteDAO;

    private IngredienteDAO() {

    }

    public static IngredienteDAO getInstance() {
        if (ingredienteDAO == null) {
             ingredienteDAO = new IngredienteDAO();
        }
        return ingredienteDAO;
    }

    public Ingrediente agregarIngrediente(Ingrediente ingrediente) throws AgregarIngredienteException {

        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre= :nombre AND i.unidadMedida= :unidadMedida");
            query.setParameter("nombre", ingrediente.getNombre());
            query.setParameter("unidadMedida", ingrediente.getUnidadMedida());
            List<Ingrediente> existentes = query.getResultList();

            if (!existentes.isEmpty()) {
                throw new AgregarIngredienteException("El ingrediente ya existe con el mismo nombre y misma unidad de medida");
            }
            em.persist(ingrediente);
            em.getTransaction().commit();
            return ingrediente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AgregarIngredienteException("Error al agregar el ingrediente " + e.getMessage());

        } finally {
            em.close();
        }

    }
    
    @Override
    public List<Ingrediente> buscarPorNombre(String nombre) throws BuscarPorNombreException{
        EntityManager em= Conexion.crearConexion();
        try {
            System.out.println("Buscando ingredientes con nombre: "+nombre);
           List<Ingrediente> ingredientes = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre LIKE :nombre")
                                           .setParameter("nombre", nombre + "%")
                                           .getResultList();
           System.out.println("Ingredientes encontrados: " + ingredientes.size());
          
            if(ingredientes.isEmpty()){
                throw new BuscarPorNombreException("No hay ingredientes registrados con el nombre "+ nombre);
            }
            return ingredientes;
        } catch(Exception e){
            throw new BuscarPorNombreException("Error al buscar ingrediente por nombre" + e.getMessage());
            
        } finally{
            em.close();
        }
    }
    
    public List<Ingrediente> buscarPorMedida(String medida) throws BuscarPorMedidaException{
        EntityManager em= Conexion.crearConexion();
        try{
            UnidadMedida unidadMedida= convertirTextoAUnidad(medida);
            List<Ingrediente> ingredientes= em.createQuery("SELECT i FROM Ingrediente i WHERE i.unidadMedida= :unidadMedida")
                    .setParameter("unidadMedida", unidadMedida)
                    .getResultList();
            
            if(ingredientes.isEmpty()){
                throw new BuscarPorMedidaException("No hay ingredientes con la unidad de medida" +unidadMedida);
            }
            
            return ingredientes;
        } catch(Exception e){
            throw new BuscarPorMedidaException("Error al buscar ingredientes por unidad de medida" + e.getMessage());
        } finally{
            em.close();
        }
    }
    
    public List<Ingrediente> buscarIngrediente(String nombre, String medida) throws BuscarIngredienteException{
        
        
        EntityManager em= Conexion.crearConexion();
        try{
            String consulta= ("SELECT i FROM Ingrediente i WHERE 1=1 ");
            if(nombre!= null && !nombre.trim().isEmpty()){
                consulta+= (" AND i.nombre LIKE :nombre");
            } if(medida!=null){
                consulta+= (" AND i.unidadMedida= :unidadMedida");
                
            } Query query= em.createQuery(consulta);
            if(nombre!= null){
                query.setParameter("nombre", nombre + "%");           
             }if(medida!=null && !medida.trim().isEmpty()){
                 UnidadMedida unidad= convertirTextoAUnidad(medida);
                 System.out.println(unidad);
                 query.setParameter("unidadMedida", unidad);
                }  
                List<Ingrediente> ingredientes= query.getResultList();
                
                if(ingredientes.isEmpty()) {
                    throw new BuscarIngredienteException("Ingredientes con esos filtros no encontrados");
                }    
                return ingredientes;
        } catch(Exception e){
            throw new BuscarIngredienteException("Error al buscar ingredientes");
        } finally{
            em.close();
        }
        }
        
 
    
    
    public UnidadMedida convertirTextoAUnidad(String texto) throws ConvertirTextoAUnidadException{
        try{
            return UnidadMedida.valueOf(texto.toUpperCase());
            
        } catch(Exception e){
            throw new ConvertirTextoAUnidadException("Unidad de medida no valida "+texto);
            
        }
    }
    
    public Ingrediente buscarPorNombreUnico(String nombreIngrediente) throws BuscarIngredienteException {
    EntityManager em = Conexion.crearConexion();
    try {
        List<Ingrediente> ingredientes = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre = :nombre")
                                           .setParameter("nombre", nombreIngrediente)
                                           .getResultList();
        if (ingredientes.isEmpty()) {
            throw new BuscarIngredienteException("No se encontró el ingrediente con nombre: " + nombreIngrediente);
        }
        if (ingredientes.size() > 1) {
            throw new BuscarIngredienteException("Se encontraron múltiples ingredientes con el mismo nombre: " + nombreIngrediente);
        }
        return ingredientes.get(0);
    } catch (Exception e) {
        throw new BuscarIngredienteException("Error al buscar ingrediente único: " + e.getMessage(), e);
    } finally {
        em.close();
    }
}
    public Ingrediente buscarPorNombreYUnidad(String nombre, UnidadMedida unidad) {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre = :nombre AND i.unidadMedida = :unidad");
            query.setParameter("nombre", nombre);
            query.setParameter("unidad", unidad);

            List<Ingrediente> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                return (Ingrediente) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }
    
    
    public Ingrediente actualizarStock(Long idIngrediente, Double stock) throws ActualizarStockException{
        EntityManager em= Conexion.crearConexion();
        try{
            em.getTransaction().begin();
            Ingrediente ingrediente= em.find(Ingrediente.class, idIngrediente);
            if(ingrediente==null){
                throw new ActualizarStockException("Ingrediente con ID "+idIngrediente+ " no encontrado");
                
            }
            ingrediente.setStock(stock);
            em.merge(ingrediente);
            em.getTransaction().commit();
            return ingrediente;
        } catch(Exception e){
            throw new ActualizarStockException("Error al actualizar stock" + e.getMessage());
        } finally{
            em.close();
        }
    }
    
    public boolean eliminarIngrediente(Long idIngrediente) throws EliminarIngredienteException{
       
        EntityManager em= Conexion.crearConexion();
        try{
            em.getTransaction().begin();
            Query query= em.createQuery("SELECT COUNT (i) FROM ProductoIngrediente i WHERE i.ingrediente.id= :idIngrediente");
            query.setParameter("idIngrediente", idIngrediente);
            Long cant= (Long) query.getSingleResult();
            
            if(cant>0){
                throw new EliminarIngredienteException("No se pueden eliminar ingredientes asociados a productos");
            }
            
            Ingrediente ingrediente = em.find(Ingrediente.class, idIngrediente);
            
            em.remove(ingrediente);
            em.getTransaction().commit();
            return true;
            
            
            
        } catch(EliminarIngredienteException e){
            em.getTransaction().rollback();
            throw new EliminarIngredienteException("Error al eliminar ingrediente"+ e.getMessage());
            
        } finally{
            em.close();
        }
    }
}
    
    


