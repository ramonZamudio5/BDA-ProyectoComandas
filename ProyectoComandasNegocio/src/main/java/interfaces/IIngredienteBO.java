/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.IngredienteDTO;
import excepciones.BuscarPorNombreException;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IIngredienteBO {
       public List<IngredienteDTO> buscarPorNombre(String nombre) throws NegocioException, BuscarPorNombreException;
}
