/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
public class RegistarClienteFrame extends JFrame {
    
    public RegistarClienteFrame() {
      
        setTitle("Registro de Cliente Frecuente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

     
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Nuevo Cliente Frecuente");
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

   
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Nombre Completo:"), gbc);
        JTextField nombreField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(nombreField, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Teléfono:"), gbc);
        JTextField telefonoField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(telefonoField, gbc);

      
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Correo Electrónico:"), gbc);
        JTextField correoField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(correoField, gbc);

        
        JButton registrarButton = new JButton("Registrar Cliente");
        registrarButton.setBackground(new Color(128, 0, 128)); // Morado
        registrarButton.setForeground(Color.WHITE);
        registrarButton.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registrarButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistarClienteFrame());
    }

}
