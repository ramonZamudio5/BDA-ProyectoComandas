/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Ingrediente;
import entidades.Producto;
import entidades.ProductoIngrediente;
import enums.Tipo;
import excepciones.ActualizarProductoException;
import excepciones.AgregarIngredienteException;
import excepciones.AgregarProductoException;
import excepciones.AgregarProductoIngredienteException;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import excepciones.ProductoNoEncontradoException;
import interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoDAO implements IProductoDAO{
    
    public static ProductoDAO productoDAO;

    public ProductoDAO() {
    }
    
    public static ProductoDAO getInstance(){
        if(productoDAO == null){
            productoDAO = new ProductoDAO();
        }
        return productoDAO;
    }
    

    @Override
    public Producto agregarProducto(Producto producto) throws AgregarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            IngredienteDAO ingredienteDAO = new IngredienteDAO();

            for (ProductoIngrediente pi : producto.getIngredientes()) {
                if (pi.getIngrediente().getId() == null) {
                    Ingrediente ingredienteExistente = ingredienteDAO.buscarPorNombreYUnidad(
                            pi.getIngrediente().getNombre(),
                            pi.getIngrediente().getUnidadMedida()
                    );

                    if (ingredienteExistente != null) {
                        pi.setIngrediente(ingredienteExistente);
                    } else {
                        Ingrediente nuevoIngrediente = ingredienteDAO.agregarIngrediente(pi.getIngrediente());
                        pi.setIngrediente(nuevoIngrediente);
                    }
                }

                pi.setProducto(producto);
            }

            em.persist(producto);
            em.getTransaction().commit();

            if (producto.getId() == null) {
                throw new AgregarProductoException("No se generó ID para el producto.");
            }

            return producto;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AgregarProductoException("Error al agregar producto: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Producto obtenerProducto(Long id) throws BuscarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Producto.class, id);
        } catch (Exception e) {
            throw new BuscarProductoException("Error al buscar producto por ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String nombreProducto) throws BuscarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.estado = TRUE")
                     .setParameter("nombre", "%" + nombreProducto + "%")
                     .getResultList();
        } catch (Exception e) {
            throw new BuscarProductoException("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Producto buscarPorNombreUnico(String nombreProducto) throws BuscarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            return (Producto) em.createQuery("SELECT p FROM Producto p WHERE p.nombre = :nombre")
                     .setParameter("nombre", nombreProducto)
                     .getSingleResult();
        } catch (NoResultException e) {
            throw new BuscarProductoException("No se encontró el producto con nombre: " + nombreProducto);
        } catch (Exception e) {
            throw new BuscarProductoException("Error al buscar producto único: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> obtenerTodos() throws BuscarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p").getResultList();
        } catch (Exception e) {
            throw new BuscarProductoException("Error al obtener todos los productos: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Producto actualizarProducto(Producto producto) throws ActualizarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            System.out.println("entre a la dao");
            em.getTransaction().begin();
            System.out.println(producto.getId()+"hola");
            System.out.println(producto.getNombre());
            Producto existente = buscarPorNombreUnico(producto.getNombre());
            System.out.println(existente.getNombre());
            System.out.println(existente.getId());
            if (existente == null) {
                System.out.println("falle");
                throw new ActualizarProductoException("El producto no existe en la base de datos.");
            }
            existente.setNombre(producto.getNombre());
            existente.setPrecio(producto.getPrecio());
            existente.setTipoProducto(producto.getTipoProducto());
            existente.setEstado(producto.isEstado());
            System.out.println("voy a hacer merge");
            em.merge(existente);
            System.out.println("hice merge");
            System.out.println("hare commit");
            if (em.getTransaction().isActive()) {
                System.out.println("Transacción activa");
            } else {
                System.out.println("Transacción no activa");
            }
            em.getTransaction().commit();
            System.out.println("hice commit");
            System.out.println(producto);
            System.out.println(existente);
            return existente;

        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new ActualizarProductoException("Error al actualizar el producto: " + e.getMessage());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ActualizarProductoException("Error inesperado al actualizar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarProducto(long id) throws EliminarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Producto productoEliminar = em.find(Producto.class, id);
            if (productoEliminar == null) {
                throw new EliminarProductoException("No se encontró el producto con ID: " + id);
            }
            em.remove(productoEliminar);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new EliminarProductoException("Error al eliminar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarPorTipo(Tipo tipo) throws BuscarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            Query query = em.createQuery("SELECT p FROM Producto p WHERE p.tipoProducto  = :tipoProducto AND p.estado = TRUE");
            query.setParameter("tipoProducto", tipo );
            List<Producto> productos = query.getResultList();
            return productos;
        } catch (Exception e) {
            throw new BuscarProductoException("Error al buscar productos por tipo: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarProductoPorNombre(String nombreProducto) throws EliminarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Producto productoEliminar = buscarPorNombreUnico(nombreProducto);

            if (productoEliminar == null) {
                throw new EliminarProductoException("No se encontró el producto con nombre: " + nombreProducto);
            }

            productoEliminar = em.merge(productoEliminar);
            em.remove(productoEliminar);
            em.getTransaction().commit();
            return true;

        } catch (BuscarProductoException e) {
            em.getTransaction().rollback();
            throw new EliminarProductoException("No se pudo encontrar el producto: " + e.getMessage());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new EliminarProductoException("Error al eliminar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
