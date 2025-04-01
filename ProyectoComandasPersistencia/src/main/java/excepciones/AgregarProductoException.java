/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramón Zamudio
 */
public class AgregarProductoException extends Exception{

    public AgregarProductoException() {
    }

    public AgregarProductoException(String message) {
        super(message);
    }

    public AgregarProductoException(String message, Throwable cause) {
        super(message, cause);
        //.
    }
    
}
