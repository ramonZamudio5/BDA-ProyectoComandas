/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramón Zamudio
 */
public class BuscarProductoException extends Exception{

    public BuscarProductoException() {
    }

    public BuscarProductoException(String message) {
        super(message);
    }

    public BuscarProductoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
