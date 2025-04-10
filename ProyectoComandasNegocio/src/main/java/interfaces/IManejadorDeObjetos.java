/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import bos.ProductoBO;
import daos.ProductoDAO;
import excepciones.ActualizarStockException;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarClienteFrecuenteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.BuscarProductoException;
import excepciones.RegistrarClienteException;

import dtos.ClienteFrecuenteDTO;
import dtos.ComandaDTO;
import dtos.DetalleComandaDTO;
import dtos.IngredienteDTO;
import dtos.IngredienteSeleccionadoDTO;
import dtos.MesaDispDTO;
import dtos.ProductoDTO;
import entidades.ProductoIngrediente;
import enums.EstadoComanda;
import enums.Tipo;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IManejadorDeObjetos {
    //productos
    public abstract List<ProductoDTO> obtenerPorNombre(String nombre) throws NegocioException;
    public abstract List<ProductoDTO> obtenerPorTipo(Tipo tipo) throws NegocioException;
    public ProductoDTO obtenerProducto(Long id) throws NegocioException;
    public ProductoDTO actualizarProducto(ProductoDTO producto) throws NegocioException;
    public boolean eliminarProducto(String nombreProducto) throws NegocioException;
    public ProductoDTO AgregarProducto(ProductoDTO producto);
    //ingredientes
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException;
    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException;
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException;
    public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws NegocioException;
    public List<IngredienteDTO> buscarIngredientes(String nombre, String medida) throws NegocioException;
    //Clientes
    public List<ClienteFrecuenteDTO> buscarClientes(String nombre, String telefono, String correo)  throws NegocioException;
    public  ClienteFrecuenteDTO registrarCliente(ClienteFrecuenteDTO cliente) throws NegocioException;
    public List<ClienteFrecuenteDTO> obtenerTodos()throws NegocioException;
    public ClienteFrecuenteDTO obtenerCliente(Long id) throws NegocioException;
    //ProductoIngrediente
    public List<ProductoIngrediente> obtenerListaProductoIngrediente(List<IngredienteSeleccionadoDTO> ingrediente, ProductoDTO productoDto);
    //Mesa
    public List<MesaDispDTO>obtenerMesasDisponibles();
    //Comanda
    public List<ComandaDTO> obtenerTodasComandas();
    public ComandaDTO buscarComandaXFolio(String folio);
    //detallecomandas
    public List<DetalleComandaDTO> obtenerTodosDetallesComanda() throws NegocioException;
    public List<DetalleComandaDTO> obtenerDetalleComanda(Long id) throws NegocioException;
    
    public boolean actualizarEstadoComanda(ComandaDTO comanda)throws NegocioException;
     public LocalDateTime obtenerUltimaComanda(Long idCliente) throws NegocioException;

}
