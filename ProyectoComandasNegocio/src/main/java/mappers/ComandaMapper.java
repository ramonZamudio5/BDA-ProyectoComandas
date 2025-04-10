/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.ComandaDTO;
import entidades.Comanda;
import enums.EstadoComanda;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ComandaMapper {
    public static Comanda toEntity(ComandaDTO comanda){
        return new Comanda(comanda.getFolio(), comanda.getFechaHoraCreacion(), comanda.getTotalVenta(), comanda.getEstado(), comanda.getCliente(), 
                comanda.getMesa(), comanda.getDetalles());
    }
    public static ComandaDTO toDTO(Comanda comanda){
        return new ComandaDTO(comanda.getFolio(), comanda.getFechaHoraCreacion(), comanda.getTotalVenta(), comanda.getEstado(), comanda.getCliente(), comanda.getDetalles(),comanda.getMesa());
    }
    public static List<ComandaDTO> toListDTO(List<Comanda> comandas){
        List<ComandaDTO> comandasDTO = new LinkedList<>();
        for(Comanda comanda : comandas){
            comandasDTO.add(new ComandaDTO(comanda.getFolio(), comanda.getFechaHoraCreacion(), comanda.getTotalVenta(), comanda.getEstado(), comanda.getCliente(), comanda.getDetalles(),comanda.getMesa()));
        }
        return comandasDTO;
    }
}
