/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Cricri
 */
@Entity
public class ClienteFrecuente extends Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private int puntosObtenidos;
    
    @Column(nullable = false)
    private double gastoTotalAcumulado;
    
    @Column(nullable = false)
    private int ventasAcumuladas;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(Long id, int puntosObtenidos, double gastoTotalAcumulado, int ventasAcumuladas) {
        this.id = id;
        this.puntosObtenidos = puntosObtenidos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.ventasAcumuladas = ventasAcumuladas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "ClienteFrecuente{" + "id=" + id + ", puntosObtenidos=" + puntosObtenidos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", ventasAcumuladas=" + ventasAcumuladas + '}';
    }
    
    
    
    
    
}
