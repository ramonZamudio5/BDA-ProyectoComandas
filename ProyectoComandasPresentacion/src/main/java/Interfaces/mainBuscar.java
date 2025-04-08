/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import excepciones.NegocioException;
import interfaces.IManejadorDeObjetos;
import manejadorDeObjetos.ManejadorDeObjetos;

/**
 *
 * @author Cricri aaaaaaaaaaaaa
 */
public class mainBuscar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegocioException{

    IManejadorDeObjetos manejador = new ManejadorDeObjetos(); 
    ControlNavegacion control = new ControlNavegacion(manejador);

    BuscarCliente buscarCliente = new BuscarCliente(control);
    
   
    buscarCliente.setVisible(true);
    }
    }
    

