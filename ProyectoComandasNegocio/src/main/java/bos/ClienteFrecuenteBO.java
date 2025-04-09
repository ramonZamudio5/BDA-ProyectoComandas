/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.ClienteFrecuenteDTO;
import entidades.ClienteFrecuente;
import excepciones.NegocioException;
import interfaces.IClienteFrecuenteBO;
import interfaces.IClienteFrecuenteDAO;
import java.util.List;
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
    public ClienteFrecuenteDTO agregarCliente(ClienteFrecuenteDTO clienteDTO) throws NegocioException {
        if (clienteDTO == null) {
            throw new NegocioException("El cliente no puede ser nulo");
        }
        if (clienteDTO.getNombreCompleto() == null || clienteDTO.getNombreCompleto().trim().isEmpty()) {
            throw new NegocioException("El nombre completo es obligatorio");
        }
        if (clienteDTO.getTelefono() == null || !clienteDTO.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe ser un número de 10 dígitos");
        }

      
        if (clienteDTO.getCorreoElectronico() != null && !clienteDTO.getCorreoElectronico().trim().isEmpty()) {
            if (!clienteDTO.getCorreoElectronico().contains("@")) {
                throw new NegocioException("El correo electrónico no es válido");
            }
        }

        ClienteFrecuente cliente = ClienteFrecuenteMapper.toEntity(clienteDTO);
        try {
            ClienteFrecuente agregado = clienteFrecuenteDAO.agregarCliente(cliente);
            return ClienteFrecuenteMapper.toDTO(agregado);
        } catch (Exception e) {
            throw new NegocioException("Error al agregar cliente: " + e.getMessage());
        }
    }

    @Override
    public ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException {
        if (id == null || id <= 0) {
            throw new NegocioException("El ID debe ser válido");
        }

        try {
            ClienteFrecuente cliente = clienteFrecuenteDAO.obtenerCliente(id);
            if (cliente == null) {
                throw new NegocioException("Cliente no encontrado");
            }
            return ClienteFrecuenteMapper.toDTO(cliente);
        } catch (Exception e) {
            throw new NegocioException("Error al obtener cliente: " + e.getMessage());
        }
    }

    @Override
    public List<ClienteFrecuenteDTO> obtenerTodos() throws NegocioException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.obtenerTodos();
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (Exception e) {
            throw new NegocioException("Error al obtener todos los clientes: " + e.getMessage());
        }
    }

    @Override
    public List<ClienteFrecuenteDTO> buscarClientes(String nombre, String telefono, String correo) throws NegocioException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.buscarCliente(nombre, telefono, correo);
            return ClienteFrecuenteMapper.toListDTO(clientes);
        } catch (Exception e) {
            throw new NegocioException("Error al buscar clientes: " + e.getMessage());
        }
    }
}


