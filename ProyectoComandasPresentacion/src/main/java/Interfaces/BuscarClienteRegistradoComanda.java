/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import dtos.ClienteFrecuenteDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Cricri
 */
public class BuscarClienteRegistradoComanda extends JFrame {
     private ControlNavegacion control;
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private JTextArea areaResultados;
    private JComboBox<String> tipoBusquedaComboBox;
    private JButton btnSiguiente, btnContinuarSinCliente;

    private ClienteFrecuenteDTO clienteSeleccionado = null; // Para almacenar el cliente seleccionado

    public BuscarClienteRegistradoComanda(ControlNavegacion control) {
        this.control = control;
        inicializarComponentes();
        configurarEventos();
        cargarClientesIniciales();
    }

    private void inicializarComponentes() {
        setTitle("Buscador de Clientes");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(255, 253, 208)); 

        JLabel titulo = new JLabel("Buscador de Clientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(34, 139, 34)); 
        add(titulo, BorderLayout.NORTH);

      
        JPanel panelCampos = new JPanel(new GridLayout(5, 2));
        panelCampos.setBackground(new Color(255, 253, 208));

        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        tipoBusquedaComboBox = new JComboBox<>(new String[]{"Nombre", "Teléfono", "Correo"});

        txtNombre.setBackground(new Color(255, 253, 208)); 
        txtTelefono.setBackground(new Color(255, 253, 208));
        txtCorreo.setBackground(new Color(255, 253, 208));
        tipoBusquedaComboBox.setBackground(new Color(255, 253, 208));

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Teléfono (La búsqueda debe ser exacta):"));
        panelCampos.add(txtTelefono);
        panelCampos.add(new JLabel("Correo:"));
        panelCampos.add(txtCorreo);
        panelCampos.add(new JLabel("Buscar por:"));
        panelCampos.add(tipoBusquedaComboBox);

        add(panelCampos, BorderLayout.CENTER);

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setLineWrap(false);  
        areaResultados.setBackground(new Color(240, 240, 240));

        JScrollPane scrollResultados = new JScrollPane(areaResultados,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollResultados.setPreferredSize(new Dimension(550, 300));
        add(scrollResultados, BorderLayout.SOUTH);

     
        JPanel panelBotones = new JPanel();
        btnSiguiente = new JButton("Siguiente");
        btnContinuarSinCliente = new JButton("Continuar sin cliente");

        panelBotones.add(btnSiguiente);
        panelBotones.add(btnContinuarSinCliente);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        tipoBusquedaComboBox.addActionListener(e -> actualizarCamposBusqueda());

        DocumentListener buscarListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { buscar(); }
            public void removeUpdate(DocumentEvent e) { buscar(); }
            public void changedUpdate(DocumentEvent e) { buscar(); }
        };

        txtNombre.getDocument().addDocumentListener(buscarListener);
        txtTelefono.getDocument().addDocumentListener(buscarListener);
        txtCorreo.getDocument().addDocumentListener(buscarListener);

     
        btnSiguiente.addActionListener(e -> siguiente());

     
        btnContinuarSinCliente.addActionListener(e -> continuarSinCliente());
    }

    private void cargarClientesIniciales() {
        try {
            List<ClienteFrecuenteDTO> clientes = control.obtenerTodosLosClientes();
            if (clientes != null && !clientes.isEmpty()) {
                mostrarResultados(clientes);
            } else {
                areaResultados.setText("No se encontraron clientes.");
            }
        } catch (Exception e) {
            areaResultados.setText("Error al cargar los clientes: " + e.getMessage());
        }
    }

    private void buscar() {
        try {
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String correo = txtCorreo.getText().trim();

            if (nombre.isEmpty() && telefono.isEmpty() && correo.isEmpty()) {
                cargarClientesIniciales();
                return;
            }

            List<ClienteFrecuenteDTO> resultados = control.buscarClientes(nombre, telefono, correo);

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
    JPanel panelResultados = new JPanel();
    panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
    panelResultados.setBackground(new Color(255, 240, 200));

   
    JLabel avisoDeslizar = new JLabel("Desliza hacia la izquierda para seleccionar un cliente");
    avisoDeslizar.setFont(new Font("Arial", Font.ITALIC, 16));
    avisoDeslizar.setForeground(new Color(255, 69, 0)); 
    panelResultados.add(avisoDeslizar);

    for (ClienteFrecuenteDTO cliente : clientes) {
        JPanel panelCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCliente.setBackground(new Color(255, 240, 200)); 

        JLabel lblCliente = new JLabel(cliente.toString());
        lblCliente.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCliente.setForeground(new Color(34, 139, 34)); 

        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setFont(new Font("Arial", Font.BOLD, 14)); 
        btnSeleccionar.setBackground(new Color(50, 205, 50)); 
        btnSeleccionar.setForeground(Color.WHITE);  
        btnSeleccionar.addActionListener(e -> seleccionarCliente(cliente));

        panelCliente.add(lblCliente);
        panelCliente.add(btnSeleccionar);

        panelResultados.add(panelCliente);
    }

    areaResultados.setText("");  

    JScrollPane scrollPane = new JScrollPane(panelResultados,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(550, 300));

    
    areaResultados.setBackground(new Color(255, 240, 200)); 

    getContentPane().add(scrollPane, BorderLayout.CENTER);
    revalidate();
    repaint();
}


    private void actualizarCamposBusqueda() {
        String tipoBusqueda = (String) tipoBusquedaComboBox.getSelectedItem();

        if ("Nombre".equals(tipoBusqueda)) {
            txtNombre.setEnabled(true);
            txtTelefono.setEnabled(false);
            txtCorreo.setEnabled(false);
        } else if ("Teléfono".equals(tipoBusqueda)) {
            txtNombre.setEnabled(false);
            txtTelefono.setEnabled(true);
            txtCorreo.setEnabled(false);
        } else if ("Correo".equals(tipoBusqueda)) {
            txtNombre.setEnabled(false);
            txtTelefono.setEnabled(false);
            txtCorreo.setEnabled(true);
        }
    }

    private void siguiente() {
        if (clienteSeleccionado != null) {
           
            control.openFormAñadirProductoComanda(clienteSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void continuarSinCliente() {
       
        control.openFormAñadirProductoComanda();
    }

  
    private void seleccionarCliente(ClienteFrecuenteDTO cliente) {
        this.clienteSeleccionado = cliente; 
        JOptionPane.showMessageDialog(this, "Cliente seleccionado: " + cliente.toString());
    }
}