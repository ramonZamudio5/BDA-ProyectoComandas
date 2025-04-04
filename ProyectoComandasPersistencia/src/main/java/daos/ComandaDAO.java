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
import excepciones.AgregarComandaException;
import excepciones.BusquedaComandaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ramón Zamudio
 */
public class ComandaDAO {
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
            comanda.setDetalles(new ArrayList<>()); // temporalmente vacía
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
        }
    }
    
    public Comanda buscarPorFolio(String folio) throws BusquedaComandaException{
        EntityManager em = Conexion.crearConexion();
        try{
            return (Comanda) em.createQuery("SELECT c FROM Comanda c WHERE folio = :folio").setParameter("folio", folio).getSingleResult();
        }catch(Exception e){
            throw new BusquedaComandaException("Error al buscar la comanda",e);
        }
    }
    
}
    
    

