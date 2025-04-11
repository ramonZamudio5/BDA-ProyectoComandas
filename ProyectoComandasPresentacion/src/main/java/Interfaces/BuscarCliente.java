/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import dtos.ClienteFrecuenteDTO;
import dtos.ClienteMesaDTO;
import dtos.MesaDispDTO;
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


public class BuscarCliente extends JFrame {
     private MesaDispDTO mesa;
    private ControlNavegacion control;
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private JComboBox<String> tipoBusquedaComboBox;
    private JButton btnSiguiente, btnContinuarSinCliente;
    private JScrollPane scrollPanelResultados;

    private ClienteFrecuenteDTO clienteSeleccionado = null; // Para almacenar el cliente seleccionado

    public BuscarCliente(ControlNavegacion control, MesaDispDTO mesa) {
        System.out.println(mesa);
        this.control = control;
        this.mesa = mesa;
        inicializarComponentes();
        configurarEventos();
        cargarClientesIniciales();
    }

    public BuscarCliente(ControlNavegacion control) {
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

        JPanel panelCampos = new JPanel(new GridLayout(5, 2));
        panelCampos.setBackground(new Color(255, 253, 208));

        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        tipoBusquedaComboBox = new JComboBox<>(new String[] { "Nombre", "Teléfono", "Correo" });

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

        JPanel panelBuscador = new JPanel();
        panelBuscador.setLayout(new BorderLayout());
        panelBuscador.add(titulo, BorderLayout.NORTH);
        panelBuscador.add(panelCampos, BorderLayout.CENTER);

        add(panelBuscador, BorderLayout.NORTH);

        if (mesa != null) {
            JPanel panelBotones = new JPanel();
            btnSiguiente = new JButton("Siguiente");
            btnContinuarSinCliente = new JButton("Continuar sin cliente");

            panelBotones.add(btnSiguiente);
            panelBotones.add(btnContinuarSinCliente);
            add(panelBotones, BorderLayout.SOUTH);
        }
    }

    private void configurarEventos() {
        tipoBusquedaComboBox.addActionListener(e -> actualizarCamposBusqueda());

        DocumentListener buscarListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        };

        txtNombre.getDocument().addDocumentListener(buscarListener);
        txtTelefono.getDocument().addDocumentListener(buscarListener);
        txtCorreo.getDocument().addDocumentListener(buscarListener);

        if(mesa != null){

            btnSiguiente.addActionListener(e -> siguiente());
    
            btnContinuarSinCliente.addActionListener(e -> continuarSinCliente());
        }
    }

    private void cargarClientesIniciales() {
        try {
            List<ClienteFrecuenteDTO> clientes = control.obtenerTodosLosClientes();
            mostrarResultados(clientes);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error al realizar la busqueda: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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

            mostrarResultados(resultados);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error al realizar la busqueda: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarResultados(List<ClienteFrecuenteDTO> clientes) {

        if (scrollPanelResultados != null) {
            getContentPane().remove(scrollPanelResultados);
        }

        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
        panelResultados.setBackground(new Color(255, 240, 200));

        if (clientes == null || clientes.isEmpty()) {
            JLabel aviso = new JLabel("No se encontraron resultados");
            aviso.setFont(new Font("Arial", Font.ITALIC, 16));
            aviso.setForeground(new Color(255, 69, 0));
            panelResultados.add(aviso);
            return;
        }

        if(mesa != null){

            JLabel avisoDeslizar = new JLabel("Desliza hacia la izquierda para seleccionar un cliente");
            avisoDeslizar.setFont(new Font("Arial", Font.ITALIC, 16));
            avisoDeslizar.setForeground(new Color(255, 69, 0));
            panelResultados.add(avisoDeslizar);
        }

        for (ClienteFrecuenteDTO cliente : clientes) {
            JPanel panelCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelCliente.setBackground(new Color(255, 240, 200));

            JLabel lblCliente = new JLabel(cliente.toString());
            lblCliente.setFont(new Font("Arial", Font.PLAIN, 18));
            lblCliente.setForeground(new Color(34, 139, 34));

            panelCliente.add(lblCliente);

            if(mesa != null){
                JButton btnSeleccionar = new JButton("Seleccionar");
                btnSeleccionar.setFont(new Font("Arial", Font.BOLD, 14));
                btnSeleccionar.setBackground(new Color(50, 205, 50));
                btnSeleccionar.setForeground(Color.WHITE);
                btnSeleccionar.addActionListener(e -> seleccionarCliente(cliente));
                
                panelCliente.add(btnSeleccionar);
            }

            panelResultados.add(panelCliente);
        }

        scrollPanelResultados = new JScrollPane(panelResultados,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelResultados.setPreferredSize(new Dimension(550, 300));

        getContentPane().add(scrollPanelResultados, BorderLayout.CENTER);
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
            ClienteMesaDTO clienteMesa = new ClienteMesaDTO(mesa, clienteSeleccionado);
            control.openFormAñadirProductoComanda(clienteMesa);
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void continuarSinCliente() {
        ClienteMesaDTO clienteMesa = new ClienteMesaDTO(mesa, clienteSeleccionado);
        control.openFormAñadirProductoComanda(clienteMesa);
    }

    private void seleccionarCliente(ClienteFrecuenteDTO cliente) {
        this.clienteSeleccionado = cliente;
        JOptionPane.showMessageDialog(this, "Cliente seleccionado: " + cliente.toString());
    }
}