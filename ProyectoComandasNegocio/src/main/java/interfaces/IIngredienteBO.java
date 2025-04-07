/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.ActualizarStockException;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IIngredienteBO {
    public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException;
    public List<IngredienteDTO> buscarPorMedida(String medida) throws NegocioException, BuscarPorMedidaException;
    public IngredienteDTO agregarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException, AgregarIngredienteException;
    public IngredienteDTO buscarPorNombreUnico(String nombreIngrediente) throws BuscarIngredienteException;
     public IngredienteDTO actualizarStock(Long idIngrediente, Double stock) throws ActualizarStockException;
}
