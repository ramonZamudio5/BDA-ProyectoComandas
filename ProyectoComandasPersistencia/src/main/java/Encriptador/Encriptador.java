/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encriptador;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Cricri
 */
public class Encriptador {
      
    private static final String LLAVE_SECRETA = "1234567890123456";

    public static String encriptar(String datos) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(LLAVE_SECRETA.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encriptado = cipher.doFinal(datos.getBytes());
        return Base64.getEncoder().encodeToString(encriptado);
    }

    public static String desencriptar(String datosEncriptados) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(LLAVE_SECRETA.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodificado = Base64.getDecoder().decode(datosEncriptados);
        byte[] original = cipher.doFinal(decodificado);
        return new String(original);
    }
}

