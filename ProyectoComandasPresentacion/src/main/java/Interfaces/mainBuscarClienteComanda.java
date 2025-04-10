/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import dtos.MesaDispDTO;
import interfaces.IManejadorDeObjetos;
import manejadorDeObjetos.ManejadorDeObjetos;

/**
 *
 * @author Cricri
 */
public class mainBuscarClienteComanda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    IManejadorDeObjetos manejador = new ManejadorDeObjetos(); 
    ControlNavegacion control = new ControlNavegacion(manejador);
        MesaDispDTO mesa = new MesaDispDTO();
    BuscarClienteRegistradoComanda buscarCliente = new BuscarClienteRegistradoComanda(control,mesa);
    
   
    buscarCliente.setVisible(true);
    }
    
}
