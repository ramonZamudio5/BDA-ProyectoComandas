/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.ClienteFrecuenteDTO;
import entidades.ClienteFrecuente;
import excepciones.AgregarClienteFrecuenteException;
import excepciones.BuscarClienteFrecuenteException;
import excepciones.NegocioException;
import interfaces.IClienteFrecuenteBO;
import interfaces.IClienteFrecuenteDAO;
import java.util.List;
import java.util.stream.Collectors;
import mappers.ClienteFrecuenteMapper;

/**
 *
 * @author Cricri
 */
public class ClienteFrecuenteBO implements IClienteFrecuenteBO {
     
    private final IClienteFrecuenteDAO clienteFrecuenteDAO;
    
    public ClienteFrecuenteBO(IClienteFrecuenteDAO clienteFrecuenteDAO) {
        this.clienteFrecuenteDAO = clienteFrecuenteDAO;
    }

    @Override
    public ClienteFrecuenteDTO agregarCliente(ClienteFrecuenteDTO clienteDTO) throws NegocioException, AgregarClienteFrecuenteException {
        try {
            ClienteFrecuente cliente = ClienteFrecuenteMapper.toEntity(clienteDTO);
            ClienteFrecuente clienteGuardado = clienteFrecuenteDAO.agregarCliente(cliente);
            return ClienteFrecuenteMapper.toDTO(clienteGuardado);
        } catch (AgregarClienteFrecuenteException e) {
            throw new NegocioException("Error en la capa de negocio al agregar cliente", e);
        }
    }
    
    @Override
    public ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException, BuscarClienteFrecuenteException {
        try {
            ClienteFrecuente cliente = clienteFrecuenteDAO.obtenerCliente(id);
            if (cliente == null) {
                throw new BuscarClienteFrecuenteException("Cliente no encontrado con ID: " + id);
            }
            return ClienteFrecuenteMapper.toDTO(cliente);
        } catch (BuscarClienteFrecuenteException e) {
            throw new NegocioException("Error en la capa de negocio al obtener cliente", e);
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarClienteFrecuenteException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.buscarPorNombre(nombre);
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (BuscarClienteFrecuenteException e) {
            throw new NegocioException("Error en la capa de negocio al buscar clientes por nombre", e);
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> buscarPorTelefono(String telefono) throws NegocioException, BuscarClienteFrecuenteException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.buscarPorTelefono(telefono);
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (BuscarClienteFrecuenteException e) {
            throw new NegocioException("Error en la capa de negocio al buscar clientes por teléfono", e);
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> buscarPorCorreo(String correo) throws NegocioException, BuscarClienteFrecuenteException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.buscarPorCorreo(correo);
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (BuscarClienteFrecuenteException e) {
            throw new NegocioException("Error en la capa de negocio al buscar clientes por correo", e);
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> obtenerTodos() throws NegocioException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.obtenerTodos();
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (BuscarClienteFrecuenteException e) {
            throw new NegocioException("Error en la capa de negocio al obtener todos los clientes", e);
        }
    }
}


