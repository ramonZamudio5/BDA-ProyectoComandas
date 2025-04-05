/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Cricri
 */
@Entity
@DiscriminatorValue("ClienteFrecuente")
public class ClienteFrecuente extends Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Transient
    private int puntosObtenidos;
    
    @Transient
    private double gastoTotalAcumulado;
    @Transient
    private int conteoVisitas;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(int puntosObtenidos, double gastoTotalAcumulado, int conteoVisitas) {
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    public ClienteFrecuente(int puntosObtenidos, double gastoTotalAcumulado, int conteoVisitas, Long id, String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        super(id, nombreCompleto, telefono, correoElectronico, fechaRegistro);
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }
    

    public ClienteFrecuente(String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        super(nombreCompleto, telefono, correoElectronico, fechaRegistro);
    }

    public ClienteFrecuente(int puntosObtenidos, double gastoTotalAcumulado, int conteoVisitas, String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        super(nombreCompleto, telefono, correoElectronico, fechaRegistro);
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas= conteoVisitas;
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
        return "ClienteFrecuente{" + "puntosObtenidos=" + puntosObtenidos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", conteoVisitas=" + conteoVisitas + '}';
    }
    
    
    
    
    
    
    
}
