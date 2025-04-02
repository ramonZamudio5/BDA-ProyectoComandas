/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import interfaces.IIngredienteBO;
import interfaces.IIngredienteDAO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class IngredienteBO implements IIngredienteBO {
    
    private IIngredienteDAO ingredienteDAO;
    
    public IngredienteBO(IIngredienteDAO ingredienteDAO){
        this.ingredienteDAO= ingredienteDAO;
    }
    
    
    
}
