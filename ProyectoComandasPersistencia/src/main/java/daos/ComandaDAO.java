/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.Producto;
import enums.EstadoComanda;
import excepciones.AgregarComandaException;
import excepciones.BuscarProductoException;
import excepciones.BusquedaComandaException;
import interfaces.IComandaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Ramón Zamudio
 */
public class ComandaDAO implements IComandaDAO{
    public static ComandaDAO comandaDAO;

    public ComandaDAO() {
    }
    
    public static ComandaDAO getInstance(){
        if(comandaDAO==null){
            comandaDAO = new ComandaDAO();
        }
        return comandaDAO;
    }
    
    public Comanda agregarComanda(Comanda comanda) throws AgregarComandaException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();

            if (comanda.getCliente() != null && comanda.getCliente().getId() == null) {
                throw new AgregarComandaException("El cliente debe estar persistido antes de agregar la comanda.");
            }
            
            List<DetalleComanda> detallesOriginales = comanda.getDetalles();
            
            
            comanda.setDetalles(new ArrayList<>()); 
            em.persist(comanda);
            em.flush(); 
            
            
            if (comanda.getId() == null) {
                throw new AgregarComandaException("No se generó ID para la comanda.");
            }

            List<DetalleComanda> detallesPersistidos = new ArrayList<>();
            for (DetalleComanda detalle : detallesOriginales) {
                if (detalle.getProducto() == null || detalle.getProducto().getId() == null) {
                    throw new AgregarComandaException("El producto del detalle no es válido o no tiene ID.");
                }
                detalle.setComanda(comanda);
                em.persist(detalle);
                detallesPersistidos.add(detalle);
            }

            comanda.setDetalles(detallesPersistidos);
            em.merge(comanda); 

            em.getTransaction().commit();
            return comanda;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AgregarComandaException("Error al agregar la comanda: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public List<Comanda> ObtenerTodo() throws BusquedaComandaException{
        EntityManager em = Conexion.crearConexion();
        try{
            return em.createQuery("SELECT c FROM Comanda c ").getResultList();
        }catch(Exception e){
            throw new BusquedaComandaException("Error al buscar la comanda",e);
        }finally {
            em.close();
        }
    }
    
    @Override
    public Comanda buscarPorFolio(String folio) throws BusquedaComandaException{
        EntityManager em = Conexion.crearConexion();
        try{
            return (Comanda) em.createQuery("SELECT c FROM Comanda c WHERE c.folio = :folio").setParameter("folio", folio).getSingleResult();
        }catch(Exception e){
            throw new BusquedaComandaException("Error al buscar la comanda",e);
        }finally {
            em.close();
        }
    }

    @Override
    public boolean actualizarComanda(Comanda comanda) throws BusquedaComandaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Query query = em.createQuery("SELECT c FROM Comanda c WHERE c.folio = :folio");
            query.setParameter("folio", comanda.getFolio());

            Comanda comandaAActualizar = (Comanda) query.getSingleResult();

            comandaAActualizar.setEstado(comanda.getEstado());
            comandaAActualizar.setTotalVenta(comanda.getTotalVenta());

            for (DetalleComanda nuevoDetalle : comanda.getDetalles()) {
                Producto producto = nuevoDetalle.getProducto();
                Producto productoExistente = buscarPorNombreUnico(producto.getNombre());

                if (productoExistente != null) {
                    producto = productoExistente;
                } else {
                    em.persist(producto);
                }

                nuevoDetalle.setProducto(producto);
                nuevoDetalle.setComanda(comandaAActualizar);

                nuevoDetalle.setImporte(nuevoDetalle.getPrecioUnitario() * nuevoDetalle.getCantidad());

                if (nuevoDetalle.getId() != null) {
                    DetalleComanda detalleExistente = em.find(DetalleComanda.class, nuevoDetalle.getId());
                    if (detalleExistente != null) {
                        detalleExistente.setCantidad(nuevoDetalle.getCantidad());
                        detalleExistente.setPrecioUnitario(nuevoDetalle.getPrecioUnitario());
                        detalleExistente.setNotas(nuevoDetalle.getNotas());
                        detalleExistente.setImporte(nuevoDetalle.getPrecioUnitario() * nuevoDetalle.getCantidad());  // Asegurar que el IMPORTE se actualice
                    }
                } else {
                    comandaAActualizar.getDetalles().add(nuevoDetalle);
                    em.persist(nuevoDetalle);
                }
            }

            em.getTransaction().commit();
            return true;

        } catch (NoResultException ex) {
            throw new BusquedaComandaException("Comanda no encontrada con folio: " + comanda.getFolio());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BusquedaComandaException("Error al actualizar la comanda: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Producto buscarPorNombreUnico(String nombreProducto) throws BuscarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            return (Producto) em.createQuery("SELECT p FROM Producto p WHERE p.nombre = :nombre")
                    .setParameter("nombre", nombreProducto)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new BuscarProductoException("No se encontró el producto con nombre: " + nombreProducto);
        } catch (Exception e) {
            throw new BuscarProductoException("Error al buscar producto único: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}


