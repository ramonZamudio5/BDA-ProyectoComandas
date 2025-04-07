/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlNavegacion;


import Interfaces.AgregarIngrediente;
import Interfaces.AgregarProductoFrame;
import Interfaces.BuscadorDeProductosFrame;
import Interfaces.BuscarCliente;
import Interfaces.BuscarIngrediente;
import Interfaces.EditarProductoFrame;
import Interfaces.EliminarProducto;
import Interfaces.PantallaAdministrador;
import Interfaces.PantallaMeseroComandas;
import Interfaces.PantallaPrincipalRol;
import Interfaces.RegistarClienteFrecuente;
import Interfaces.SeleccionarOpccionProductos;
import Interfaces.BuscarIngredienteComanda;
import Interfaces.BuscarIngredienteComandaActualizarProducto;
import Interfaces.BuscarIngredienteComandaAñadirProducto;
import Interfaces.OpcionReportesFrame;
import Interfaces.opcionesModuloCliente;
import dtos.ClienteFrecuenteDTO;
import dtos.IngredienteDTO;
import dtos.IngredienteSeleccionadoDTO;
import dtos.ProductoDTO;
import entidades.ProductoIngrediente;
import enums.Tipo;
import excepciones.ActualizarProductoException;
import excepciones.ActualizarStockException;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarClienteFrecuenteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.BuscarProductoException;
import excepciones.EliminarProductoException;
import excepciones.NegocioException;
import excepciones.RegistrarClienteException;
import interfaces.IManejadorDeObjetos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramón Zamudio
 */
public class ControlNavegacion {
    IManejadorDeObjetos manejador;
    ProductoDTO productoDto;
   
    

    public ControlNavegacion(IManejadorDeObjetos manejador) {
        this.manejador = manejador;
    }
    public void openFormSeleccionarOpccionProducto(){
        new SeleccionarOpccionProductos(this).setVisible(true);
    }
    public void openFormAgregarProductoFrame(){
        new AgregarProductoFrame(this).setVisible(true);
    }
    public void openFormBuscadorProductosFrame(){
        new BuscadorDeProductosFrame(this).setVisible(true);
    }
    public void openFormEditarProdutoFrame(ProductoDTO producto){
        new EditarProductoFrame(this,producto).setVisible(true);
    }
    public void openFormEliminarProductoFrame(){
        new EliminarProducto(this).setVisible(true);
    }
    public void openFormPantallaPrincipalRol(){
        new PantallaPrincipalRol(this).setVisible(true);
    }
    public void openFormPantallaMeseroComandas(){
        new PantallaMeseroComandas(this).setVisible(true);
    }
    public void openFormPantallaAdministrador(){
        new PantallaAdministrador(this).setVisible(true);
    }
    public void openFormBuscarIngrediente(){
        new BuscarIngrediente(this).setVisible(true);
    }
    
    public void openFormAgregarIngrediente(){
        new AgregarIngrediente(this).setVisible(true);
    }
    
     public void  openFormBuscarCliente() throws BuscarClienteFrecuenteException{
           new BuscarCliente(this).setVisible(true);
     }
    
    public void openFormRegistrarCliente() {
      new RegistarClienteFrecuente(this).setVisible(true);
  }
    public void openFormBuscarIngredienteComanda(){
        new BuscarIngredienteComanda(this).setVisible(true);
    }
    
    public void openFormOpcionesModuloCliente(){
        new opcionesModuloCliente(this).setVisible(true);
    }
     
     
    public List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException{
        try{
            return manejador.obtenerPorNombre(nombre);
        }catch(Exception e){
            throw new NegocioException("Error al buscar el producto", e);
        }   
    }
    
    public List<ProductoDTO> obtenerPorTipo(Tipo tipo) throws NegocioException{
        try{
            return manejador.obtenerPorTipo(tipo);
        }catch(Exception e){
            throw new NegocioException("Error al buscar el producto", e);
        }   
    }
    
    public ProductoDTO obtenerProducto(Long id) throws NegocioException{
        try{
            return manejador.obtenerProducto(id);
        }catch(Exception e){
            throw new NegocioException("Error al buscar el producto", e);
        }
    }
    
    public ProductoDTO actualiarProduto(ProductoDTO producto) throws NegocioException{
        try{
            return manejador.actualizarProducto(producto);
        }catch(Exception e){
            throw new NegocioException("Error al actualizar el producto");
        }
    }

    public void MostrarMensajeParaEliminar(String mensaje,String nombreProducto) {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            try {
                boolean eliminado = eliminarProducto(nombreProducto);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
        }
    }
    
    public boolean eliminarProducto(String nombreProducto) throws NegocioException{
        try{
            return manejador.eliminarProducto(nombreProducto);
        }catch(Exception e){
            throw new NegocioException("Error al actualizar el producto");
        }
    }
    
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException, AgregarIngredienteException{
         try{
             IngredienteDTO ingredienteRegistrado= manejador.agregarIngrediente(ingredienteDTO);
             JOptionPane.showMessageDialog(null, "Ingrediente guardado correctamente");
             return ingredienteRegistrado;
         } catch(AgregarIngredienteException ex){
             Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Error al guardar ingrediente: " +ex.getMessage(),
                     "ERROR", JOptionPane.WARNING_MESSAGE);
         } catch(NegocioException ex){
             Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Error de negocio al guardar ingrediente: " + ex.getMessage(),
                "ERROR", JOptionPane.WARNING_MESSAGE);

         }
         return new IngredienteDTO();
    }

    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException{
        try{
            return manejador.buscarPorNombre(nombre);
        } catch (Exception e){
            throw new NegocioException("Error al buscar ingrediente");
        }
    }

    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException, BuscarPorMedidaException{
      return manejador.buscarPorMedida(medida);
    }
     public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws NegocioException, ActualizarStockException{
         return manejador.actualizarStock(idIngrediente, stock);
     }

        public List<ClienteFrecuenteDTO> buscarClientesPorNombre(String nombre) throws NegocioException, BuscarClienteFrecuenteException {
      return manejador.buscarClientePorNombre(nombre);
    }

    public ClienteFrecuenteDTO buscarClientesPorTelefono(String telefono) throws NegocioException, BuscarClienteFrecuenteException {
      return manejador.buscarClientePorTelefono(telefono);
    }

    public ClienteFrecuenteDTO buscarClientesPorCorreo(String correo) throws NegocioException, BuscarClienteFrecuenteException {
      return manejador.buscarClientePorCorreo(correo);
    }

    public List<ClienteFrecuenteDTO> obtenerTodosLosClientes() throws NegocioException, BuscarClienteFrecuenteException {
       return manejador.obtenerTodos(); 
    }


    public ClienteFrecuenteDTO registrarCliente(ClienteFrecuenteDTO cliente) throws NegocioException, RegistrarClienteException {
        try {
            ClienteFrecuenteDTO clienteRegistrado = manejador.registrarCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
            return clienteRegistrado;
        } catch (RegistrarClienteException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + ex.getMessage(), 
                  "ERROR", JOptionPane.WARNING_MESSAGE);
            throw ex;
        }catch (NegocioException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error de negocio al registrar cliente: " + ex.getMessage(),
                "ERROR", JOptionPane.WARNING_MESSAGE);
            throw ex;
        }
    }

    public void openFormBuscarIngredienteComandaAñadirProducto(ProductoDTO producto){
        new BuscarIngredienteComandaAñadirProducto(this, producto).setVisible(true);
    }

    public ProductoDTO getProductoDao() {
        return productoDto;
    }

    public void setProductoDao(ProductoDTO productoDao) {
        this.productoDto = productoDao;
    }

    public List<ProductoIngrediente> obtenerListaProductoIngrediente(List<IngredienteSeleccionadoDTO> ingrediente, ProductoDTO productoDto){
        return manejador.obtenerListaProductoIngrediente(ingrediente, productoDto);
    }
    
    public ProductoDTO agregarProducto(ProductoDTO producto){
        return manejador.AgregarProducto(producto);
    }
    
    public void openFormopenFormBuscarIngredienteComandaActualizarProducto(ProductoDTO producto){
        new BuscarIngredienteComandaActualizarProducto(this, producto).setVisible(true);
    }
    
    public void openFormOpcionReportesFrame(){
        new OpcionReportesFrame(this).setVisible(true);
    }
}
     

