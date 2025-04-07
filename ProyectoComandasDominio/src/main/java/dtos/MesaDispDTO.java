/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.EstadoMesa;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class MesaDispDTO {
    
    private Long id;
    private Integer numero;
    private EstadoMesa estado;

    public MesaDispDTO() {
    }

    public MesaDispDTO(Long id, Integer numero, EstadoMesa estado) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "MesaDispDTO{" + "id=" + id + ", numero=" + numero + ", estado=" + estado + '}';
    }
    
    
    
}
