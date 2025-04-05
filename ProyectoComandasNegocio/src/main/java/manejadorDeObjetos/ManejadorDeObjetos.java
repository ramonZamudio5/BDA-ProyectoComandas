/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorDeObjetos;

//import Interfaces.AgregarProductoFrame;
//import Interfaces.BuscadorDeProductosFrame;
//import Interfaces.SeleccionarOpccionProductos;
import bos.IngredienteBO;
import bos.ProductoBO;
import daos.IngredienteDAO;
import daos.ProductoDAO;
import dtos.IngredienteDTO;
import dtos.ProductoDTO;
import enums.Tipo;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import excepciones.NegocioException;
import interfaces.IIngredienteBO;
import interfaces.IManejadorDeObjetos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ramón Zamudio
 */
public class ManejadorDeObjetos implements IManejadorDeObjetos{
    
    private final IIngredienteBO ingredienteBO;
    private final ProductoBO productoBO;

    public ManejadorDeObjetos() {
        IngredienteDAO ingredienteDAO = IngredienteDAO.getInstance();
        ProductoDAO productoDAO = ProductoDAO.getInstance();
        this.ingredienteBO = new IngredienteBO(ingredienteDAO);
        this.productoBO = new ProductoBO(productoDAO);
    }
    
    
   
    public List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException{
       
        return productoBO.obtenerPorNombre(nombre);
    }
    
    public List<ProductoDTO> obtenerPorTipo(Tipo tipo) throws NegocioException, BuscarProductoException{
      
        return productoBO.buscarPorTipo(tipo);
    }
    
    public ProductoDTO obtenerProducto(Long id) throws NegocioException, BuscarProductoException, BuscarProductoException{
        
        return productoBO.obtenerProducto(id);
    }
    
    public ProductoDTO actualizarProducto(ProductoDTO producto) throws NegocioException{
       
        return productoBO.actualizarProducto(producto);
    }
    
    @Override
    public boolean eliminarProducto(String nombreProducto) throws NegocioException, BuscarProductoException{
       
        try {
            return productoBO.eliminarProductoPorNombre(nombreProducto);
        } catch (EliminarProductoException ex) {
            Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException{
         return ingredienteBO.buscarPorNombre(nombre);
     }
    
    
     public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException, BuscarPorMedidaException{
         return ingredienteBO.buscarPorMedida(medida);
     }
             
             
     public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException, AgregarIngredienteException{
         return ingredienteBO.agregarIngrediente(ingredienteDTO);
     }  
     
    
}
