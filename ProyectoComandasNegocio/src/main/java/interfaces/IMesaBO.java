/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.MesaDispDTO;
import entidades.Mesa;
import excepciones.NegocioException;
import excepciones.ObtenerMesasDispException;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IMesaBO {
    
    public List<MesaDispDTO> obtenerMesasDisponibles() throws NegocioException;
    
    
}
