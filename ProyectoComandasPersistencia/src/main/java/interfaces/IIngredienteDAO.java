/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Ingrediente;
import excepciones.AgregarIngredienteException;
import excepciones.BuscarPorNombreException;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IIngredienteDAO {
 public Ingrediente agregarIngrediente(Ingrediente ingrediente) throws AgregarIngredienteException;
    public List<Ingrediente> buscarPorNombre(String nombre) throws BuscarPorNombreException;
}
