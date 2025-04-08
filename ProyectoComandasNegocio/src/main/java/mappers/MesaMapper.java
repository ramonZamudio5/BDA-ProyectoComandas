/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.MesaDispDTO;
import entidades.Mesa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class MesaMapper {
    
    public static List<MesaDispDTO> toListDTOConID(List<Mesa> mesas){
        if(mesas==null || mesas.isEmpty()){
            return null;
        }
        List<MesaDispDTO> listaMesasDTO = new ArrayList<>();
        
        for(Mesa mesa : mesas){
           MesaDispDTO mesaDispDTO= new MesaDispDTO(
                   mesa.getId(),
                   mesa.getNumero(),
                   mesa.getEstado()
           );
           listaMesasDTO.add(mesaDispDTO);
        }
        return listaMesasDTO;
    }
    
    
}
