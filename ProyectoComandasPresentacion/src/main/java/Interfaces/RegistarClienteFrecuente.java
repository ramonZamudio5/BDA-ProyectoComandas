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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

      
        JPanel panelCampos = new JPanel(new GridLayout(4, 2));
        panelCampos.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Teléfono (10 dígitos):"));
        txtTelefono = new JTextField();
        panelCampos.add(txtTelefono);

        panelCampos.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        panelCampos.add(txtCorreo);

        add(panelCampos, BorderLayout.CENTER);

        btnRegistrar = new JButton("Registrar Cliente");
        add(btnRegistrar, BorderLayout.SOUTH);

     
        lblMensaje = new JLabel("", JLabel.CENTER);
        add(lblMensaje, BorderLayout.NORTH);
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

            if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                lblMensaje.setText("Todos los campos son obligatorios.");
                lblMensaje.setForeground(Color.RED);
                return;
            }

            
            ClienteFrecuenteDTO clienteDTO = new ClienteFrecuenteDTO();
            clienteDTO.setNombreCompleto(nombre);
            clienteDTO.setTelefono(telefono);
            clienteDTO.setCorreoElectronico(correo);

         
            ClienteFrecuenteDTO clienteRegistrado = control.registrarCliente(clienteDTO);

            
            lblMensaje.setText("Cliente registrado con éxito: " + clienteRegistrado.getNombreCompleto());
            lblMensaje.setForeground(Color.GREEN);
        } catch (Exception ex) {
            lblMensaje.setText("Error: " + ex.getMessage());
            lblMensaje.setForeground(Color.RED);
        }
    }

}
