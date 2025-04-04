/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramón Zamudio
 */
public class AgregarComandaException extends Exception{

    public AgregarComandaException() {
    }

    public AgregarComandaException(String message) {
        super(message);
    }

    public AgregarComandaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
