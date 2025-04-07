/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Mesa;
import excepciones.InsercionMasivaException;
import interfaces.IMesaDAO;
import javax.persistence.EntityManager;

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
    
    public boolean insercionMasiva() throws InsercionMasivaException {
        EntityManager em= Conexion.crearConexion();
        
        try{
            em.getTransaction().begin();
            
            for(int i=1; i<=20; i++){
                Mesa mesa= new Mesa(i);
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
    
    
}
