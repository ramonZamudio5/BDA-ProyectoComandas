/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorDeObjetos;

//import Interfaces.AgregarProductoFrame;
//import Interfaces.BuscadorDeProductosFrame;
//import Interfaces.SeleccionarOpccionProductos;
import bos.ClienteFrecuenteBO;
import bos.ComandaBO;
import bos.DetalleComandaBO;
import bos.IngredienteBO;
import bos.MesaBO;
import bos.ProductoBO;
import daos.ClienteFrecuenteDAO;
import daos.ComandaDAO;
import daos.DetalleComandaDAO;
import daos.IngredienteDAO;
import daos.MesaDAO;
import daos.ProductoDAO;
import dtos.ClienteFrecuenteDTO;
import dtos.ComandaDTO;
import dtos.DetalleComandaDTO;
import dtos.IngredienteDTO;
import dtos.IngredienteSeleccionadoDTO;
import dtos.MesaDispDTO;
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
import excepciones.BusquedaComandaException;
import excepciones.EliminarProductoException;
import excepciones.NegocioException;
import excepciones.ObtenerMesasDispException;
import excepciones.RegistrarClienteException;
import interfaces.IComandaBO;
import interfaces.IDetalleComandaBO;
import interfaces.IIngredienteBO;
import interfaces.IManejadorDeObjetos;
import interfaces.IMesaBO;
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
    
    private final IngredienteBO ingredienteBO;
    private final ProductoBO productoBO;
    private final ClienteFrecuenteBO clienteFrecuenteBO;
    private List<IngredienteSeleccionadoDTO> ingredienteSeleccionado;
    private IMesaBO mesaBO;
    private IComandaBO comandaBO;
    private IDetalleComandaBO detalleComandaBO;
    

    public ManejadorDeObjetos() {
        IngredienteDAO ingredienteDAO = IngredienteDAO.getInstance();
        ProductoDAO productoDAO = ProductoDAO.getInstance();
        ClienteFrecuenteDAO clienteFrecuenteDAO = ClienteFrecuenteDAO.getInstance();
        MesaDAO mesadao = MesaDAO.getInstance();
        ComandaDAO comandaDAO = ComandaDAO.getInstance();
        DetalleComandaDAO detalleComandaDAO = DetalleComandaDAO.getInstance();
        this.ingredienteBO = new IngredienteBO(ingredienteDAO);
        this.productoBO = new ProductoBO(productoDAO);
        this.clienteFrecuenteBO = new ClienteFrecuenteBO(clienteFrecuenteDAO);
        this.mesaBO = new MesaBO(mesadao);
        this.comandaBO = new ComandaBO(comandaDAO);
        this.detalleComandaBO = new DetalleComandaBO(detalleComandaDAO);
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
    public ClienteFrecuenteDTO registrarCliente(ClienteFrecuenteDTO cliente) throws NegocioException{
        return clienteFrecuenteBO.agregarCliente(cliente);
    }
    
    @Override
      public List<ClienteFrecuenteDTO> buscarClientes(String nombre, String telefono, String correo)  throws NegocioException{
            return clienteFrecuenteBO.buscarClientes(nombre, telefono, correo);
      }
      
    @Override
      public ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException{
          return clienteFrecuenteBO.obtenerCliente(id);
      }
      
    @Override
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
    
    @Override
     public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws NegocioException{
         IngredienteDTO ingredienteActualizado= ingredienteBO.actualizarStock(idIngrediente, stock);
         return ingredienteActualizado;
        
     }
     
     
    @Override
     public List<IngredienteDTO> buscarIngredientes(String nombre, String medida) throws NegocioException{
       
         return ingredienteBO.buscarIngredientes(nombre, medida);
        
         
     }
     
     
     
    @Override
      public List<MesaDispDTO> obtenerMesasDisponibles(){
        try {
            return mesaBO.obtenerMesasDisponibles();
        } catch (NegocioException ex) {
            Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
    @Override
    public List<ComandaDTO> obtenerTodasComandas() {
        try {
             return comandaBO.ObtenerTodo();
        } catch (BusquedaComandaException ex) {
            Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ComandaDTO buscarComandaXFolio(String folio) {
        try {
            return comandaBO.buscarPorFolio(folio);
        } catch (BusquedaComandaException ex) {
            try {
                throw new NegocioException("Error al buscar por el folio");
            } catch (NegocioException ex1) {
                Logger.getLogger(ManejadorDeObjetos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;
    }
    
    public List<DetalleComandaDTO> obtenerTodosDetallesComanda() throws NegocioException{
        return detalleComandaBO.obtenerTodos();
    }
    
    public List<DetalleComandaDTO> obtenerDetalleComanda(Long id) throws NegocioException{
        return detalleComandaBO.obtenerDetallesPorComanda(id);
    }
      
      
      
      
}    
     

    
    
    

