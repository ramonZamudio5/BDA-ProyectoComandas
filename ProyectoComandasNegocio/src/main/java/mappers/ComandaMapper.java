/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.ComandaDTO;
import entidades.Comanda;
import enums.EstadoComanda;
import java.time.LocalDateTime;

/**
 *
 * @author Ramón Zamudio
 */
public class ComandaMapper {
    public static Comanda toEntity(ComandaDTO comanda){
        return new Comanda(comanda.getFolio(), comanda.getFechaHoraCreacion(), comanda.getTotalVenta(), comanda.getEstado(), comanda.getCliente(), comanda.getDetalles());
    }
    public static ComandaDTO toDTO(Comanda comanda){
        return new ComandaDTO(comanda.getFolio(), comanda.getFechaHoraCreacion(), comanda.getTotalVenta(), comanda.getEstado(), comanda.getCliente(), comanda.getDetalles());
    }
}
