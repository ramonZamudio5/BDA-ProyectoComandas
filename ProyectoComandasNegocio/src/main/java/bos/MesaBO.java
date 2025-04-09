/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.MesaDispDTO;
import entidades.Mesa;
import excepciones.NegocioException;
import excepciones.ObtenerMesasDispException;
import interfaces.IMesaDAO;
import java.util.List;
import mappers.MesaMapper;
import interfaces.IMesaBO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class MesaBO implements IMesaBO{
    
    private IMesaDAO mesaDAO;

    public MesaBO(IMesaDAO mesaDAO) {
        
        this.mesaDAO= mesaDAO;
    }
    
    
    @Override
     public List<MesaDispDTO> obtenerMesasDisponibles() throws NegocioException{
         
         try{
             List<Mesa> mesasDisponibles= mesaDAO.obtenerMesasDisponibles();
             if(mesasDisponibles==null || mesasDisponibles.isEmpty()){
                 throw new ObtenerMesasDispException("No hay mesas disponibles");
             }
             for(Mesa mesa: mesasDisponibles){
                 if(mesa.getId()==null){
                     throw new ObtenerMesasDispException("La mesa no puede ser nulo");
                 }
             }
             return MesaMapper.toListDTOConID(mesasDisponibles);
         } catch(Exception e){
             throw new NegocioException("Error al obtener las mesas disponibles "+e.getMessage());
         }
     }
    
}
