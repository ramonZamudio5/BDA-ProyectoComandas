/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;
import conexion.Conexion;
import entidades.ClienteFrecuente;
import excepciones.AgregarClienteFrecuenteException;
import excepciones.BuscarClienteFrecuenteException;
import interfaces.IClienteFrecuenteDAO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


/**
 *
 * @author Cricri
 */
public class ClienteFrecuenteDAO implements IClienteFrecuenteDAO{
    
    
    private static ClienteFrecuenteDAO clienteFrecuenteDAO;

    public ClienteFrecuenteDAO() {
    }
    
    public static ClienteFrecuenteDAO getInstance() {
        if (clienteFrecuenteDAO == null) {
            clienteFrecuenteDAO = new ClienteFrecuenteDAO();
        }
        return clienteFrecuenteDAO;
    }
    
    
    
   
    @Override
    public ClienteFrecuente agregarCliente(ClienteFrecuente cliente) throws AgregarClienteFrecuenteException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AgregarClienteFrecuenteException("Error al agregar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
    @Override
    public ClienteFrecuente obtenerCliente(Long id) throws BuscarClienteFrecuenteException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(ClienteFrecuente.class, id);
        } catch (Exception e) {
            throw new BuscarClienteFrecuenteException("Error al buscar cliente");
        } finally {
            em.close();
        }
    }
    
    
   
    
    @Override
public List<ClienteFrecuente> buscarPorNombre(String nombre) throws BuscarClienteFrecuenteException {
    EntityManager em = Conexion.crearConexion();
    try {
        Query query = em.createQuery("SELECT c FROM ClienteFrecuente c WHERE LOWER(c.nombreCompleto) LIKE LOWER(:nombre)");
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    } catch (Exception e) {
        throw new BuscarClienteFrecuenteException("Error al buscar cliente por nombre: " + e.getMessage());
    } finally {
        em.close();
    }
}

    
 
    @Override
    public List<ClienteFrecuente> buscarPorTelefono(String telefono) throws BuscarClienteFrecuenteException {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery("SELECT c FROM ClienteFrecuente c WHERE c.telefono = :telefono");
            query.setParameter("telefono", telefono);
            return query.getResultList();
        } catch (Exception e) {
            throw new BuscarClienteFrecuenteException("Error al buscar cliente por teléfono");
        } finally {
            em.close();
        }
    }
    
   @Override
public List<ClienteFrecuente> buscarPorCorreo(String correo) throws BuscarClienteFrecuenteException {
    EntityManager em = Conexion.crearConexion();
    try {
        Query query = em.createQuery("SELECT c FROM ClienteFrecuente c WHERE c.correoElectronico = :correo");
        query.setParameter("correo", correo);
        return query.getResultList();
    } catch (Exception e) {
        throw new BuscarClienteFrecuenteException("Error al buscar cliente por correo: " + e.getMessage());
    } finally {
        em.close();
    }
}

    @Override
    public List<ClienteFrecuente> obtenerTodos() throws BuscarClienteFrecuenteException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT c FROM ClienteFrecuente c").getResultList();
        } catch (Exception e) {
            throw new BuscarClienteFrecuenteException("Error al obtener todos los clientes");
        } finally {
            em.close();
        }
    }

  
    
    
}

    
    
    

