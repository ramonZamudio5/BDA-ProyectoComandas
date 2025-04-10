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
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoDAO implements IProductoDAO{
    
    public static ProductoDAO productoDAO;

    public ProductoDAO() {
    }
    /**
     * metodo que obtiene la instancia de clase 
     * @return regresa la instancia de la clase
     */
    public static ProductoDAO getInstance(){
        if(productoDAO == null){
            productoDAO = new ProductoDAO();
        }
        return productoDAO;
    }
    
    /**
     * metodo que agrega un producto a la base de datos
     * @param producto producto a persistirse
     * @return regresa el producto persistido en la base de datos
     * @throws AgregarProductoException tira la excepcion si hay un problema en el agregar
     */
    @Override
    public Producto agregarProducto(Producto producto) throws AgregarProductoException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();

            for (ProductoIngrediente pi : producto.getIngredientes()) {
                Ingrediente ing = pi.getIngrediente();
                List<Ingrediente> resultados = em.createQuery(
                    "FROM Ingrediente i WHERE i.nombre = :nombre AND i.unidadMedida = :unidadMedida"
                )
                .setParameter("nombre", ing.getNombre())
                .setParameter("unidadMedida", ing.getUnidadMedida())
                .getResultList();

                if (!resultados.isEmpty()) {
                    pi.setIngrediente((Ingrediente) resultados.get(0));
                } else {
                    em.persist(ing);
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
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new AgregarProductoException("Error al agregar producto: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    /**
     * metodo que obtiene un producto en especifico por su id
     * @param id identificador unico del producto
     * @return regresa el producto solicitado
     * @throws BuscarProductoException tira la excepcion si hubo un problema al buscar el producto
     */
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
    /**
     * metodo que busca todas los productos con similitudes en el nombre
     * @param nombreProducto nombre del producto que buscamos
     * @return  regresa una lista con todos los produtos que cumplan con el filtro
     * @throws BuscarProductoException  tira la excepcion si hubo un problema al buscar el producto
     */
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
    /**
     * metodo que busca un producto por su nombre
     * @param nombreProducto nombre del producto a buscar
     * @return regresa un solo producto el cual coincida con el solicitado
     * @throws BuscarProductoException tira la excepcion si hubo un problema al buscar el producto
     */
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
    /**
     * metodo que obtiene todos los productos de la base de datos
     * @return regresa todos los productos de la base de datos
     * @throws BuscarProductoException  tira la excepcion si hubo un problema al buscar el producto
     */
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
    /**
     * metodo que actualiza un producto 
     * @param producto producto con los datos a ser actualizados
     * @return regresa el producto actualizado
     * @throws ActualizarProductoException tira la excepcion si hubo un problema al actualizar el producto
     */
    @Override
    public Producto actualizarProducto(Producto producto) throws ActualizarProductoException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Producto existente = buscarPorNombreUnico(producto.getNombre());
            if (existente == null) {
                throw new ActualizarProductoException("El producto no existe en la base de datos.");
            }
            existente.setNombre(producto.getNombre());
            existente.setPrecio(producto.getPrecio());
            existente.setTipoProducto(producto.getTipoProducto());
            existente.setEstado(producto.isEstado());
            em.merge(existente);
            em.getTransaction().commit();
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
    /**
     * metodo que elimina un producto de la base de datos
     * @param id identificador unico del producto que se desea eliminar 
     * @return regresa el estado de la eliminacion true si se elimino y false si no 
     * @throws EliminarProductoException tira la excepcion si hubo un problema al eliminar el producto
     */
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
    /**
     * metodo que busca un producto por su tipo
     * @param tipo tipo por el cual se desea filtra el producto
     * @return  regresa una lista de todos los productos los cuales cumplen con el filtro
     * @throws BuscarProductoException tira la excepcion si hubo un problema al buscar el producto
     */
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
    /**
     * metodo que elimina un producto por su nombre 
     * @param nombreProducto
     * @return
     * @throws EliminarProductoException 
     */
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
