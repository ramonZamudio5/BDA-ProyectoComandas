/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author Ramón Zamudio
 */
public class ClienteMesaDTO {
    private MesaDispDTO mesa;
    private ClienteFrecuenteDTO cliente;

    public ClienteMesaDTO() {
    }

    public ClienteMesaDTO(MesaDispDTO mesa, ClienteFrecuenteDTO cliente) {
        this.mesa = mesa;
        this.cliente = cliente;
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

    @Override
    public String toString() {
        return "ClienteMesaDTO{" + "mesa=" + mesa + ", cliente=" + cliente + '}';
    }
    
    
}
