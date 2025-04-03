/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadoresDeObjetosNegocio;

import bos.ClienteFrecuenteBO;
import daos.ClienteFrecuenteDAO;
import interfaces.IClienteFrecuenteBO;
import interfaces.IClienteFrecuenteDAO;

/**
 *
 * @author Cricri
 */
public class ManejadorObjetosNegocio {

    public static IClienteFrecuenteBO crearClienteFrecuenteBO() {
        
        IClienteFrecuenteDAO clienteFrecuenteDAO = ClienteFrecuenteDAO.getInstance();

    
        IClienteFrecuenteBO clienteFrecuenteBO = new ClienteFrecuenteBO(clienteFrecuenteDAO);

      
        return clienteFrecuenteBO;
    }
}

