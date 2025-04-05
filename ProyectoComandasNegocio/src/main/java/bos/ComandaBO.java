/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import daos.ComandaDAO;
import dtos.ComandaDTO;
import entidades.Comanda;
import excepciones.AgregarComandaException;
import excepciones.BusquedaComandaException;
import excepciones.NegocioException;
import interfaces.IComandaBO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import mappers.ComandaMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class ComandaBO implements IComandaBO{
    
    private ComandaDAO comandaDAO;
    
    public ComandaBO(ComandaDAO comandaDAO) {
        this.comandaDAO = comandaDAO;
    }
    @Override
    public ComandaDTO agregarComanda(ComandaDTO comanda) throws AgregarComandaException {
        if(comanda == null){
            throw new NullPointerException("La comanda no puede ser nula");
        }
        if(comanda.getTotalVenta()==null || comanda.getTotalVenta() < 0){
            throw new IllegalArgumentException("El total no puede ser nulo o negativo");
        }
        if(comanda.getCliente() == null){
            throw new NullPointerException("EL cliente no puede ser nulo");
        }
        if (comanda.getDetalles() == null || comanda.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("Los detalles no pueden ser nulos o vacíos");
        }
        if(comanda.getEstado() == null){
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        if(comanda.getFolio().isEmpty()||!validarFolio(comanda.getFolio())){
            throw new IllegalArgumentException("El folio no puede estar vacío o ser inválido");
        }
        if (comanda.getFechaHoraCreacion() == null) {
            throw new IllegalArgumentException("La fecha de creación no puede ser nula");
        }
        if (comanda.getFechaHoraCreacion().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de creación no puede ser en el futuro");
        }
        Comanda comandaAAgregar = ComandaMapper.toEntity(comanda);
        try{
            Comanda comandaAgregada = comandaDAO.agregarComanda(comandaAAgregar);
            if(comandaAgregada == null || comandaAgregada.getId() == null){
                throw new NegocioException("El producto no se creo");
            }
            return ComandaMapper.toDTO(comandaAgregada);
        }catch(Exception e){
            throw new AgregarComandaException("Error al agregar la comanda"+e);
        }
    }

    @Override
    public List<ComandaDTO> ObtenerTodo() throws BusquedaComandaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ComandaDTO buscarPorFolio(String folio) throws BusquedaComandaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public static boolean validarFolio(String folio) {
        String patron = "^OB-\\d{4}\\d{2}\\d{2}-\\d{1,19}$";
        if (folio.matches(patron)) {
            String fecha = folio.substring(3, 11); 
            String identificador = folio.substring(12);
            int anio = Integer.parseInt(fecha.substring(0, 4));
            int mes = Integer.parseInt(fecha.substring(4, 6));
            int dia = Integer.parseInt(fecha.substring(6, 8));
            if (mes < 1 || mes > 12) {
                return false;
            }
            if (dia < 1 || dia > 31) {
                return false;
            }
            if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                return false;
            }
            if (mes == 2) {
                if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)) {
                    if (dia > 29) {
                        return false;
                    }
                } else {
                    if (dia > 28) {
                        return false;
                    }
                }
            }
            try {
                Long.parseLong(identificador);
            } catch (NumberFormatException e) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }
}
