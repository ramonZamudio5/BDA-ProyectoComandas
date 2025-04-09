/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;
import Encriptador.Encriptador;
import conexion.Conexion;
import entidades.ClienteFrecuente;
import excepciones.AgregarClienteFrecuenteException;
import excepciones.BuscarClienteFrecuenteException;
import interfaces.IClienteFrecuenteDAO;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Cricri
 */import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.NoResultException;


public class ClienteFrecuenteDAO implements IClienteFrecuenteDAO {

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
        if (cliente.getFechaRegistro() == null) {
            cliente.setFechaRegistro(LocalDate.now());
        }

        try {
          
            cliente.setTelefono(Encriptador.encriptar(cliente.getTelefono()));  
        } catch (Exception e) {
            throw new AgregarClienteFrecuenteException("Error al encriptar teléfono: " + e.getMessage());
        }

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
  public List<ClienteFrecuente> buscarCliente(String nombre, String telefono, String correo) throws BuscarClienteFrecuenteException {
      EntityManager em = Conexion.crearConexion();
      try {
          String consulta = "SELECT c FROM ClienteFrecuente c WHERE 1=1";

          if (nombre != null && !nombre.trim().isEmpty()) {
              consulta += " AND LOWER(c.nombreCompleto) LIKE LOWER(:nombre)";
          }
          if (telefono != null && !telefono.trim().isEmpty()) {
              consulta += " AND c.telefono LIKE :telefono";
          }
          if (correo != null && !correo.trim().isEmpty()) {
              consulta += " AND c.correoElectronico LIKE :correo";
          }

          Query query = em.createQuery(consulta);

          if (nombre != null && !nombre.trim().isEmpty()) {
              query.setParameter("nombre", "%" + nombre + "%");
          }
          if (telefono != null && !telefono.trim().isEmpty()) {
              String telefonoCifrado = Encriptador.encriptar(telefono);
              query.setParameter("telefono", "%" + telefonoCifrado + "%");
          }
          if (correo != null && !correo.trim().isEmpty()) {
              query.setParameter("correo", "%" + correo+ "%");
          }

          List<ClienteFrecuente> clientes = query.getResultList();
          for (ClienteFrecuente cliente : clientes) {
              
              cliente.setTelefono(Encriptador.desencriptar(cliente.getTelefono()));

              
              calcularAtributosClienteFrecuente(cliente);
          }

          return clientes;

      } catch (Exception e) {
          throw new BuscarClienteFrecuenteException("Error al buscar cliente con los filtros ingresados: " + e.getMessage());
      } finally {
          em.close();
      }
  }


        @Override
    public ClienteFrecuente obtenerCliente(Long id) throws BuscarClienteFrecuenteException {
        EntityManager em = Conexion.crearConexion();
        try {
            ClienteFrecuente cliente = em.find(ClienteFrecuente.class, id);
            if (cliente != null) {
                calcularAtributosClienteFrecuente(cliente);

                
                cliente.setTelefono(Encriptador.desencriptar(cliente.getTelefono()));
            }
            return cliente;
        } catch (Exception e) {
            throw new BuscarClienteFrecuenteException("Error al buscar cliente con ID: " + id);
        } finally {
            em.close();
        }
    }

  
  
  
    @Override
    public List<ClienteFrecuente> obtenerTodos() throws BuscarClienteFrecuenteException {
        EntityManager em = Conexion.crearConexion();
        try {
            List<ClienteFrecuente> clientes = em.createQuery("SELECT c FROM ClienteFrecuente c").getResultList();
            for (ClienteFrecuente cliente : clientes) {
                calcularAtributosClienteFrecuente(cliente);

               
                cliente.setTelefono(Encriptador.desencriptar(cliente.getTelefono()));
            }
            return clientes;
        } catch (Exception e) {
            throw new BuscarClienteFrecuenteException("Error al obtener todos los clientes");
        } finally {
            em.close();
        }
    }

  
    private void calcularAtributosClienteFrecuente(ClienteFrecuente cliente) {
        cliente.setPuntosObtenidos(calcularPuntos(cliente));
        cliente.setGastoTotalAcumulado(calcularGastoTotal(cliente));
        cliente.setConteoVisitas(calcularVisitas(cliente));
    }

    private int calcularPuntos(ClienteFrecuente cliente) {
        double gastoTotal = calcularGastoTotal(cliente);
        return (int) (gastoTotal / 20);
    }

    private double calcularGastoTotal(ClienteFrecuente cliente) {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery("SELECT SUM(c.total) FROM Comanda c WHERE c.cliente.id = :clienteId");
            query.setParameter("clienteId", cliente.getId());
            Double gastoTotal = (Double) query.getSingleResult();
            return (gastoTotal != null) ? gastoTotal : 0.0;
        } catch (Exception e) {
            return 0.0;
        } finally {
            em.close();
        }
    }

    private int calcularVisitas(ClienteFrecuente cliente) {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery("SELECT COUNT(c) FROM Comanda c WHERE c.cliente.id = :clienteId");
            query.setParameter("clienteId", cliente.getId());
            Long cantidadVisitas = (Long) query.getSingleResult();
            return cantidadVisitas.intValue();
        } catch (Exception e) {
            return 0;
        } finally {
            em.close();
        }
    }

    
   
}

