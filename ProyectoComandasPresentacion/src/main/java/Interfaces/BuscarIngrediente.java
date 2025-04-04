/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import ControlIngrediente.ControlIngrediente;
import Utilerias.Utileria;
import dtos.IngredienteDTO;
import entidades.Ingrediente;
import java.awt.CardLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import enums.UnidadMedida;
import excepciones.BuscarPorMedidaException;
import excepciones.BuscarPorNombreException;
import excepciones.NegocioException;
import interfaces.IIngredienteBO;
import interfaces.IManejadorObjetoNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import manejadorObjetoNegocio.ManejadorObjetoNegocio;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BuscarIngrediente extends javax.swing.JFrame {

    ControlIngrediente control;
    private CardLayout cardLayout;
    JComboBox<String> comboUnidad = new JComboBox<>();
    private JTextField campoNombre;
    private IIngredienteBO ingredienteBO;

    public BuscarIngrediente(ControlIngrediente control) {
        this.control = control;
        initComponents();
        //this.ingredienteBO= ingredienteBO;
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(botonMedida);
        buttonGroup.add(botonNombre);
        cardLayout = new CardLayout();
        panelBusqueda.setLayout(cardLayout);
        JPanel panelNombre = new JPanel();

        campoNombre = new JTextField(15);
        panelNombre.add(new JLabel("Nombre..."));
        panelNombre.add(campoNombre);

        JPanel panelUnidad = new JPanel();
        //  JComboBox<String> comboUnidad= new JComboBox<>();
        panelUnidad.add(comboUnidad);

        panelBusqueda.add(panelNombre, "nombre");
        panelBusqueda.add(panelUnidad, "unidad");
        manejoEventos();

    }

    private void manejoEventos() {
        campoNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    actualizarBusqueda();
                } catch (NegocioException | BuscarPorNombreException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    actualizarBusqueda();
                } catch (NegocioException | BuscarPorNombreException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    actualizarBusqueda();
                } catch (NegocioException | BuscarPorNombreException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void mostrarResultados(List<IngredienteDTO> IngredientesEncontrados) {
        panelIngredientes.removeAll();
        panelIngredientes.setLayout(new GridLayout(0, 1, 10, 10));
        
        for (IngredienteDTO ingrediente : IngredientesEncontrados) {

            JPanel panelIngrediente = new JPanel(new GridBagLayout());
            
            
           
            panelIngrediente.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panelIngrediente.setBackground(Color.WHITE);
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL; 
            
            JLabel nombreLabel = new JLabel(ingrediente.getNombre());
            nombreLabel.setFont(new Font("Arial", Font.BOLD, 18));
            nombreLabel.setHorizontalAlignment(SwingConstants.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelIngrediente.add(nombreLabel, gbc);
            
            JLabel unidadLabel = new JLabel(ingrediente.getUnidadMedida() + ": " + ingrediente.getStock());
            unidadLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            unidadLabel.setHorizontalAlignment(SwingConstants.LEFT);
             gbc.gridx = 0;
             gbc.gridy = 1;
             panelIngrediente.add(unidadLabel, gbc);

          //  panelIngrediente.add(nombreLabel, BorderLayout.NORTH);
           // panelIngrediente.add(unidadLabel, BorderLayout.CENTER);
            panelIngredientes.add(panelIngrediente);

        }
        panelIngredientes.revalidate();
        panelIngredientes.repaint();

        //   ImageIcon imagen= Utileria.convertirImagenABytes(/Users/janethcristinagalvanquinonez/Downloads);
    }

//        private ImageIcon convertirBytesAImagenes(byte[] imagenBytes){
//            if(imagenBytes==null || imagenBytes.length==0){
//                return 
//            }
//            try{
//                ByteArrayInputStream bis = new ByteArrayInputStream(imagenBytes);
//                BufferedImage bufferedImage = ImageIO.read(bis);
//                return new ImageIcon("/");
//            }
//        }
    private void actualizarBusqueda() throws NegocioException, BuscarPorNombreException {

        String textoBuscado = campoNombre.getText().trim();
        System.out.println("Texto ingresado: '" + textoBuscado + "'");
        panelIngredientes.removeAll();
        if (!textoBuscado.isEmpty()) {
            try {
                List<IngredienteDTO> IngredientesEncontrados = control.buscarPorNombre(textoBuscado);
                System.out.println("Ingredientes encontrados: " + IngredientesEncontrados.size());

                if (IngredientesEncontrados.isEmpty()) {
                    panelIngredientes.add(new JLabel("No se encontraron resultados"));
                } else {

                    mostrarResultados(IngredientesEncontrados);
                }

            } catch (NegocioException | BuscarPorNombreException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al buscar ingredientes: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    private void actualizarBusquedaUnidad(String unidadMedida) throws NegocioException, BuscarPorMedidaException {
        panelIngredientes.removeAll();
        System.out.println("unidad " + unidadMedida);
        if (unidadMedida != null && !unidadMedida.trim().isEmpty()) {
            try {
                List<IngredienteDTO> ingredientesEncontrados = control.buscarPorMedida(unidadMedida);
                System.out.println("Ingredientes encotrados: " + ingredientesEncontrados.size());

                if (ingredientesEncontrados.isEmpty()) {
                    panelIngredientes.add(new JLabel("No se encontraron resultados"));
                } else {
                    mostrarResultados(ingredientesEncontrados);
                }

            } catch (NegocioException | BuscarPorMedidaException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al buscar ingredientes: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelFiltro = new javax.swing.JPanel();
        botonMedida = new javax.swing.JRadioButton();
        botonNombre = new javax.swing.JRadioButton();
        panelBusqueda = new javax.swing.JPanel();
        panelIngredientes = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 220));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Gujarati MT", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("BUSCAR INGREDIENTE");

        botonMedida.setText("UNIDAD DE MEDIDA");
        botonMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMedidaActionPerformed(evt);
            }
        });

        botonNombre.setText("NOMBRE");
        botonNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltroLayout = new javax.swing.GroupLayout(panelFiltro);
        panelFiltro.setLayout(panelFiltroLayout);
        panelFiltroLayout.setHorizontalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonMedida)
                .addContainerGap())
        );
        panelFiltroLayout.setVerticalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botonMedida)
                .addComponent(botonNombre))
        );

        panelBusqueda.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panelIngredientes.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelIngredientesLayout = new javax.swing.GroupLayout(panelIngredientes);
        panelIngredientes.setLayout(panelIngredientesLayout);
        panelIngredientesLayout.setHorizontalGroup(
            panelIngredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );
        panelIngredientesLayout.setVerticalGroup(
            panelIngredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(panelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(panelIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMedidaActionPerformed
        cardLayout.show(panelBusqueda, "unidad");
        comboUnidad.removeAllItems();
        for (UnidadMedida unidad : UnidadMedida.values()) {
            comboUnidad.addItem(unidad.name());
        }
        comboUnidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String unidadSeleccionada = (String) comboUnidad.getSelectedItem();
                try {
                    actualizarBusquedaUnidad(unidadSeleccionada);
                } catch (NegocioException ex) {
                    Logger.getLogger(BuscarIngrediente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BuscarPorMedidaException ex) {
                    Logger.getLogger(BuscarIngrediente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }//GEN-LAST:event_botonMedidaActionPerformed

    private void botonNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNombreActionPerformed
        cardLayout.show(panelBusqueda, "nombre");

    }//GEN-LAST:event_botonNombreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            //      IIngredienteBO ingredienteBO= ManejadorObjetoNegocio.crearIngredienteBO();
            public void run() {
                IManejadorObjetoNegocio manejador = new ManejadorObjetoNegocio();
                ControlIngrediente control = new ControlIngrediente(manejador);
                new BuscarIngrediente(control).setVisible(true);
                URL url = getClass().getClassLoader().getResource("Resources/fotoDefecto.png");
                System.out.println("Ruta encontrada: " + (url != null ? url.getPath() : "Imagen no encontrada"));

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton botonMedida;
    private javax.swing.JRadioButton botonNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelIngredientes;
    // End of variables declaration//GEN-END:variables
}
