/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import bos.ClienteFrecuenteBO;
import daos.ClienteFrecuenteDAO;
import dtos.ClienteFrecuenteDTO;
import entidades.ClienteFrecuente;
import excepciones.BuscarClienteFrecuenteException;
import excepciones.NegocioException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import manejadoresDeObjetosNegocio.ManejadorObjetosNegocio;
import interfaces.IClienteFrecuenteBO;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Cricri
 */

public class BuscarCliente extends JFrame {
    
    private JTextField textFieldNombre;
    private JTextField textFieldTelefono;
    private JTextField textFieldCorreo;
    private JButton buttonBuscar;
    private JTextArea textAreaResultados;

    private  IClienteFrecuenteBO clienteFrecuenteBO;

    public BuscarCliente() {
        
        setTitle("Buscar Cliente Frecuente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        clienteFrecuenteBO = new ClienteFrecuenteBO(new ClienteFrecuenteDAO());

      
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(4, 2));
        
        panelBusqueda.add(new JLabel("Nombre:"));
        textFieldNombre = new JTextField();
        panelBusqueda.add(textFieldNombre);
        
        panelBusqueda.add(new JLabel("Teléfono:"));
        textFieldTelefono = new JTextField();
        panelBusqueda.add(textFieldTelefono);
        
        panelBusqueda.add(new JLabel("Correo:"));
        textFieldCorreo = new JTextField();
        panelBusqueda.add(textFieldCorreo);
        
        buttonBuscar = new JButton("Buscar");
        panelBusqueda.add(buttonBuscar);
        
        add(panelBusqueda, BorderLayout.NORTH);
        
    
        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);
        add(new JScrollPane(textAreaResultados), BorderLayout.CENTER);
        
   
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarCliente();
                } catch (BuscarClienteFrecuenteException ex) {
                    Logger.getLogger(BuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        setVisible(true);
    }

    private void buscarCliente() throws BuscarClienteFrecuenteException {
        String nombre = textFieldNombre.getText().trim();
        String telefono = textFieldTelefono.getText().trim();
        String correo = textFieldCorreo.getText().trim();
        
        List<ClienteFrecuenteDTO> clientes = null;
        
        try {
            if (!nombre.isEmpty()) {
                clientes = clienteFrecuenteBO.buscarPorNombre(nombre);
            } else if (!telefono.isEmpty()) {
                clientes = clienteFrecuenteBO.buscarPorTelefono(telefono);
            } else if (!correo.isEmpty()) {
                clientes = clienteFrecuenteBO.buscarPorCorreo(correo);
            }
            
            if (clientes != null && !clientes.isEmpty()) {
                mostrarResultados(clientes);
            } else {
                textAreaResultados.setText("No se encontraron resultados.");
            }
        } catch (NegocioException ex) {
            textAreaResultados.setText("Error al realizar la búsqueda: " + ex.getMessage());
        }
    }
    
    private void mostrarResultados(List<ClienteFrecuenteDTO> clientes) {
        StringBuilder sb = new StringBuilder();
        
        for (ClienteFrecuenteDTO cliente : clientes) {
            sb.append(cliente.toString()).append("\n");
        }
        
        textAreaResultados.setText(sb.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuscarCliente();
            }
        });
    }
}
