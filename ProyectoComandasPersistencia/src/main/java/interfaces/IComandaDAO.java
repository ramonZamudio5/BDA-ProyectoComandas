/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import conexion.Conexion;
import entidades.Comanda;
import entidades.DetalleComanda;
import enums.EstadoComanda;
import enums.Tipo;
import excepciones.AgregarComandaException;
import excepciones.BusquedaComandaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ramón Zamudio
 */
public interface IComandaDAO {
    public Comanda agregarComanda(Comanda comanda) throws AgregarComandaException;
    public List<Comanda> ObtenerTodo() throws BusquedaComandaException;
    public Comanda buscarPorFolio(String folio) throws BusquedaComandaException;
    public boolean actualizarComanda(Comanda comanda)throws BusquedaComandaException;
}
