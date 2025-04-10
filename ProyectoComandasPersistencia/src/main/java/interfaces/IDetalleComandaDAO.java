/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.DetalleComanda;
import excepciones.ObtenerDetallesComandaException;
import java.util.List;

/**
 *
 * @author Cricri
 */
public interface IDetalleComandaDAO {
      public List<DetalleComanda> obtenerTodos() throws ObtenerDetallesComandaException;
      
      public double obtenerImporteTotalPorComanda(Long idComanda)  throws ObtenerDetallesComandaException;
      
        public List<DetalleComanda> obtenerDetallesPorComanda(Long idComanda)  throws ObtenerDetallesComandaException ;
}
