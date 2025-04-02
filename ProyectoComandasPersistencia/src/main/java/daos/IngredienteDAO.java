/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Ingrediente;
import entidades.UnidadMedida;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorNombreException;
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

    public IngredienteDAO() {

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
    
    public List<Ingrediente> buscarPorNombre(String nombre) throws BuscarPorNombreException{
        EntityManager em= Conexion.crearConexion();
        try {
       
           TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre = :nombre", Ingrediente.class);
            query.setParameter("nombre", nombre);
            List<Ingrediente> ingredientes = query.getResultList();

            
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

}
