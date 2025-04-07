/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 *
 * @author Cricri
 */
@Entity
@Table(name = "Clientes")
@Inheritance(strategy = InheritanceType.JOINED) 
public class Cliente  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombreCompleto;

    @Column(nullable = false, unique = true)
    private String telefono;
    
    @Column(nullable = true)
    private String correoElectronico;
    
    @Column(nullable = false)
    private LocalDate fechaRegistro;
    
   

    @OneToMany(mappedBy = "cliente",fetch= FetchType.LAZY)
    private List<Comanda> comandas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
    }

    
    
    
    public Cliente(Long id, String nombreCompleto, String telefono, String correoElectronico, LocalDate fechaRegistro) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
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

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + ", fechaRegistro=" + fechaRegistro + ", comandas=" + comandas + '}';
    }
    
    
    
   
}

