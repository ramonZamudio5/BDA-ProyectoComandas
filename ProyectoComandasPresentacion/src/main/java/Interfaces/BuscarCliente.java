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
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author Cricri
 */

public class BuscarCliente extends JFrame {
   private JTextField textFieldNombre, textFieldTelefono, textFieldCorreo;
    private JTextArea textAreaResultados;
    private IClienteFrecuenteBO clienteFrecuenteBO;
    private JRadioButton rbNombre, rbTelefono, rbCorreo;
    private ButtonGroup grupoBusqueda;

    public BuscarCliente() {
        setTitle("Buscar Cliente Frecuente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        clienteFrecuenteBO = new ClienteFrecuenteBO(new ClienteFrecuenteDAO());

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new FlowLayout());

        rbNombre = new JRadioButton("Nombre");
        rbTelefono = new JRadioButton("Teléfono");
        rbCorreo = new JRadioButton("Correo");
        grupoBusqueda = new ButtonGroup();
        grupoBusqueda.add(rbNombre);
        grupoBusqueda.add(rbTelefono);
        grupoBusqueda.add(rbCorreo);

        panelOpciones.add(new JLabel("Buscar por:"));
        panelOpciones.add(rbNombre);
        panelOpciones.add(rbTelefono);
        panelOpciones.add(rbCorreo);

        add(panelOpciones, BorderLayout.NORTH);

     
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(4, 2));

        panelBusqueda.add(new JLabel("Nombre:"));
        textFieldNombre = new JTextField();
        textFieldNombre.setEnabled(false);  
        panelBusqueda.add(textFieldNombre);

        panelBusqueda.add(new JLabel("Teléfono:"));
        textFieldTelefono = new JTextField();
        textFieldTelefono.setEnabled(false);  
        panelBusqueda.add(textFieldTelefono);

        panelBusqueda.add(new JLabel("Correo:"));
        textFieldCorreo = new JTextField();
        textFieldCorreo.setEnabled(false);  
        panelBusqueda.add(textFieldCorreo);

        add(panelBusqueda, BorderLayout.CENTER);

   
        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);
        add(new JScrollPane(textAreaResultados), BorderLayout.SOUTH);

   
        DocumentListener actualizarBusqueda = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    realizarBusqueda();
                } catch (BuscarClienteFrecuenteException ex) {
                    Logger.getLogger(BuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    realizarBusqueda();
                } catch (BuscarClienteFrecuenteException ex) {
                    Logger.getLogger(BuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    realizarBusqueda();
                } catch (BuscarClienteFrecuenteException ex) {
                    Logger.getLogger(BuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        textFieldNombre.getDocument().addDocumentListener(actualizarBusqueda);
        textFieldTelefono.getDocument().addDocumentListener(actualizarBusqueda);
        textFieldCorreo.getDocument().addDocumentListener(actualizarBusqueda);

   
        ActionListener actualizarCampos = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                textFieldNombre.setEnabled(false);
                textFieldTelefono.setEnabled(false);
                textFieldCorreo.setEnabled(false);

       
                if (rbNombre.isSelected()) {
                    textFieldNombre.setEnabled(true);
                } else if (rbTelefono.isSelected()) {
                    textFieldTelefono.setEnabled(true);
                } else if (rbCorreo.isSelected()) {
                    textFieldCorreo.setEnabled(true);
                }

           
                if (!rbNombre.isSelected()) textFieldNombre.setText("");
                if (!rbTelefono.isSelected()) textFieldTelefono.setText("");
                if (!rbCorreo.isSelected()) textFieldCorreo.setText("");
            }
        };

        rbNombre.addActionListener(actualizarCampos);
        rbTelefono.addActionListener(actualizarCampos);
        rbCorreo.addActionListener(actualizarCampos);

        setVisible(true);
    }

    
    private void realizarBusqueda() throws BuscarClienteFrecuenteException {
        List<ClienteFrecuenteDTO> clientes = null;

        try {
            if (rbNombre.isSelected()) {
                String nombre = textFieldNombre.getText().trim();
                if (!nombre.isEmpty()) {
                    clientes = clienteFrecuenteBO.buscarPorNombre(nombre);
                }
            } else if (rbTelefono.isSelected()) {
                String telefono = textFieldTelefono.getText().trim();
                if (!telefono.isEmpty()) {
                    clientes = clienteFrecuenteBO.buscarPorTelefono(telefono);
                }
            } else if (rbCorreo.isSelected()) {
                String correo = textFieldCorreo.getText().trim();
                if (!correo.isEmpty()) {
                    clientes = clienteFrecuenteBO.buscarPorCorreo(correo);
                }
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
        SwingUtilities.invokeLater(() -> new BuscarCliente());
    }
}
