/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Ingrediente;
import entidades.Producto;
import entidades.ProductoIngrediente;
import entidades.Tipo;
import entidades.UnidadMedida;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
public class ProductoDAOTest {
    
    public ProductoDAOTest() {
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
     * Test of getInstance method, of class ProductoDAO.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ProductoDAO expResult = ProductoDAO.productoDAO;
        ProductoDAO result = ProductoDAO.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarProducto method, of class ProductoDAO.
     */
    @Test
    public void testAgregarProducto() throws Exception {
        System.out.println("agregarProducto");
        Producto producto = new Producto("pizza", 50, Tipo.PLATILLO);
        ProductoDAO instance = new ProductoDAO();
        Producto expResult = producto;
        Producto result = instance.agregarProducto(producto);
        assertEquals(expResult, result);
    }
    /**
     * Test of obtenerProducto method, of class ProductoDAO.
     */
    @Test
    public void testObtenerProducto() throws Exception {
        System.out.println("obtenerProducto");
        long id = 501L;
        ProductoDAO instance = new ProductoDAO();
        EntityManager em = Conexion.crearConexion();
        Producto expResult = em.find(Producto.class, id);
        Producto result = instance.obtenerProducto(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarPorNombre method, of class ProductoDAO.
     */
    @Test
    public void testBuscarPorNombre() throws Exception {
        System.out.println("buscarPorNombre");
        String nombreProducto = "sandwiches";
        ProductoDAO instance = new ProductoDAO();
        EntityManager em = Conexion.crearConexion();
        Query query = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombreProducto");
        query.setParameter("nombreProducto", "%" + nombreProducto + "%");
        List<Producto> productos = query.getResultList();
        List<Producto> expResult = productos;
        List<Producto> result = instance.buscarPorNombre(nombreProducto);
        assertEquals(expResult, result);
    }
    //
    /**
     * Test of obtenerTodos method, of class ProductoDAO.
     */
    @Test
    public void testObtenerTodos() throws Exception {
        System.out.println("obtenerTodos");
        ProductoDAO instance = new ProductoDAO();
        EntityManager em = Conexion.crearConexion();
        List<Producto> expResult = em.createQuery("SELECT p FROM Producto p").getResultList();
        List<Producto> result = instance.obtenerTodos();
        assertEquals(expResult, result);
    }

    /**
     * Test of actualizarProducto method, of class ProductoDAO.
     */
    @Test
    public void testActualizarProducto() throws Exception {
        System.out.println("actualizarProducto");
        Producto productoActualizado = new Producto("nieve", 125, Tipo.POSTRE);
        Producto producto = productoActualizado;
        ProductoDAO instance = new ProductoDAO();
        Producto expResult = productoActualizado;
        Producto result = instance.actualizarProducto(producto);
        assertEquals(expResult, result);
    }
    //
    /**
     * Test of eliminarProducto method, of class ProductoDAO.
     */
    @Test
    public void testEliminarProducto() throws Exception {
        System.out.println("eliminarProducto");
        long id = 1L;
        ProductoDAO instance = new ProductoDAO();
        boolean expResult = true;
        boolean result = instance.eliminarProducto(id);
        assertEquals(expResult, result);
    } 

    /**
     * Test of agregarIngredientes method, of class ProductoDAO.
     */
    @Test
    public void testAgregarIngredientes() throws Exception {
        System.out.println("agregarIngredientes");
        ProductoDAO instance = new ProductoDAO();
        IngredienteDAO ingredientedao = IngredienteDAO.getInstance();
        String nombreProducto = "Hamburguesa";
        Producto producto = new Producto("Hamburguesa", 200, Tipo.PLATILLO);
        instance.agregarProducto(producto);
        Ingrediente ingrediente = new Ingrediente("Lechiga", 2.0, UnidadMedida.PIEZA);
        ingredientedao.agregarIngrediente(ingrediente);
        List<ProductoIngrediente> nuevosIngredientes = List.of(new ProductoIngrediente(producto, ingrediente, 1.0));
        
        boolean expResult = true;
        boolean result = instance.agregarIngredientes(nombreProducto, nuevosIngredientes);
        assertEquals(expResult, result);
    }
    
}
