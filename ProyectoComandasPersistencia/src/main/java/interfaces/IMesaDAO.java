/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.InsercionMasivaException;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IMesaDAO {
    public boolean insercionMasiva() throws InsercionMasivaException;
}
