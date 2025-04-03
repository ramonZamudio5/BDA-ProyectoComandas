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
  
    private IClienteFrecuenteDAO clienteFrecuenteDAO;
    
    public ClienteFrecuenteBO(IClienteFrecuenteDAO clienteFrecuenteDAO) {
        this.clienteFrecuenteDAO = clienteFrecuenteDAO;
    }
    
    @Override
    public ClienteFrecuenteDTO agregarCliente(ClienteFrecuenteDTO clienteDTO) throws NegocioException {
        if (clienteDTO == null) {
            throw new NegocioException("El cliente no puede ser nulo");
        }
        if (clienteDTO.getNombreCompleto() == null || clienteDTO.getNombreCompleto().trim().isEmpty()) {
            throw new NegocioException("El nombre del cliente no puede ser nulo o vacío");
        }
        if (clienteDTO.getTelefono() == null || !clienteDTO.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("El teléfono del cliente debe ser un número de 10 dígitos");
        }
        if (clienteDTO.getCorreoElectronico() == null || !clienteDTO.getCorreoElectronico().contains("@")) {
            throw new NegocioException("El correo electrónico del cliente no es válido");
        }
        
        ClienteFrecuente cliente = ClienteFrecuenteMapper.toEntity(clienteDTO);
        try {
            ClienteFrecuente clienteAgregado = clienteFrecuenteDAO.agregarCliente(cliente);
            if (clienteAgregado == null || clienteAgregado.getId() == null) {
                throw new NegocioException("El cliente no se pudo agregar");
            }
            return ClienteFrecuenteMapper.toDTO(clienteAgregado);
        } catch (Exception e) {
            throw new NegocioException("Error al agregar cliente");
        }
    }
    
    @Override
    public ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException {
        if (id == null || id <= 0) {
            throw new NegocioException("El ID no puede ser nulo o menor a 1");
        }
        try {
            ClienteFrecuente cliente = clienteFrecuenteDAO.obtenerCliente(id);
            if (cliente == null) {
                throw new NegocioException("No se encontró el cliente");
            }
            return ClienteFrecuenteMapper.toDTO(cliente);
        } catch (Exception e) {
            throw new NegocioException("Error al obtener cliente");
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> buscarPorNombre(String nombre) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede ser nulo o vacío");
        }
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.buscarPorNombre(nombre);
            if (clientes == null || clientes.isEmpty()) {
                throw new NegocioException("No se encontraron clientes");
            }
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (Exception e) {
            throw new NegocioException("Error al buscar clientes por nombre");
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> buscarPorTelefono(String telefono) throws NegocioException {
        if (telefono == null || !telefono.matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe ser un número de 10 dígitos");
        }
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.buscarPorTelefono(telefono);
            if (clientes == null || clientes.isEmpty()) {
                throw new NegocioException("No se encontraron clientes con ese teléfono");
            }
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (Exception e) {
            throw new NegocioException("Error al buscar cliente por teléfono");
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> buscarPorCorreo(String correo) throws NegocioException {
        if (correo == null || !correo.contains("@")) {
            throw new NegocioException("El correo electrónico no es válido");
        }
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.buscarPorCorreo(correo);
            if (clientes == null || clientes.isEmpty()) {
                throw new NegocioException("No se encontraron clientes con ese correo electrónico");
            }
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (Exception e) {
            throw new NegocioException("Error al buscar cliente por correo");
        }
    }
    
    @Override
    public List<ClienteFrecuenteDTO> obtenerTodos() throws NegocioException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.obtenerTodos();
            if (clientes == null || clientes.isEmpty()) {
                throw new NegocioException("No hay clientes registrados");
            }
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (Exception e) {
            throw new NegocioException("Error al obtener clientes");
        }
    }
}

