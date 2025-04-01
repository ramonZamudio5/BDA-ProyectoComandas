/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectocomandaspersistencia;

import daos.ProductoDAO;
import entidades.Producto;
import entidades.Tipo;
import excepciones.AgregarProductoException;

/**
 *
 * @author Ramón Zamudio
 */
public class ProyectoComandasPersistencia {

    public static void main(String[] args) throws AgregarProductoException {
        Producto prod = new Producto("sshs", 5.0, Tipo.POSTRE);
        ProductoDAO dao = ProductoDAO.getInstance();
        dao.agregarProducto(prod);
        
    }
}
