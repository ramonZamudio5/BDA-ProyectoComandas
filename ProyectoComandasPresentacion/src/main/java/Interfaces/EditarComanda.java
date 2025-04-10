/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import dtos.ComandaDTO;
import dtos.DetalleComandaDTO;
import dtos.ProductoDTO;
import entidades.DetalleComanda;
import entidades.Producto;
import enums.EstadoComanda;
import enums.Tipo;
import excepciones.NegocioException;
import interfaces.IManejadorDeObjetos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import manejadorDeObjetos.ManejadorDeObjetos;
import javax.swing.JOptionPane;
/**
 *
 * @author Ramón Zamudio
 */
public class EditarComanda extends javax.swing.JFrame {
    ControlNavegacion control;
    JComboBox<Tipo> tipoComboBox;
    JComboBox<EstadoComanda> estadoComandaComboBox;
    List<DetalleComandaDTO>listaDetallesComanda;
    ComandaDTO comanda;
    /**
     * Creates new form EditarComanda
     */
    public EditarComanda(ControlNavegacion control,ComandaDTO comanda) {
        initComponents();
        this.comanda = comanda;
        this.control = control;
        tipoComboBox = new JComboBox<>(new DefaultComboBoxModel<>(Tipo.values()));
        tipoComboBox.setBounds(410, 145,130,70);
        add(tipoComboBox); 
        estadoComandaComboBox = new JComboBox<>(new DefaultComboBoxModel<>(EstadoComanda.values()));
        estadoComandaComboBox.setBounds(800, 145,130,70);
        add(estadoComandaComboBox);
        getContentPane().setBackground(new java.awt.Color(248, 248, 241));
        listaDetallesComanda = new ArrayList<>();
        cargarPanel2();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jTextField1.setColumns(15);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel2);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Editar EstadoComanda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(137, 137, 137))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String busqueda = jTextField1.getText();
        jPanel1.removeAll();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        try {
            List<ProductoDTO> listaProductos;
            if (jTextField1.getText().isEmpty()) {
                listaProductos = control.obtenerPorTipo((Tipo) tipoComboBox.getSelectedItem());
            } else {
                listaProductos = control.obtenerPorNombre(busqueda);
            }

            jPanel1.removeAll();

            for (ProductoDTO producto : listaProductos) {
                DetalleComandaDTO detalleComanda = new DetalleComandaDTO();
                JPanel productoPanel = new JPanel();
                productoPanel.setLayout(new BoxLayout(productoPanel, BoxLayout.Y_AXIS));
                productoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                productoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
                productoPanel.setBackground(Color.WHITE);

                JLabel nombreLabel = new JLabel(producto.getNombre(), SwingConstants.CENTER);
                nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));

                JPanel contadorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                contadorPanel.setOpaque(false);

                JButton btnMenos = new JButton("-");
                JLabel cantidadLabel = new JLabel("0");
                JButton btnMas = new JButton("+");

                btnMenos.setPreferredSize(new Dimension(40, 40));
                btnMas.setPreferredSize(new Dimension(40, 40));
                cantidadLabel.setPreferredSize(new Dimension(30, 30));
                cantidadLabel.setFont(new Font("Arial", Font.BOLD, 16));
                cantidadLabel.setHorizontalAlignment(SwingConstants.CENTER);

                final int[] cantidad = {0};

                btnMas.addActionListener(e -> {
                    cantidad[0]++;
                    cantidadLabel.setText(String.valueOf(cantidad[0]));

                    boolean productoExiste = false;
                    for (DetalleComandaDTO detalle : listaDetallesComanda) {
                        if (detalle.getProducto().getNombre().equals(producto.getNombre())) {
                            detalle.setCantidad(cantidad[0]);
                            detalle.setImporte(detalle.getPrecioUnitario() * cantidad[0]);
                            productoExiste = true;
                        }
                    }

                    if (!productoExiste) {
                        DetalleComandaDTO nuevoDetalleComanda = new DetalleComandaDTO();
                        nuevoDetalleComanda.setProducto(new Producto(producto.getNombre(), producto.getPrecio(), producto.getTipoProducto(), producto.isEstado()));
                        nuevoDetalleComanda.setCantidad(cantidad[0]);
                        nuevoDetalleComanda.setPrecioUnitario(producto.getPrecio());
                        nuevoDetalleComanda.setImporte(producto.getPrecio() * cantidad[0]); // Calcular el importe
                        listaDetallesComanda.add(nuevoDetalleComanda);
                    }
                });

                btnMenos.addActionListener(e -> {
                    if (cantidad[0] > 0) {
                        cantidad[0]--;
                        cantidadLabel.setText(String.valueOf(cantidad[0]));
                        for (DetalleComandaDTO detalle : listaDetallesComanda) {
                            if (detalle.getProducto().getNombre().equals(producto.getNombre())) {
                                detalle.setCantidad(cantidad[0]);
                                detalle.setImporte(detalle.getPrecioUnitario() * cantidad[0]); // Actualiza el importe correctamente
                            }
                        }
                    }
                });

                contadorPanel.add(btnMenos);
                contadorPanel.add(cantidadLabel);
                contadorPanel.add(btnMas);

                JButton btnNotas = new JButton("Notas");
                btnNotas.setAlignmentX(Component.CENTER_ALIGNMENT);
                btnNotas.addActionListener(e -> {
                    JTextArea textArea = new JTextArea(10, 30);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);

                    JScrollPane scrollPane = new JScrollPane(textArea);

                    int option = JOptionPane.showConfirmDialog(
                        null,
                        scrollPane,
                        "Agregar notas para " + producto.getNombre(),
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                    );

                    if (option == JOptionPane.OK_OPTION) {
                        String notas = textArea.getText();
                        System.out.println("Notas capturadas: " + notas);
                        for (DetalleComandaDTO detalle : listaDetallesComanda) {
                            if (detalle.getProducto().getNombre().equals(producto.getNombre())) {
                                detalle.setNotas(notas);
                            }
                        }
                    }
                });

                productoPanel.add(nombreLabel);
                productoPanel.add(contadorPanel);
                productoPanel.add(btnNotas);

                jPanel1.add(productoPanel);
            }

            jPanel1.revalidate();
            jPanel1.repaint();
        } catch (Exception ex) {
            try {
                throw new NegocioException("Error al buscar el producto");
            } catch (NegocioException ex1) {
                Logger.getLogger(BuscadorDeProductosFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed
    
    public void cargarPanel2() {
    jPanel2.removeAll();

    if (comanda == null || comanda.getDetalles() == null || comanda.getDetalles().isEmpty()) {
        JLabel lblVacio = new JLabel("No hay productos en la comanda.");
        lblVacio.setBounds(10, 10, 300, 20);
        jPanel2.add(lblVacio);
    } else {
        int y = 10;

        for (DetalleComanda detalle : comanda.getDetalles()) {
            JPanel panelProducto = new JPanel();
            panelProducto.setLayout(null);
            panelProducto.setBounds(10, y, 400, 60);
            panelProducto.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel lblNombre = new JLabel(detalle.getProducto().getNombre());
            lblNombre.setBounds(10, 10, 200, 20);
            panelProducto.add(lblNombre);

            JLabel lblCantidad = new JLabel("Cantidad: " + detalle.getCantidad());
            lblCantidad.setBounds(10, 30, 200, 20);
            panelProducto.add(lblCantidad);

            JButton btnNotas = new JButton("Notas");
            btnNotas.setBounds(220, 15, 80, 30);
            btnNotas.addActionListener(e -> {
                String notaActual = detalle.getNotas() != null ? detalle.getNotas() : "";
                String nuevaNota = JOptionPane.showInputDialog(null, "Escribe una nota para este producto:", notaActual);
                if (nuevaNota != null) {
                    detalle.setNotas(nuevaNota);
                }
            });
            panelProducto.add(btnNotas);

            jPanel2.add(panelProducto);
            y += 70;
        }
    }

    jPanel2.revalidate();
    jPanel2.repaint();
}
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            // TODO add your handling code here:
            double total = 0.0;
            for(DetalleComanda detalles :comanda.getDetalles()){
                if(detalles.getCantidad()>0){
                    detalles.setImporte(detalles.getCantidad() * detalles.getPrecioUnitario());
                    total += detalles.getImporte()*detalles.getCantidad();
                }
            }
            List<DetalleComanda> detalleComandas = new LinkedList<>();
            for (DetalleComandaDTO detalleComanda : listaDetallesComanda) {
                detalleComandas.add(new DetalleComanda(
                    detalleComanda.getComanda(),
                    detalleComanda.getProducto(),
                    detalleComanda.getCantidad(),
                    detalleComanda.getPrecioUnitario(),
                    detalleComanda.getNotas()
                ));
                detalleComanda.setImporte(detalleComanda.getImporte());
            }
            for (DetalleComandaDTO detalleComanda : listaDetallesComanda) {
                System.out.println(detalleComanda.getImporte());
            }
            ComandaDTO comandaActualizada = new ComandaDTO(comanda.getFolio(), comanda.getFechaHoraCreacion(), total,(EstadoComanda) estadoComandaComboBox.getSelectedItem()
                    , comanda.getCliente(), detalleComandas, comanda.getMesa());
            
            control.actualizarComanda(comandaActualizada);
        } catch (NegocioException ex) {
            Logger.getLogger(EditarComanda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(EditarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IManejadorDeObjetos manejador = new ManejadorDeObjetos();
                ControlNavegacion control = new ControlNavegacion(manejador);
                new EditarComanda(control,new ComandaDTO()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
