/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Ingrediente;
import enums.UnidadMedida;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.ConvertirTextoAUnidadException;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IIngredienteDAO {
 public Ingrediente agregarIngrediente(Ingrediente ingrediente) throws AgregarIngredienteException;
    public List<Ingrediente> buscarPorNombre(String nombre) throws BuscarPorNombreException;
  public List<Ingrediente> buscarPorMedida(String medida) throws BuscarPorMedidaException;
   public UnidadMedida convertirTextoAUnidad(String texto) throws ConvertirTextoAUnidadException;
}
