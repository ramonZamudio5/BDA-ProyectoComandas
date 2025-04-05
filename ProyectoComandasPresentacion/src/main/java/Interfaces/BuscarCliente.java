/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import dtos.ClienteFrecuenteDTO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Cricri
 */
public class BuscarCliente extends JFrame {
     private ControlNavegacion control;
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private JTextArea areaResultados;
    private JRadioButton rbNombre, rbTelefono, rbCorreo;
    private ButtonGroup grupoBusqueda;

    public BuscarCliente(ControlNavegacion control) {
        this.control = control;
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setTitle("Buscar Cliente Frecuente");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panelOpciones = new JPanel(new FlowLayout());
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

       
        JPanel panelCampos = new JPanel(new GridLayout(3, 2));
        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        txtNombre.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtCorreo.setEnabled(false);

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(txtTelefono);
        panelCampos.add(new JLabel("Correo:"));
        panelCampos.add(txtCorreo);
        add(panelCampos, BorderLayout.CENTER);

        // Área de resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        add(new JScrollPane(areaResultados), BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        ActionListener tipoBusquedaListener = e -> {
            txtNombre.setEnabled(rbNombre.isSelected());
            txtTelefono.setEnabled(rbTelefono.isSelected());
            txtCorreo.setEnabled(rbCorreo.isSelected());

            if (!rbNombre.isSelected()) txtNombre.setText("");
            if (!rbTelefono.isSelected()) txtTelefono.setText("");
            if (!rbCorreo.isSelected()) txtCorreo.setText("");
        };

        rbNombre.addActionListener(tipoBusquedaListener);
        rbTelefono.addActionListener(tipoBusquedaListener);
        rbCorreo.addActionListener(tipoBusquedaListener);

        DocumentListener buscarListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { buscar(); }
            public void removeUpdate(DocumentEvent e) { buscar(); }
            public void changedUpdate(DocumentEvent e) { buscar(); }
        };

        txtNombre.getDocument().addDocumentListener(buscarListener);
        txtTelefono.getDocument().addDocumentListener(buscarListener);
        txtCorreo.getDocument().addDocumentListener(buscarListener);
    }

    private void buscar() {
        try {
            List<ClienteFrecuenteDTO> resultados = null;

            if (rbNombre.isSelected() && !txtNombre.getText().trim().isEmpty()) {
                resultados = control.buscarClientesPorNombre(txtNombre.getText().trim());
            } else if (rbTelefono.isSelected() && !txtTelefono.getText().trim().isEmpty()) {
                resultados = control.buscarClientesPorTelefono(txtTelefono.getText().trim());
            } else if (rbCorreo.isSelected() && !txtCorreo.getText().trim().isEmpty()) {
                resultados = control.buscarClientesPorCorreo(txtCorreo.getText().trim());
            }

            if (resultados != null && !resultados.isEmpty()) {
                mostrarResultados(resultados);
            } else {
                areaResultados.setText("No se encontraron resultados.");
            }
        } catch (Exception e) {
            areaResultados.setText("Error al realizar la búsqueda: " + e.getMessage());
        }
    }

    private void mostrarResultados(List<ClienteFrecuenteDTO> clientes) {
        StringBuilder sb = new StringBuilder();
        for (ClienteFrecuenteDTO cliente : clientes) {
            sb.append(cliente.toString()).append("\n");
        }
        areaResultados.setText(sb.toString());
    }
    
    
}

