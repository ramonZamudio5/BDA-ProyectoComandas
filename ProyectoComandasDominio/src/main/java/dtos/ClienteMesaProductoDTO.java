/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ClienteMesaProductoDTO {
    private MesaDispDTO mesa;
    private ClienteFrecuenteDTO cliente;
    private List<DetalleComandaDTO> listaDetalleComanda;

    public ClienteMesaProductoDTO(MesaDispDTO mesa, ClienteFrecuenteDTO cliente, List<DetalleComandaDTO> listaDetalleComanda) {
        this.mesa = mesa;
        this.cliente = cliente;
        this.listaDetalleComanda = listaDetalleComanda;
    }

    public ClienteMesaProductoDTO() {
    }

    public MesaDispDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaDispDTO mesa) {
        this.mesa = mesa;
    }

    public ClienteFrecuenteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteFrecuenteDTO cliente) {
        this.cliente = cliente;
    }

    public List<DetalleComandaDTO> getListaDetalleComanda() {
        return listaDetalleComanda;
    }

    public void setListaDetalleComanda(List<DetalleComandaDTO> listaDetalleComanda) {
        this.listaDetalleComanda = listaDetalleComanda;
    }

    @Override
    public String toString() {
        return "ClienteMesaProductoDTO{" + "mesa=" + mesa + ", cliente=" + cliente + ", listaDetalleComanda=" + listaDetalleComanda + '}';
    }
    
}
