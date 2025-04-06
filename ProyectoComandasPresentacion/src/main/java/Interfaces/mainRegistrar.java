/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import interfaces.IManejadorDeObjetos;
import manejadorDeObjetos.ManejadorDeObjetos;

/**
 *
 * @author Cricri
 */
public class mainRegistrar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         IManejadorDeObjetos manejador = new ManejadorDeObjetos(); 

    ControlNavegacion control = new ControlNavegacion(manejador);

    RegistarClienteFrecuente registrar = new RegistarClienteFrecuente(control);
    
   
    registrar.setVisible(true);
    }
    }
    

