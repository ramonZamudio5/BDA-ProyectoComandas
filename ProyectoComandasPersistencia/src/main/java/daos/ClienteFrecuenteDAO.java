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
            if (cliente.getFechaRegistro() == null) {
                cliente.setFechaRegistro(LocalDate.now());
            }

            try {
                // encriptar datos
                cliente.setTelefono(Encriptador.encriptar(cliente.getTelefono()));
                if (cliente.getCorreoElectronico() != null) {
                    cliente.setCorreoElectronico(Encriptador.encriptar(cliente.getCorreoElectronico()));
                }

                EntityManager em = Conexion.crearConexion();
                em.getTransaction().begin();
                em.persist(cliente);
                em.getTransaction().commit();
                return cliente;
            } catch (Exception e) {
                throw new AgregarClienteFrecuenteException("Error al agregar cliente: " + e.getMessage());
            }
        }

        @Override
     public ClienteFrecuente obtenerCliente(Long id) throws BuscarClienteFrecuenteException {
         EntityManager em = Conexion.crearConexion();
         try {
             ClienteFrecuente cliente = em.find(ClienteFrecuente.class, id);
             if (cliente != null) {
                 // desencriptar datos
                 cliente.setTelefono(Encriptador.desencriptar(cliente.getTelefono()));
                 if (cliente.getCorreoElectronico() != null) {
                     cliente.setCorreoElectronico(Encriptador.desencriptar(cliente.getCorreoElectronico()));
                 }

                 calcularAtributosClienteFrecuente(cliente);
             }
             return cliente;
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
            List<ClienteFrecuente> clientes = query.getResultList();
            for (ClienteFrecuente cliente : clientes) {
                calcularAtributosClienteFrecuente(cliente);
            }
            return clientes;
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
            
            Query query = em.createQuery("SELECT c FROM ClienteFrecuente c WHERE c.telefono LIKE :telefono");
            query.setParameter("telefono", "%" + telefono + "%"); 
            List<ClienteFrecuente> clientes = query.getResultList();
            for (ClienteFrecuente cliente : clientes) {
                calcularAtributosClienteFrecuente(cliente);
            }
            return clientes;
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
            
            Query query = em.createQuery("SELECT c FROM ClienteFrecuente c WHERE c.correoElectronico LIKE :correo");
            query.setParameter("correo", "%" + correo + "%"); 
            List<ClienteFrecuente> clientes = query.getResultList();
            for (ClienteFrecuente cliente : clientes) {
                calcularAtributosClienteFrecuente(cliente);
            }
            return clientes;
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
                List<ClienteFrecuente> clientes = em.createQuery("SELECT c FROM ClienteFrecuente c").getResultList();

                // desencriptar 
                for (ClienteFrecuente cliente : clientes) {
                    calcularAtributosClienteFrecuente(cliente);

                    // desencriptar 
                    cliente.setCorreoElectronico(Encriptador.desencriptar(cliente.getCorreoElectronico()));
                    cliente.setTelefono(Encriptador.desencriptar(cliente.getTelefono()));
                }

                return clientes;
            } catch (Exception e) {
                throw new BuscarClienteFrecuenteException("Error al obtener todos los clientes");
            } finally {
                em.close();
            }
        }


    //calcular los atributos transient
    private void calcularAtributosClienteFrecuente(ClienteFrecuente cliente) {
        //  puntos de fidelidad
        cliente.setPuntosObtenidos(calcularPuntos(cliente));

        // gasto total acumulado
        cliente.setGastoTotalAcumulado(calcularGastoTotal(cliente));

        //número de visitas
        cliente.setConteoVisitas(calcularVisitas(cliente));
    }
        //puntos obtenidos por el cliente
    private int calcularPuntos(ClienteFrecuente cliente) {
      
        double gastoTotal = calcularGastoTotal(cliente);
        return (int) (gastoTotal / 20);
    }

    //gasto total acumulado por el cliente
    private double calcularGastoTotal(ClienteFrecuente cliente) {
        EntityManager em = Conexion.crearConexion();
        try {
            //comandas asociadas al cliente para calcular el gasto total
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
        //   número de visitas del cliente 
    private int calcularVisitas(ClienteFrecuente cliente) {
        EntityManager em = Conexion.crearConexion();
        try {
            //comandas asociadas al cliente
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

    
    
    

