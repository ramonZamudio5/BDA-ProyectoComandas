/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ComandaDTO;
import enums.EstadoComanda;
import excepciones.AgregarComandaException;
import excepciones.BusquedaComandaException;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IComandaBO {
    public ComandaDTO agregarComanda(ComandaDTO comanda) throws AgregarComandaException;
    
    public List<ComandaDTO> ObtenerTodo() throws BusquedaComandaException;
    
    public ComandaDTO buscarPorFolio(String folio) throws BusquedaComandaException;
    
    public boolean actualizarComanda(ComandaDTO comanda)throws NegocioException;
}
