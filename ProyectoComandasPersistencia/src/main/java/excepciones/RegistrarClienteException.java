/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Cricri
 */
public class RegistrarClienteException extends Exception {
      
    
    public RegistrarClienteException() {
    }
    
    
    public RegistrarClienteException(String message) {
        super(message);
    }

    public RegistrarClienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
