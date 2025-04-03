/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectocomandasnegocio;

import dtos.ClienteFrecuenteDTO;
import excepciones.AgregarClienteFrecuenteException;
import excepciones.BuscarClienteFrecuenteException;
import excepciones.NegocioException;
import interfaces.IClienteFrecuenteBO;
import interfaces.IClienteFrecuenteDAO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import manejadoresDeObjetosNegocio.ManejadorObjetosNegocio;

/**
 *
 * @author Cricri
 */
public class pruebaClienteBO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BuscarClienteFrecuenteException, AgregarClienteFrecuenteException  {
        // Crear el BO utilizando el manejador de objetos de negocio
        IClienteFrecuenteBO clienteFrecuenteBO = ManejadorObjetosNegocio.crearClienteFrecuenteBO();
        
        try {
            // Crear un DTO con datos de cliente
            ClienteFrecuenteDTO nuevoCliente = new ClienteFrecuenteDTO();
            nuevoCliente.setNombreCompleto("Juan Pérez");
            nuevoCliente.setTelefono("1234567890");
            nuevoCliente.setCorreoElectronico("juan.perez@example.com");
            nuevoCliente.setFechaRegistro(LocalDate.now()); 

            // Agregar cliente
            System.out.println("Registrando nuevo cliente...");
            ClienteFrecuenteDTO clienteRegistrado = clienteFrecuenteBO.agregarCliente(nuevoCliente);
            System.out.println("Cliente registrado con éxito!");
            System.out.println("Nombre: " + clienteRegistrado.getNombreCompleto());
            System.out.println("Teléfono: " + clienteRegistrado.getTelefono());
            System.out.println("Correo: " + clienteRegistrado.getCorreoElectronico());
            
            // Buscar cliente por nombre
            System.out.println("\nBuscando cliente por nombre...");
            List<ClienteFrecuenteDTO> clientesPorNombre = clienteFrecuenteBO.buscarPorNombre("Juan");
            if (clientesPorNombre.isEmpty()) {
                System.out.println("No se encontraron clientes con ese nombre.");
            } else {
                for (ClienteFrecuenteDTO cliente : clientesPorNombre) {
                    System.out.println(" | Nombre: " + cliente.getNombreCompleto());
                }
            }

            // Buscar cliente por teléfono
            System.out.println("\nBuscando cliente por teléfono...");
            List<ClienteFrecuenteDTO> clientesPorTelefono = clienteFrecuenteBO.buscarPorTelefono("1234567890");
            if (clientesPorTelefono.isEmpty()) {
                System.out.println("No se encontraron clientes con ese teléfono.");
            } else {
                for (ClienteFrecuenteDTO cliente : clientesPorTelefono) {
                    System.out.println( " | Nombre: " + cliente.getNombreCompleto());
                }
            }

        } catch (NegocioException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
