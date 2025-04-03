/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ClienteFrecuenteDTO;
import excepciones.AgregarClienteFrecuenteException;
import excepciones.BuscarClienteFrecuenteException;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Cricri
 */
public interface IClienteFrecuenteBO {
     
    ClienteFrecuenteDTO agregarCliente(ClienteFrecuenteDTO clienteDTO) throws NegocioException, AgregarClienteFrecuenteException;
    
    ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException, BuscarClienteFrecuenteException;
    
    List<ClienteFrecuenteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarClienteFrecuenteException;
    
    List<ClienteFrecuenteDTO> buscarPorTelefono(String telefono) throws NegocioException, BuscarClienteFrecuenteException;
    
    List<ClienteFrecuenteDTO> buscarPorCorreo(String correo) throws NegocioException, BuscarClienteFrecuenteException;
    
    List<ClienteFrecuenteDTO> obtenerTodos() throws NegocioException;
    
}
