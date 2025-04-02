/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Excepciones.NegocioException;
import dtos.ProductoDTO;
import entidades.Producto;
import interfaces.IProductoDAO;
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
        if(productoDTO.getPrecio() <= 0){
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
    
}
