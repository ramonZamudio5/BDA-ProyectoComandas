/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.IngredienteDTO;
import excepciones.NegocioException;
import java.util.List;
import entidades.Ingrediente;
import excepciones.ActualizarStockException;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IIngredienteBO {
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException;
    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException;
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException;
    public IngredienteDTO buscarPorNombreUnico(String nombreIngrediente) throws NegocioException;
     public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws NegocioException;
     public List<IngredienteDTO> buscarIngredientes(String nombre, String medida) throws NegocioException;
}
