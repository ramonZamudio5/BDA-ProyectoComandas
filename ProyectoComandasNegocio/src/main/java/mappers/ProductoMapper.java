/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.ProductoDTO;
import entidades.Producto;
import enums.Tipo;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoMapper {
    /**
     * metodo que transforma un productoDTO a producto
     * @param productoDTO producto a ser transformado
     * @return regresa el producto ya transformado
     */
    public static Producto toEntity(ProductoDTO productoDTO){
        Producto producto = new Producto(productoDTO.getNombre(), productoDTO.getPrecio(), productoDTO.getTipoProducto() ,productoDTO.isEstado());
        producto.setIngredientes(productoDTO.getIngredientes());
        producto.setId(productoDTO.getId());
        return producto;
    }
    /**
     * metodo que transforma un producto a productoDTO
     * @param producto producto a ser transformado
     * @return regresa el producto ya transformado
     */
    public static ProductoDTO toDTO(Producto producto){
       return new ProductoDTO(producto.getNombre(), producto.getPrecio(), producto.getTipoProducto(), producto.getIngredientes(), producto.isEstado());
    }
    /**
     * transforma una lista de productos a productosDTO
     * @param productos lista deproductos a transformar
     * @return regresa el producto ya transformado
     */
    public static List<ProductoDTO> toListDTO(List<Producto> productos){
        List<ProductoDTO> productosDTO = new LinkedList<>();
        for(Producto producto : productos){
            productosDTO.add(new ProductoDTO(producto.getId(),producto.getNombre(),producto.getPrecio(), producto.getTipoProducto(), producto.getIngredientes(),producto.isEstado()));
        }
        return productosDTO;
    }
}
