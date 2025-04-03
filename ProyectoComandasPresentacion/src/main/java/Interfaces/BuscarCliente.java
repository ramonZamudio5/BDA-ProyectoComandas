/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cricri
 */
public class BuscarCliente extends JFrame {
  
    private JTextField searchField;
    private JCheckBox checkNombre, checkCorreo, checkTelefono;
    private JList<String> resultsList;
    private DefaultListModel<String> listModel;

    public BuscarCliente() {
     
        setTitle("Búsqueda de Cliente");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT)); 

        checkNombre = new JCheckBox("Buscar por Nombre");
        checkCorreo = new JCheckBox("Buscar por Correo");
        checkTelefono = new JCheckBox("Buscar por Teléfono");

        panelBusqueda.add(checkNombre);
        panelBusqueda.add(checkCorreo);
        panelBusqueda.add(checkTelefono);

   
        add(panelBusqueda, BorderLayout.NORTH);

    
        searchField = new JTextField(20);
        JPanel panelSearchField = new JPanel();
        panelSearchField.add(searchField);
        add(panelSearchField, BorderLayout.CENTER);

        
        listModel = new DefaultListModel<>();
        resultsList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(resultsList);
        add(scrollPane, BorderLayout.SOUTH);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BuscarCliente());
    }
}
