/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;

/**
 *
 * @author Cricri
 */
public class ClienteFrecuenteDTO {
   
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;

    // Nuevos campos
    private int puntosObtenidos;
    private double gastoTotalAcumulado;
    private int ventasAcumuladas;

    public ClienteFrecuenteDTO() {
    }

    // Constructor actualizado con todos los parámetros
    public ClienteFrecuenteDTO(String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro,
                               int puntosObtenidos, double gastoTotalAcumulado, int ventasAcumuladas) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.ventasAcumuladas = ventasAcumuladas;
    }

    // Getters y setters para los nuevos atributos
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

    public int getVentasAcumuladas() {
        return ventasAcumuladas;
    }

    public void setVentasAcumuladas(int ventasAcumuladas) {
        this.ventasAcumuladas = ventasAcumuladas;
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

    @Override
    public String toString() {
        return "ClienteFrecuenteDTO{" + "nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + 
               ", fechaRegistro=" + fechaRegistro + ", puntosObtenidos=" + puntosObtenidos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + 
               ", ventasAcumuladas=" + ventasAcumuladas + '}';
    }
}


    

