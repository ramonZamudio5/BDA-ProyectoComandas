/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramón Zamudio
 */
public class BusquedaComandaException extends Exception{

    public BusquedaComandaException() {
    }

    public BusquedaComandaException(String message) {
        super(message);
    }

    public BusquedaComandaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
