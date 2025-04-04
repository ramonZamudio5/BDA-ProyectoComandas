/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.Cliente;
import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.EstadoComanda;
import entidades.Producto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ramón Zamudio
 */
public class ComandaDAOTest {
    
    public ComandaDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ComandaDAO.
     */
//    @Test
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        ComandaDAO expResult = ComandaDAO.comandaDAO;
//        ComandaDAO result = ComandaDAO.getInstance();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of agregarComanda method, of class ComandaDAO.
     */
    @Test
    public void testAgregarComanda() throws Exception {
        System.out.println("agregarComanda");
        EntityManager em = conexion.Conexion.crearConexion();
        Cliente cliente = em.find(Cliente.class, 1L);
        Producto producto = em.find(Producto.class, 1L);
        DetalleComanda detalle = new DetalleComanda(null, producto, 2, producto.getPrecio(), "Sin cebolla");
        Comanda comanda = new Comanda("123", LocalDateTime.now(), detalle.getImporte(), EstadoComanda.CERRADO, cliente, Collections.singletonList(detalle));
        ComandaDAO instance = new ComandaDAO();
        Comanda expResult = comanda;
        Comanda result = instance.agregarComanda(comanda);
        assertEquals(expResult, result);

    }
    
}
