/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Mesa;
import enums.EstadoMesa;
import excepciones.InsercionMasivaException;
import excepciones.ObtenerMesasDispException;
import interfaces.IMesaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class MesaDAO implements IMesaDAO{
    
    public static MesaDAO mesaDAO;

    public MesaDAO() {
    }
    
    public static MesaDAO getInstance(){
        if(mesaDAO==null){
            mesaDAO= new MesaDAO();    
        }
        return mesaDAO;
    }
    
    @Override
    public boolean insercionMasiva() throws InsercionMasivaException {
        EntityManager em= Conexion.crearConexion();
        
        try{
             em.getTransaction().begin();
            long mesas= (long) em.createQuery("SELECT COUNT(m) FROM Mesa m").getSingleResult();
            if(mesas>0){
                return false;
            }
            
           
            
            for(int i=1; i<=20; i++){
                Mesa mesa= new Mesa(i);
                mesa.setEstado(EstadoMesa.DISPONIBLE);
                em.persist(mesa);
            
        }
            em.getTransaction().commit();
            //quitar
            System.out.println("si se agregaron");
            return true;
        } catch(Exception e){
            em.getTransaction().rollback();
            throw new InsercionMasivaException("Error al agregar mesas "+e.getMessage());
            
        } finally{
            em.close();
        }
    }
    
    
    @Override
    public List<Mesa> obtenerMesasDisponibles() throws ObtenerMesasDispException{
        
        EntityManager em= Conexion.crearConexion();
        
        try{
             em.getTransaction().begin();
            Query query= em.createQuery("SELECT m FROM Mesa m WHERE m.estado= :estado");           
            query.setParameter("estado", EstadoMesa.DISPONIBLE);     
            return query.getResultList();
            
        } catch(Exception e){
            throw new ObtenerMesasDispException("Error al obtener mesas disponibles "+e.getMessage());
        } finally{
            em.close();
        }
        
    }
    
}
