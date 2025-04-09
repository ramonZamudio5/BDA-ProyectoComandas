/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import bos.ClienteFrecuenteBO;

import dtos.ClienteFrecuenteDTO;
import excepciones.NegocioException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cricri
 */

public class RegistarClienteFrecuente extends JFrame {
     private ControlNavegacion control;
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private JButton btnRegistrar;
    private JLabel lblMensaje;

    public RegistarClienteFrecuente(ControlNavegacion control) {
        this.control = control;
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setTitle("Registrar Cliente Frecuente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        getContentPane().setBackground(new Color(255, 253, 208)); 

        JLabel titulo = new JLabel("Registrar Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(34, 139, 34));
        add(titulo, BorderLayout.NORTH);

    
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.setBackground(new Color(255, 253, 208)); 

    
        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        Color colorCremita = new Color(255, 253, 208); 
        txtNombre.setBackground(colorCremita);
        txtTelefono.setBackground(colorCremita);
        txtCorreo.setBackground(colorCremita);


        panelCampos.add(new JLabel("Nombre Completo:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Teléfono (10 dígitos):"));
        panelCampos.add(txtTelefono);
        panelCampos.add(new JLabel("Correo Electrónico:"));
        panelCampos.add(txtCorreo);

        add(panelCampos, BorderLayout.CENTER);

        JPanel panelBotonMensaje = new JPanel();
        panelBotonMensaje.setLayout(new BorderLayout()); 

     
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(255, 253, 208)); 
        btnRegistrar = new JButton("Registrar Cliente");
        btnRegistrar.setBackground(new Color(34, 139, 34)); 
        btnRegistrar.setForeground(Color.WHITE);
        panelBoton.add(btnRegistrar); 
        panelBotonMensaje.add(panelBoton, BorderLayout.NORTH);

        // Mensaje de resultado
        lblMensaje = new JLabel("", JLabel.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 14));
        lblMensaje.setForeground(Color.RED);
        panelBotonMensaje.add(lblMensaje, BorderLayout.CENTER); 

        add(panelBotonMensaje, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });
    }

    private void registrarCliente() {
        try {
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String correo = txtCorreo.getText().trim();

            if (nombre.isEmpty() || telefono.isEmpty()) {
                lblMensaje.setText("TELEFONO Y NOMBRE SON CAMPOS OBLIGATORIOS.");
                lblMensaje.setForeground(Color.RED);
                return;
            }

            ClienteFrecuenteDTO clienteDTO = new ClienteFrecuenteDTO();
            clienteDTO.setNombreCompleto(nombre);
            clienteDTO.setTelefono(telefono);
            clienteDTO.setCorreoElectronico(correo);

            ClienteFrecuenteDTO clienteRegistrado = control.registrarCliente(clienteDTO);

            lblMensaje.setText("Cliente registrado con éxito: " + clienteRegistrado.getNombreCompleto());
            lblMensaje.setForeground(new Color(34, 139, 34));
        } catch (Exception ex) {
            lblMensaje.setText("Error: " + ex.getMessage());
            lblMensaje.setForeground(Color.RED);
        }
    }
}