/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
/*
package daos;

import entidades.ClienteFrecuente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import javax.persistence.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;



public class ClienteFrecuenteDAOTest {
  
    private ClienteFrecuenteDAO clienteFrecuenteDAO;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeEach
    public void setUp() {
        // Crear mocks de EntityManager y EntityTransaction usando Mockito
        entityManager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);

        // Simular que la conexión se crea correctamente
        when(entityManager.getTransaction()).thenReturn(transaction);
        
        // Crear la instancia de ClienteFrecuenteDAO y pasarle el EntityManager simulado
        clienteFrecuenteDAO = new ClienteFrecuenteDAO() {
            public EntityManager crearConexion() {
                return entityManager;
            }
        };
    }

    @Test
    public void testGetInstance() {
        // Verificar que se obtiene una única instancia (singleton)
        ClienteFrecuenteDAO instance1 = ClienteFrecuenteDAO.getInstance();
        ClienteFrecuenteDAO instance2 = ClienteFrecuenteDAO.getInstance();
        assertSame(instance1, instance2);  // Verificamos que ambas instancias sean la misma
    }

    @Test
    public void testAgregarCliente() throws Exception {
        // Preparamos los datos de prueba
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombreCompleto("John Doe");
        cliente.setTelefono("1234567890");
        cliente.setCorreoElectronico("johndoe@example.com");

        // Simulamos la persistencia
        doNothing().when(entityManager).persist(cliente);
        
        // Ejecutamos el método y verificamos el resultado
        ClienteFrecuente result = clienteFrecuenteDAO.agregarCliente(cliente);
        assertNotNull(result);
        assertEquals("John Doe", result.getNombreCompleto());
        assertEquals("1234567890", result.getTelefono());
        assertEquals("johndoe@example.com", result.getCorreoElectronico());
    }

    @Test
    public void testBuscarCliente() throws Exception {
        // Preparamos los datos de prueba
        String nombre = "John";
        String telefono = "1234567890";
        String correo = "johndoe@example.com";
        
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombreCompleto("John Doe");
        cliente.setTelefono("1234567890");
        cliente.setCorreoElectronico("johndoe@example.com");

        // Simulamos la creación de una consulta
        Query mockQuery = mock(Query.class);
        when(entityManager.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of(cliente));

        // Ejecutamos el método de búsqueda
        List<ClienteFrecuente> result = clienteFrecuenteDAO.buscarCliente(nombre, telefono, correo);
        
        // Verificamos el resultado
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("John Doe", result.get(0).getNombreCompleto());
    }

    @Test
    public void testObtenerCliente() throws Exception {
        Long id = 1L;
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setId(id);
        cliente.setNombreCompleto("John Doe");

        // Simulamos la búsqueda de cliente por ID
        when(entityManager.find(ClienteFrecuente.class, id)).thenReturn(cliente);

        ClienteFrecuente result = clienteFrecuenteDAO.obtenerCliente(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("John Doe", result.getNombreCompleto());
    }

    @Test
    public void testObtenerTodos() throws Exception {
        // Preparamos los datos de prueba
        ClienteFrecuente cliente1 = new ClienteFrecuente();
        cliente1.setNombreCompleto("John Doe");
        
        ClienteFrecuente cliente2 = new ClienteFrecuente();
        cliente2.setNombreCompleto("Jane Doe");
        
        List<ClienteFrecuente> mockClientes = List.of(cliente1, cliente2);

        // Simulamos la creación de la consulta para obtener todos los clientes
        Query mockQuery = mock(Query.class);
        when(entityManager.createQuery("SELECT c FROM ClienteFrecuente c")).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(mockClientes);

        List<ClienteFrecuente> result = clienteFrecuenteDAO.obtenerTodos();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getNombreCompleto());
    }
}
 */