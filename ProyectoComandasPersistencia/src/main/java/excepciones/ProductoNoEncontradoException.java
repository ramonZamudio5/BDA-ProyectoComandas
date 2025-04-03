/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramón Zamudio
 */
public class ProductoNoEncontradoException extends Exception {

    public ProductoNoEncontradoException() {
    }

    public ProductoNoEncontradoException(String message) {
        super(message);
    }

    public ProductoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
