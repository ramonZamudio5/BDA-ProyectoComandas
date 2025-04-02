/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.ProductoDTO;
import entidades.Producto;
import entidades.Tipo;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoMapper {
    public static Producto toEntity(ProductoDTO productoDTO){
        return new Producto(productoDTO.getNombre(), productoDTO.getPrecio(), productoDTO.getTipoProducto(), productoDTO.getIngredientes());
    }
    
    public static ProductoDTO toDTO(Producto producto){
        return new ProductoDTO(producto.getNombre(), producto.getPrecio(), producto.getTipoProducto(), producto.getIngredientes());
    }
}
