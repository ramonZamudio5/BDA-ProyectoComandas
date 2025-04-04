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

/**
 *
 * @author Cricri
 */
@Entity
@DiscriminatorValue("ClienteFrecuente")
public class ClienteFrecuente extends Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Column(nullable = false)
    private int puntosObtenidos;
    
    @Column(nullable = false)
    private double gastoTotalAcumulado;
    
    @Column(nullable = false)
    private int ventasAcumuladas;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(int puntosObtenidos, double gastoTotalAcumulado, int ventasAcumuladas) {
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.ventasAcumuladas = ventasAcumuladas;
    }

    public ClienteFrecuente(int puntosObtenidos, double gastoTotalAcumulado, int ventasAcumuladas, Long id, String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        super(id, nombreCompleto, telefono, correoElectronico, fechaRegistro);
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.ventasAcumuladas = ventasAcumuladas;
    }
    

    public ClienteFrecuente(String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        super(nombreCompleto, telefono, correoElectronico, fechaRegistro);
    }

    public ClienteFrecuente(int puntosObtenidos, double gastoTotalAcumulado, int ventasAcumuladas, String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        super(nombreCompleto, telefono, correoElectronico, fechaRegistro);
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.ventasAcumuladas = ventasAcumuladas;
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

    public int getVentasAcumuladas() {
        return ventasAcumuladas;
    }

    public void setVentasAcumuladas(int ventasAcumuladas) {
        this.ventasAcumuladas = ventasAcumuladas;
    }

    @Override
    public String toString() {
        return "ClienteFrecuente{" + "puntosObtenidos=" + puntosObtenidos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", ventasAcumuladas=" + ventasAcumuladas + '}';
    }
    
    
    
    
    
}
