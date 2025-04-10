/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import daos.DetalleComandaDAO;
import dtos.DetalleComandaDTO;
import entidades.DetalleComanda;
import excepciones.NegocioException;
import excepciones.ObtenerDetallesComandaException;
import interfaces.IDetalleComandaBO;
import java.util.List;
import mappers.DetalleComandaMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class DetalleComandaBO implements IDetalleComandaBO{

    private DetalleComandaDAO detalleComandaDao;
    
    public DetalleComandaBO(DetalleComandaDAO detalleComandaDao) {
        this.detalleComandaDao = detalleComandaDao;
    }
        
    @Override
    public List<DetalleComandaDTO> obtenerTodos() throws NegocioException {
        try{
            List<DetalleComanda> detallesComanda = detalleComandaDao.obtenerTodos();
            if(detallesComanda == null){
                throw new NullPointerException("La lista esta vacia");
            }
            return DetalleComandaMapper.toListDTO(detallesComanda);
        }catch(ObtenerDetallesComandaException e){
            throw new NegocioException("Error al obtener todos los detalles comanda"+e.getMessage());
        }
    }

    @Override
    public double obtenerImporteTotalPorComanda(Long idComanda) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetalleComandaDTO> obtenerDetallesPorComanda(Long idComanda) throws NegocioException {
        try{
            List<DetalleComanda> detallesComanda = detalleComandaDao.obtenerDetallesPorComanda(idComanda);
            if(detallesComanda == null){
                throw new NullPointerException("La lista esta vacia");
            }
            return DetalleComandaMapper.toListDTO(detallesComanda);
        }catch(ObtenerDetallesComandaException e){
            throw new NegocioException("Error al obtener todos los detalles comanda"+e.getMessage());
        }
    }
    
}
