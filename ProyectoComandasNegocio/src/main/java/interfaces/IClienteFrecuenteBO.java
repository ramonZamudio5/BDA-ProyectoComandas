/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ClienteFrecuenteDTO;

import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Cricri
 */
public interface IClienteFrecuenteBO {
     
    ClienteFrecuenteDTO agregarCliente(ClienteFrecuenteDTO clienteDTO) throws NegocioException;
    
    ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException;
    
    List<ClienteFrecuenteDTO> buscarClientes(String nombre, String telefono, String correo) throws NegocioException;
    
    List<ClienteFrecuenteDTO> obtenerTodos() throws NegocioException;
    
    LocalDateTime obtenerUltimaComanda(Long idCliente) throws NegocioException;
    
}
