/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import excepciones.NegocioException;
import dtos.ProductoDTO;
import entidades.Producto;
import enums.Tipo;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import interfaces.IProductoBO;
import interfaces.IProductoDAO;
import java.util.List;
import mappers.ProductoMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoBO implements IProductoBO{
    
    private IProductoDAO productoDAO;
    
    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }
    
    @Override
    public ProductoDTO agregarProducto(ProductoDTO productoDTO)throws NegocioException{
        if(productoDTO==null){
            throw new NegocioException("el producto no puede ser nulo");
        }
        if (productoDTO.getNombre() == null) {
           throw new NegocioException("el nombre del producto no puede ser nulo");
        }
        if(productoDTO.getPrecio() == null || productoDTO.getPrecio() < 0){
            throw new NegocioException("el precio del producto no puede ser menor a 0");
        }
        if(productoDTO.getTipoProducto()==null){
            throw new NegocioException("el tipo del producto no puede ser nulo");
        }
        
        Producto prodcutoAAñadir = ProductoMapper.toEntity(productoDTO);
        try{
            Producto productoAñadido = productoDAO.agregarProducto(prodcutoAAñadir);
            if(productoAñadido == null || productoAñadido.getId() == null){
                throw new NegocioException("El producto no se creo");
            }
            return ProductoMapper.toDTO(productoAñadido);
        }catch(Exception e){
            throw new NegocioException("Error al añadir producto"+e.getMessage());
        }
    }   
    
    @Override
    public ProductoDTO obtenerProducto(Long id)throws NegocioException, BuscarProductoException{
        if(id == null || id <= 0){
            throw new NegocioException("el id no puede ser nulo");
        }
        try{
            Producto productoBuscado = productoDAO.obtenerProducto(id);
            if(productoBuscado == null || productoBuscado.getId() == null){
                throw new NegocioException("el producto no puede ser nulo");
            }
            return ProductoMapper.toDTO(productoBuscado);
        }catch(Exception e){
            throw new NegocioException("Error al buscar el producto");
        }
    }
    
    @Override
    public List<ProductoDTO> obtenerPorNombre(String nombre)throws NegocioException{
        if(nombre == null){
            throw new NegocioException("el nombre del producto no puede ser nulo");
        }
        try{
            List<Producto> productosBuscados = productoDAO.buscarPorNombre(nombre);
            if(productosBuscados == null){
                throw new NegocioException("el producto no puede ser nulo");
            }
            for(Producto producto : productosBuscados){
                if(producto.getId()==null){
                    throw new NegocioException("el producto no puede ser nulo");
                }
            }
            return ProductoMapper.toListDTO(productosBuscados);
        }catch(Exception e){
            throw new NegocioException("Error al buscar el producto");
        }
    }
    
    @Override
    public List<ProductoDTO> obtenerTodos()throws NegocioException{
        try{
            List<Producto> productosBuscados = productoDAO.obtenerTodos();
            if(productosBuscados == null){
                throw new NegocioException("el producto no puede ser nulo");
            }
            for(Producto producto : productosBuscados){
                if(producto.getId()==null){
                    throw new NegocioException("el producto no puede ser nulo");
                }
            }
            return ProductoMapper.toListDTO(productosBuscados);
        }catch(Exception e){
            throw new NegocioException("Error al buscar el producto");
        }
    }
    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO)throws NegocioException{
        if(productoDTO==null){
            throw new NegocioException("el producto no puede ser nulo");
        }
        if (productoDTO.getNombre() == null) {
           throw new NegocioException("el nombre del producto no puede ser nulo");
        }
        if (productoDTO.getPrecio() == null || productoDTO.getPrecio() < 0) {
            throw new NegocioException("El precio del producto no puede ser nulo o menor a 0");
        }   
        if(productoDTO.getTipoProducto()==null){
            throw new NegocioException("el tipo del producto no puede ser nulo");
        }
        
        
        
        Producto prodcutoAActualizar = ProductoMapper.toEntity(productoDTO);
        try{
            Producto productoActualizado = productoDAO.actualizarProducto(prodcutoAActualizar);
            if(productoActualizado == null || productoActualizado.getId() == null){
                throw new NegocioException("El producto no se actualizo");
            }
            return ProductoMapper.toDTO(productoActualizado);
        }catch(Exception e){
            throw new NegocioException("Error al actualizar producto"+e.getMessage());
        }
    }
    
    @Override
    public boolean eliminarProducto(Long id)throws NegocioException, BuscarProductoException{
        if(id == null || id <= 0){
            throw new NegocioException("el id no puede ser nulo");
        }
        try{
            boolean eliminado = productoDAO.eliminarProducto(id);
            if(!eliminado){
                throw new NegocioException("el producto no puede ser nulo");
            }
            return true;
        }catch(Exception e){
            throw new NegocioException("Error al buscar el producto");
        }
    }
    
    
    @Override
    public List<ProductoDTO> buscarPorTipo(Tipo tipo) throws BuscarProductoException {
        if (tipo == null) {
            throw new BuscarProductoException("El tipo no puede ser nulo");
        }
        return ProductoMapper.toListDTO(productoDAO.buscarPorTipo(tipo));
    }
    
    public boolean eliminarProductoPorNombre(String nombreProducto) throws EliminarProductoException {
        if (nombreProducto == null) {
            throw new EliminarProductoException("El nombre del producto no puede ser null.");
        }
        if (nombreProducto.trim().isEmpty()) {
            throw new EliminarProductoException("El nombre del producto no puede estar vacío.");
        }
        if (nombreProducto.length() < 3) {
            throw new EliminarProductoException("El nombre del producto debe tener al menos 3 caracteres.");
        }
        return productoDAO.eliminarProductoPorNombre(nombreProducto);
    }

    
    
}
