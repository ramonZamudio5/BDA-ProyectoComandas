/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlNavegacion;


import Interfaces.AgregarIngrediente;
import Interfaces.AgregarProductoFrame;
import Interfaces.AñadirProductoComanda;
import Interfaces.BuscadorDeProductosFrame;
import Interfaces.BuscarCliente;
//import Interfaces.BuscarClienteComand;
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
import Interfaces.BuscarMesaFrame;
import Interfaces.DetalleComandaUnica;

import Interfaces.BuscarCliente;
import Interfaces.EditarComanda;
import Interfaces.FechaReporteComanda;
import Interfaces.GestionComandas;
import Interfaces.OpcionReportesFrame;
import Interfaces.ReporteClientes;
import Interfaces.ReporteComandaFrame;
import Interfaces.ResumenComanda;
import Interfaces.opcionesModuloCliente;
import dtos.ClienteFrecuenteDTO;
import dtos.ClienteMesaDTO;
import dtos.ClienteMesaProductoDTO;
import dtos.ComandaDTO;
import dtos.DetalleComandaDTO;
import dtos.IngredienteDTO;
import dtos.IngredienteSeleccionadoDTO;
import dtos.MesaDispDTO;
import dtos.ProductoDTO;
import entidades.DetalleComanda;
import entidades.ProductoIngrediente;
import enums.Tipo;
import excepciones.NegocioException;
import interfaces.IManejadorDeObjetos;
import java.awt.Component;
import java.awt.Dialog;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
    public void openFormFechaReporteComanda(){
        new FechaReporteComanda(this).setVisible(true);
    }
    
    public void openFormAgregarIngrediente(){
        new AgregarIngrediente(this).setVisible(true);
    }
    
     public void  openFormBuscarCliente() throws NegocioException {
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
    public void openFormReporteComanda(){
        new ReporteComandaFrame(this).setVisible(true);
    }
     
   public void openFormReporteClientes(){
        new ReporteClientes(this).setVisible(true);
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
    
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException{
         try{
             IngredienteDTO ingredienteRegistrado= manejador.agregarIngrediente(ingredienteDTO);
             JOptionPane.showMessageDialog(null, "Ingrediente guardado correctamente");
             return ingredienteRegistrado;
         } catch(NegocioException ex){
             Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Error al guardar ingrediente: " +ex.getMessage(),
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

    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException{
      return manejador.buscarPorMedida(medida);
    }
     public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws NegocioException{
         return manejador.actualizarStock(idIngrediente, stock);
     }

    

    public List<ClienteFrecuenteDTO> obtenerTodosLosClientes() throws NegocioException{
       return manejador.obtenerTodos(); 
    }
    
    public List<ClienteFrecuenteDTO> buscarClientes(String nombre, String telefono, String correo)  throws NegocioException{
        return manejador.buscarClientes(nombre, telefono, correo);
    }
    
    
    
     public ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException{
         return manejador.obtenerCliente(id);
     }
     


    public ClienteFrecuenteDTO registrarCliente(ClienteFrecuenteDTO cliente) throws NegocioException {
        try {
            ClienteFrecuenteDTO clienteRegistrado = manejador.registrarCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
            return clienteRegistrado;
        } catch (NegocioException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + ex.getMessage(), 
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
    
    public List<MesaDispDTO> obtenerMesas(){
        return manejador.obtenerMesasDisponibles();
    }
    
    public List<ComandaDTO> obtenerTodasComandas(){
        List<ComandaDTO> comandas= manejador.obtenerTodasComandas();
        return comandas;
    }
    
    public ComandaDTO buscarPorFolio(String folio){
        return manejador.buscarComandaXFolio(folio);
    }
    
    public void openFormDetalleComandaUnica(String folio){
        new DetalleComandaUnica(this,folio).setVisible(true);
    }
    
    public void openFormBuscarMesa(){
        new BuscarMesaFrame(this).setVisible(true);
    }
    
    public void openFormgestionComandas(){
        new GestionComandas(this).setVisible(true);
    }
    
    public List<IngredienteDTO> buscarIngredientes(String nombre, String medida) throws NegocioException{
        return manejador.buscarIngredientes(nombre, medida);
    }
   
    
    public void openFormResumenComanda(ClienteMesaProductoDTO cmpDTO){
        new ResumenComanda(this,cmpDTO).setVisible(true);
    }
    
    public List<DetalleComandaDTO> obtenerTodosDetallesComanda() throws NegocioException{
        return manejador.obtenerTodosDetallesComanda();
    }
    
    public List<DetalleComandaDTO> obtenerDetalleComanda(Long id) throws NegocioException{
        return manejador.obtenerDetalleComanda(id);
}
    public void openFormAñadirProductoComanda(ClienteMesaDTO clienteMesa) {
           new AñadirProductoComanda(this,clienteMesa).setVisible(true);
    }
    
    public void openFormBuscarClienteRegistradoComanda(MesaDispDTO mesa){
        new BuscarCliente(this,mesa).setVisible(true);
    }
    
    public List<ClienteFrecuenteDTO> obtenerClientesPorParametros(String nombre, Integer visitasMinimas) throws NegocioException {
    List<ClienteFrecuenteDTO> clientes = manejador.obtenerTodos();
    return clientes.stream()
        .filter(c -> (nombre.isEmpty() || c.getNombreCompleto().toLowerCase().contains(nombre.toLowerCase())) &&
                     (visitasMinimas == null || c.getConteoVisitas() >= visitasMinimas))
        .collect(Collectors.toList());
    }
    public void openFormReporteClientes(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Filtrar Clientes Frecuentes", true);
        dialog.setContentPane(new ReporteClientes(this));
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
        public boolean actualizarComanda(ComandaDTO comanda)throws NegocioException{
        return manejador.actualizarEstadoComanda(comanda);
    }
    
    public void openFormEditarComanda(ComandaDTO comanda){
        new EditarComanda(this, comanda).setVisible(true);
    }
}
