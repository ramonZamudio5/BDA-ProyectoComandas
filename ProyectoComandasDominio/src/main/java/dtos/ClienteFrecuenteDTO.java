/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.ClienteFrecuente;
import java.time.LocalDate;

/**
 *
 * @author Cricri
 */
public class ClienteFrecuenteDTO {
   
    private Long id;
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    private int puntosObtenidos;
    private double gastoTotalAcumulado;
    private int conteoVisitas;

    public ClienteFrecuenteDTO() {
    }

    public ClienteFrecuenteDTO(String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro, int puntosObtenidos, double gastoTotalAcumulado, int ventasAcumuladas) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    public ClienteFrecuenteDTO(String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
    }

    public ClienteFrecuenteDTO(String nombreCompleto, String telefono, String correoElectronico) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public ClienteFrecuenteDTO(Long id, String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro, int puntosObtenidos, double gastoTotalAcumulado, int conteoVisitas) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
  
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getPuntosObtenidos() {
        return puntosObtenidos;
    }

    public void setPuntosObtenidos(int puntosObtenidos) {
        this.puntosObtenidos = puntosObtenidos;
    }

    public double getGastoTotalAcumulado() {
        return gastoTotalAcumulado;
    }

    public void setGastoTotalAcumulado(double gastoTotalAcumulado) {
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    public int getConteoVisitas() {
        return conteoVisitas;
    }

    public void setConteoVisitas(int conteoVisitas) {
        this.conteoVisitas = conteoVisitas;
    }

    @Override
    public String toString() {
        return "ClienteFrecuente: " + "nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + ", fechaRegistro=" + fechaRegistro + ", puntosObtenidos=" + puntosObtenidos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", conteoVisitas=" + conteoVisitas + '.';
    }

   
    
    
    
    
}


    

