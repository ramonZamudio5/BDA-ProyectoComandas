/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.DetalleComandaDTO;
import entidades.DetalleComanda;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IDetalleComandaBO {
    public List<DetalleComandaDTO> obtenerTodos() throws NegocioException;
    public double obtenerImporteTotalPorComanda(Long idComanda) throws NegocioException;
    public List<DetalleComandaDTO> obtenerDetallesPorComanda(Long idComanda) throws NegocioException; 
}
