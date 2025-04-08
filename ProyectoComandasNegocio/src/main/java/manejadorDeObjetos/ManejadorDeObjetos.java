/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorDeObjetos;

//import Interfaces.AgregarProductoFrame;
//import Interfaces.BuscadorDeProductosFrame;
//import Interfaces.SeleccionarOpccionProductos;
import bos.ClienteFrecuenteBO;
import bos.IngredienteBO;
import bos.ProductoBO;
import daos.ClienteFrecuenteDAO;
import daos.IngredienteDAO;
import daos.ProductoDAO;
import dtos.ClienteFrecuenteDTO;
import dtos.IngredienteDTO;
import dtos.IngredienteSeleccionadoDTO;
import dtos.ProductoDTO;
import entidades.Mesa;
import entidades.Producto;
import entidades.ProductoIngrediente;
import enums.Tipo;
import excepciones.ActualizarStockException;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarClienteFrecuenteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import excepciones.NegocioException;
import excepciones.ObtenerMesasDispException;
import excepciones.RegistrarClienteException;
import interfaces.IIngredienteBO;
import interfaces.IManejadorDeObjetos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.IngredienteMapper;


/**
 *
 * @author Ramón Zamudio
 */
public class ManejadorDeObjetos implements IManejadorDeObjetos{
    
    private final IIngredienteBO ingredienteBO;
    private final ProductoBO productoBO;
    private final ClienteFrecuenteBO clienteFrecuenteBO;
    private List<IngredienteSeleccionadoDTO> ingredienteSeleccionado;
   
    

    public ManejadorDeObjetos() {
        IngredienteDAO ingredienteDAO = IngredienteDAO.getInstance();
        ProductoDAO productoDAO = ProductoDAO.getInstance();
        ClienteFrecuenteDAO clienteFrecuenteDAO = ClienteFrecuenteDAO.getInstance();
        this.ingredienteBO = new IngredienteBO(ingredienteDAO);
        this.productoBO = new ProductoBO(productoDAO);
        this.clienteFrecuenteBO = new ClienteFrecuenteBO(clienteFrecuenteDAO);
        
    }
    
    
   
    public List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException{
       
        return productoBO.obtenerPorNombre(nombre);
    }
    
    public List<ProductoDTO> obtenerPorTipo(Tipo tipo) throws NegocioException{
      
        try {
            return productoBO.buscarPorTipo(tipo);
        } catch (BuscarProductoException ex) {
            Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public ProductoDTO obtenerProducto(Long id) throws NegocioException{
        
        try {
            return productoBO.obtenerProducto(id);
        } catch (BuscarProductoException ex) {
            Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ProductoDTO actualizarProducto(ProductoDTO producto) throws NegocioException{
       
        return productoBO.actualizarProducto(producto);
    }
    
    @Override
    public boolean eliminarProducto(String nombreProducto) throws NegocioException{
       
        try {
            return productoBO.eliminarProductoPorNombre(nombreProducto);
        } catch (EliminarProductoException ex) {
            Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException{
         return ingredienteBO.buscarPorNombre(nombre);
     }
    
    
    @Override
     public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException{
         return ingredienteBO.buscarPorMedida(medida);
     }
             
             
    @Override
     public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException{
         return ingredienteBO.agregarIngrediente(ingredienteDTO);
     }  

    public List<IngredienteSeleccionadoDTO> getIngredienteSeleccionado() {
        return ingredienteSeleccionado;
    }
     
     
      // Métodos de Cliente Frecuente
    @Override
    public List<ClienteFrecuenteDTO> buscarClientePorNombre(String nombre) throws NegocioException{
        return clienteFrecuenteBO.buscarPorNombre(nombre);
    }
    @Override
    public ClienteFrecuenteDTO buscarClientePorTelefono(String telefono) throws NegocioException {
        return clienteFrecuenteBO.buscarPorTelefono(telefono);
    }

    @Override
    public ClienteFrecuenteDTO buscarClientePorCorreo(String correo) throws NegocioException {
        return clienteFrecuenteBO.buscarPorCorreo(correo);
    }

  
    @Override
    public ClienteFrecuenteDTO registrarCliente(ClienteFrecuenteDTO cliente) throws NegocioException{
        return clienteFrecuenteBO.agregarCliente(cliente);
    }
    
    
     public  List<ClienteFrecuenteDTO>  obtenerTodos()throws NegocioException{
        return clienteFrecuenteBO.obtenerTodos();
     }

    @Override
    public List<ProductoIngrediente> obtenerListaProductoIngrediente(List<IngredienteSeleccionadoDTO> ingrediente, ProductoDTO productoDto) {
        List<ProductoIngrediente> productosIngredientes = new ArrayList<>();
        Producto productoNuevo = new Producto(productoDto.getNombre(), productoDto.getPrecio(), productoDto.getTipoProducto(), productoDto.isEstado());
            for(IngredienteSeleccionadoDTO ingredienteSeleDTO : ingrediente){
                productosIngredientes.add(new ProductoIngrediente(productoNuevo,IngredienteMapper.toEntity(ingredienteSeleDTO.getIngrediente()), ingredienteSeleDTO.getCantidad()));
            }
        return productosIngredientes;
    }

    @Override
    public ProductoDTO AgregarProducto(ProductoDTO producto) {
        try {
            return productoBO.agregarProducto(producto);
        } catch (NegocioException ex) {
            Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws NegocioException{
         IngredienteDTO ingredienteActualizado= ingredienteBO.actualizarStock(idIngrediente, stock);
         return ingredienteActualizado;
        
     }
     
//      public List<Mesa> obtenerMesasDisponibles() throws ObtenerMesasDispException{
//          try{
//              
//          }
//              
//          }
      }
         
     

    
    
    

