/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ClienteFrecuente;
import excepciones.AgregarClienteFrecuenteException;
import excepciones.BuscarClienteFrecuenteException;
import java.util.List;

/**
 *
 * @author Cricri
 */
public interface IClienteFrecuenteDAO {
   


    ClienteFrecuente agregarCliente(ClienteFrecuente cliente) throws AgregarClienteFrecuenteException;
    ClienteFrecuente obtenerCliente(Long id) throws BuscarClienteFrecuenteException;
    List<ClienteFrecuente> buscarPorNombre(String nombre) throws BuscarClienteFrecuenteException;
     ClienteFrecuente buscarPorTelefono(String telefono) throws BuscarClienteFrecuenteException;
    ClienteFrecuente buscarPorCorreo(String correo) throws BuscarClienteFrecuenteException;
    List<ClienteFrecuente> obtenerTodos() throws BuscarClienteFrecuenteException;
}

    

