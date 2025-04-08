///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package daos;
//
//import conexion.Conexion;
//import entidades.Producto;
//import enums.Tipo;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author Ramón Zamudio
// */
//public class ProductoDAOTest {
//    
//    public ProductoDAOTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getInstance method, of class ProductoDAO.
//     */
//    @Test
//    public void testGetInstance() {
//    System.out.println("getInstance");
//
//    ProductoDAO instance1 = ProductoDAO.getInstance();
//    ProductoDAO instance2 = ProductoDAO.getInstance();
//
//    assertNotNull(instance1);
//
//    assertSame(instance1, instance2);
//}
//
//    /**
//     * Test of agregarProducto method, of class ProductoDAO.
//     */
//    @Test
//    public void testAgregarProducto() throws Exception {
//        System.out.println("agregarProducto");
//        
//        Producto producto = new Producto("nuggets", 300.0, Tipo.PLATILLO, true);
//        ProductoDAO instance = new ProductoDAO();
//        Producto expResult = producto;
//        Producto result = instance.agregarProducto(producto);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of obtenerProducto method, of class ProductoDAO.
//     */
//    @Test
//    public void testObtenerProducto() throws Exception {
//        System.out.println("obtenerProducto");
//        EntityManager em = Conexion.crearConexion();
//        Long id = 3L;
//        ProductoDAO instance = new ProductoDAO();
//        Producto expResult = em.find(Producto.class, id);
//        Producto result = instance.obtenerProducto(id);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of buscarPorNombre method, of class ProductoDAO.
//     */
//    @Test
//    public void testBuscarPorNombre() throws Exception {
//        System.out.println("buscarPorNombre");
//        EntityManager em = Conexion.crearConexion();
//        String nombreProducto = "picsa";
//        ProductoDAO instance = new ProductoDAO();
//        List<Producto> expResult = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.estado = TRUE")
//                     .setParameter("nombre", "%" + nombreProducto + "%")
//                     .getResultList();
//        List<Producto> result = instance.buscarPorNombre(nombreProducto);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of buscarPorNombreUnico method, of class ProductoDAO.
//     */
//    @Test
//    public void testBuscarPorNombreUnico() throws Exception {
//        System.out.println("buscarPorNombreUnico");
//        EntityManager em = Conexion.crearConexion();
//        ProductoDAO instance = ProductoDAO.getInstance();
//        String nombreProducto = "Picsa";
//        Producto expResult = (Producto) em.createQuery("SELECT p FROM Producto p WHERE p.nombre = :nombre")
//                     .setParameter("nombre", nombreProducto)
//                     .getSingleResult();
//        Producto result = instance.buscarPorNombreUnico(nombreProducto);
//        assertEquals(expResult, result);
//        }
//
//    /**
//     * Test of obtenerTodos method, of class ProductoDAO.
//     */
//    @Test
//    public void testObtenerTodos() throws Exception {
//        System.out.println("obtenerTodos");
//        EntityManager em = Conexion.crearConexion();
//        ProductoDAO instance = new ProductoDAO();
//        List<Producto> expResult = em.createQuery("SELECT p FROM Producto p").getResultList();;
//        List<Producto> result = instance.obtenerTodos();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of actualizarProducto method, of class ProductoDAO.
//     */
//    @Test
//    public void testActualizarProducto() throws Exception {
//        System.out.println("actualizarProducto");
//        Producto producto = new Producto("Atun", 150.0, true);
//        ProductoDAO instance = new ProductoDAO();
//        Producto expResult = producto;
//        Producto result = instance.actualizarProducto(producto);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of eliminarProducto method, of class ProductoDAO.
//     */
//    @Test
//    public void testEliminarProducto() throws Exception {
//        System.out.println("eliminarProducto");
//        long id = 25L;
//        ProductoDAO instance = new ProductoDAO();
//        boolean expResult = true;
//        boolean result = instance.eliminarProducto(id);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of buscarPorTipo method, of class ProductoDAO.
//     */
//    @Test
//    public void testBuscarPorTipo() throws Exception {
//        System.out.println("buscarPorTipo");
//        Tipo tipo = Tipo.BEBIDA;
//        EntityManager em = Conexion.crearConexion();
//        ProductoDAO instance = new ProductoDAO();
//        Query query = em.createQuery("SELECT p FROM Producto p WHERE p.tipoProducto  = :tipoProducto AND p.estado = TRUE").setParameter("tipoProducto", tipo );
//        List<Producto> productos = query.getResultList();
//        List<Producto> expResult = productos;
//        List<Producto> result = instance.buscarPorTipo(tipo);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of eliminarProductoPorNombre method, of class ProductoDAO.
//     */
//    @Test
//    public void testEliminarProductoPorNombre() throws Exception {
//        System.out.println("eliminarProductoPorNombre");
//        String nombreProducto = "pizza";
//        ProductoDAO instance = new ProductoDAO();
//        boolean expResult = true;
//        boolean result = instance.eliminarProductoPorNombre(nombreProducto);
//        assertEquals(expResult, result);
//    }
//    
//}
