/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.DetalleComanda;
import excepciones.ObtenerDetallesComandaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Cricri
 */
public class DetalleComandaDAO {
    private static DetalleComandaDAO instancia;

    private DetalleComandaDAO() {}

    public static DetalleComandaDAO getInstance() {
        if (instancia == null) {
            instancia = new DetalleComandaDAO();
        }
        return instancia;
        }
    
    public List<DetalleComanda> obtenerTodos() throws ObtenerDetallesComandaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery("SELECT d FROM DetalleComanda d");
            return query.getResultList(); 
        } finally {
            em.close();
        }
    }


    public double obtenerImporteTotalPorComanda(Long idComanda)  throws ObtenerDetallesComandaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery(
                "SELECT SUM(d.importe) FROM DetalleComanda d WHERE d.comanda.id = :idComanda"
            );
            query.setParameter("idComanda", idComanda);
            Object result = query.getSingleResult();
            return result != null ? ((Number) result).doubleValue() : 0.0;
        } finally {
            em.close();
        }
    }



       public List<DetalleComanda> obtenerDetallesPorComanda(Long idComanda)  throws ObtenerDetallesComandaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery("SELECT d FROM DetalleComanda d WHERE d.comanda.id = :idComanda");
            query.setParameter("idComanda", idComanda);
            return (List<DetalleComanda>) query.getResultList();
        } finally {
            em.close();
        }
    }

    }
