/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Mesa;
import excepciones.InsercionMasivaException;
import excepciones.ObtenerMesasDispException;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IMesaDAO {
    public boolean insercionMasiva() throws InsercionMasivaException;
     public List<Mesa> obtenerMesasDisponibles() throws ObtenerMesasDispException;
}
