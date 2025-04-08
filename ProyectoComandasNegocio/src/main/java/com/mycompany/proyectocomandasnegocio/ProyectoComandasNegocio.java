/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectocomandasnegocio;

import static daos.ProductoDAO.productoDAO;
import dtos.ProductoDTO;
import entidades.Ingrediente;
import entidades.Producto;
import entidades.ProductoIngrediente;
import enums.Tipo;
import enums.UnidadMedida;
import excepciones.BuscarPorNombreException;

import bos.ProductoBO;
import daos.IngredienteDAO;
import daos.ProductoDAO;
import java.util.ArrayList;
import java.util.List;
import mappers.ProductoMapper;

import excepciones.AgregarIngredienteException;
import excepciones.AgregarProductoException;
import excepciones.NegocioException;
/**
 *
 * @author Ramón Zamudio
 */
public class ProyectoComandasNegocio {

    public static void main(String[] args) throws AgregarIngredienteException, NegocioException, AgregarProductoException {
        ProductoDAO productodao = ProductoDAO.getInstance();
        ProductoBO productoBO = new ProductoBO(productodao);
        IngredienteDAO ingredientedao = IngredienteDAO.getInstance();

//        Ingrediente ingrediente1 = new Ingrediente("tomate", 150.0, UnidadMedida.GRAMO);
//        ingredientedao.agregarIngrediente(ingrediente1);
//
//        ProductoDTO productoDTO = new ProductoDTO("Hamburguesa", 150.0, Tipo.PLATILLO, new ArrayList<>());
//        Producto producto = ProductoMapper.toEntity(productoDTO);
//
//        producto = productodao.agregarProducto(producto);
//
//        ProductoIngrediente productoIngrediente = new ProductoIngrediente();
//        productoIngrediente.setIngrediente(ingrediente1);
//        productoIngrediente.setCantidadRequerida(5.0);
//        productoIngrediente.setProducto(producto); 

//        producto.getIngredientes().add(productoIngrediente);
//
//        productoBO.agregarIngredientes(productoDTO);
//
//        System.out.println("Producto agregado con ingredientes.");
    }
}
