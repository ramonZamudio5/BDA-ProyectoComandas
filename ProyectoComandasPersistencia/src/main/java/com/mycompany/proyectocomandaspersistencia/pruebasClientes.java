/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectocomandaspersistencia;

import daos.ClienteFrecuenteDAO;
import entidades.ClienteFrecuente;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Cricri
 */
public class pruebasClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteFrecuenteDAO clienteFrecuenteDAO = ClienteFrecuenteDAO.getInstance();

        try {
            // Crear y agregar un nuevo ClienteFrecuente
            ClienteFrecuente nuevoCliente = new ClienteFrecuente(
                100, 5000.75, 10, // Puntos, gasto total, ventas acumuladas
                null, "Juan garcia", "9991234567", "juan.999@example.com", LocalDate.now()
            );

            ClienteFrecuente clienteAgregado = clienteFrecuenteDAO.agregarCliente(nuevoCliente);
            System.out.println("Cliente agregado: " + clienteAgregado);

            // Obtener cliente por ID
            ClienteFrecuente clienteObtenido = clienteFrecuenteDAO.obtenerCliente(clienteAgregado.getId());
            System.out.println("Cliente obtenido por ID: " + clienteObtenido);

            // Buscar por nombre
            List<ClienteFrecuente> clientesPorNombre = clienteFrecuenteDAO.buscarPorNombre("Juan");
            System.out.println("Clientes encontrados por nombre: " + clientesPorNombre);

            // Buscar por teléfono
            List<ClienteFrecuente> clientesPorTelefono = clienteFrecuenteDAO.buscarPorTelefono("5551234567");
            System.out.println("Clientes encontrados por teléfono: " + clientesPorTelefono);

            // Buscar por correo
            List<ClienteFrecuente> clientesPorCorreo = clienteFrecuenteDAO.buscarPorCorreo("juan.perez@example.com");
            System.out.println("Clientes encontrados por correo: " + clientesPorCorreo);

            // Obtener todos los clientes
            List<ClienteFrecuente> todosLosClientes = clienteFrecuenteDAO.obtenerTodos();
            System.out.println("Lista de todos los clientes: " + todosLosClientes);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
    
    

