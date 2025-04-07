/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Mesa;
import excepciones.ObtenerMesasDispException;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IMesaBO {
    
    public List<Mesa> obtenerMesasDisponibles() throws ObtenerMesasDispException;
    
    
}
