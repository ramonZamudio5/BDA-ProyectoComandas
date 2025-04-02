/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import excepciones.NegocioException;
import dtos.ProductoDTO;
import entidades.Producto;
import excepciones.BuscarProductoException;
import interfaces.IProductoDAO;
import java.util.List;
import mappers.ProductoMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoBO {
    
    private IProductoDAO productoDAO;
    
    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }
    
    public ProductoDTO agregarProducto(ProductoDTO productoDTO)throws NegocioException{
        if(productoDTO==null){
            throw new NegocioException("el producto no puede ser nulo");
        }
        if (productoDTO.getNombre() == null) {
           throw new NegocioException("el nombre del producto no puede ser nulo");
        }
        if(productoDTO.getIngredientes().isEmpty()){
           throw new NegocioException("los ingredientes del producto no pueden ser nulo");
        }
        if(productoDTO.getPrecio() < 0){
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
            throw new NegocioException("Error al añadir producto");
        }
    }   
    
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

    
}
