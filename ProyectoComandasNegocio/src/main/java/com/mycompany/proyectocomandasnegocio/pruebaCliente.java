/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectocomandasnegocio;

import bos.ClienteFrecuenteBO;
import daos.ClienteFrecuenteDAO;
import dtos.ClienteFrecuenteDTO;
import excepciones.NegocioException;
import interfaces.IClienteFrecuenteDAO;
import java.util.List;

/**
 *
 * @author Cricri
 */
public class pruebaCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Inicializamos el DAO y el BO
            IClienteFrecuenteDAO clienteFrecuenteDAO = new ClienteFrecuenteDAO();
            ClienteFrecuenteBO clienteFrecuenteBO = new ClienteFrecuenteBO(clienteFrecuenteDAO);
            
            // Crear un cliente para probar
            ClienteFrecuenteDTO clienteDTO = new ClienteFrecuenteDTO();
            clienteDTO.setNombreCompleto("Juan Pérez");
            clienteDTO.setTelefono("1234567890");
            clienteDTO.setCorreoElectronico("juan.perez@email.com");

            // Agregar cliente
            ClienteFrecuenteDTO clienteAgregado = clienteFrecuenteBO.agregarCliente(clienteDTO);
            System.out.println("Cliente agregado: " + clienteAgregado);

            // Buscar cliente por nombre
            List<ClienteFrecuenteDTO> clientesPorNombre = clienteFrecuenteBO.buscarPorNombre("Juan");
            System.out.println("Clientes encontrados por nombre: ");
            for (ClienteFrecuenteDTO cliente : clientesPorNombre) {
                System.out.println(cliente);
            }

            // Buscar cliente por teléfono
            List<ClienteFrecuenteDTO> clientesPorTelefono = clienteFrecuenteBO.buscarPorTelefono("1234567890");
            System.out.println("Clientes encontrados por teléfono: ");
            for (ClienteFrecuenteDTO cliente : clientesPorTelefono) {
                System.out.println(cliente);
            }

            // Buscar cliente por correo electrónico
            List<ClienteFrecuenteDTO> clientesPorCorreo = clienteFrecuenteBO.buscarPorCorreo("juan.perez@email.com");
            System.out.println("Clientes encontrados por correo: ");
            for (ClienteFrecuenteDTO cliente : clientesPorCorreo) {
                System.out.println(cliente);
            }

            // Obtener todos los clientes
            List<ClienteFrecuenteDTO> todosLosClientes = clienteFrecuenteBO.obtenerTodos();
            System.out.println("Todos los clientes registrados: ");
            for (ClienteFrecuenteDTO cliente : todosLosClientes) {
                System.out.println(cliente);
            }
        } catch (NegocioException e) {
            System.err.println("Error en la operación: " + e.getMessage());
        }
    }
    }
    

