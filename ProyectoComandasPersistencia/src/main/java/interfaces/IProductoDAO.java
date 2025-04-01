/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Producto;
import excepciones.AgregarProductoException;

/**
 *
 * @author Ramón Zamudio
 */
public interface IProductoDAO {
    public Producto agregarProducto(Producto producto)throws AgregarProductoException;
}
