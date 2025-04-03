/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.ClienteFrecuenteDTO;
import entidades.ClienteFrecuente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cricri
 */
public class ClienteFrecuenteMapper {
     
    public static ClienteFrecuente toEntity(ClienteFrecuenteDTO clienteDTO) {
        return new ClienteFrecuente(
                clienteDTO.getPuntosObtenidos(),
                clienteDTO.getGastoTotalAcumulado(),
                clienteDTO.getVentasAcumuladas(),
                clienteDTO.getNombreCompleto(),
                clienteDTO.getTelefono(),
                clienteDTO.getCorreoElectronico(),
                clienteDTO.getFechaRegistro()
        );
    }

    public static ClienteFrecuenteDTO toDTO(ClienteFrecuente cliente) {
        return new ClienteFrecuenteDTO(
                cliente.getNombreCompleto(),
                cliente.getTelefono(),
                cliente.getCorreoElectronico(),
                cliente.getFechaRegistro(),
                cliente.getPuntosObtenidos(),
                cliente.getGastoTotalAcumulado(),
                cliente.getVentasAcumuladas()
        );
    }

    public static List<ClienteFrecuenteDTO> toListDTO(List<ClienteFrecuente> clientes) {
        List<ClienteFrecuenteDTO> clientesDTO = new ArrayList<>();
        for (ClienteFrecuente cliente : clientes) {
            clientesDTO.add(toDTO(cliente));
        }
        return clientesDTO;
    }
}

