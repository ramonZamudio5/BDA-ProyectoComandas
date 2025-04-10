/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.DetalleComandaDTO;
import entidades.DetalleComanda;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class DetalleComandaMapper {
    public static DetalleComanda toEntity(DetalleComandaDTO detalleComanda){
        return new DetalleComanda(detalleComanda.getProducto(), detalleComanda.getCantidad(), detalleComanda.getPrecioUnitario(), detalleComanda.getNotas());
    }
    
    public static DetalleComandaDTO toDTO(DetalleComanda detalleComanda){
        return new DetalleComandaDTO(detalleComanda.getId(),detalleComanda.getComanda(),detalleComanda.getProducto(),detalleComanda.getCantidad(), detalleComanda.getPrecioUnitario()
                , detalleComanda.getImporte(), detalleComanda.getNotas());
    }
    
    public static List<DetalleComandaDTO> toListDTO(List<DetalleComanda> detalleComandas){
        List<DetalleComandaDTO> detalleComandasDTO = new LinkedList<>();
        for(DetalleComanda detalleComanda: detalleComandas){
            detalleComandasDTO.add(new DetalleComandaDTO(detalleComanda.getId(),detalleComanda.getComanda(),detalleComanda.getProducto(),detalleComanda.getCantidad(), detalleComanda.getPrecioUnitario()
                , detalleComanda.getImporte(), detalleComanda.getNotas()));
        }
        return detalleComandasDTO;
    }
}
