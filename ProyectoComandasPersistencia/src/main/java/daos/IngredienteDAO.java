/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Ingrediente;
import entidades.UnidadMedida;
import excepciones.AgregarIngredienteException;
import interfaces.IIngredienteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
            IngredienteDAO ingredienteDAO = new IngredienteDAO();
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

}
