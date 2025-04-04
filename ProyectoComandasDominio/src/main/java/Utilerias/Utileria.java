/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Utileria {
    public static byte[] convertirImagenABytes(String ruta) throws IOException{
        File file= new File(ruta);
        return Files.readAllBytes(file.toPath());
    }
}
