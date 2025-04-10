/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;


import ControlNavegacion.ControlNavegacion;
import enums.UnidadMedida;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import Utilerias.Utileria;
import bos.IngredienteBO;
//import daos.IngredienteDAO;
//import static daos.IngredienteDAO.ingredienteDAO;
import dtos.IngredienteDTO;
//import excepciones.AgregarIngredienteException;
import excepciones.NegocioException;
import interfaces.IManejadorDeObjetos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorDeObjetos.ManejadorDeObjetos;
/**
 *
 * @author janethcristinagalvanquinonez
 */
public class AgregarIngrediente extends javax.swing.JFrame {
    private ControlNavegacion control;
    private Utileria utileria;
    private UnidadMedida unidadSeleccionada;
    private JTextField campoRuta;
   // private IngredienteBO ingredienteBO;
    private IngredienteDTO ingredienteDTO;
    
 //   private JLabel labelImagen;
    /**
     * Creates new form BuscarIngrediente
     */
    public AgregarIngrediente(ControlNavegacion control) {
        initComponents();
        this.control = control; 
        jPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));

       llenarCombo();
        
       // botonUnidadDinamico();
       // this.add(jPanel4, BorderLayout.CENTER);
        this.revalidate();
      //  labelImagen= new JLabel();
        campoRuta= new JTextField();
        labelImagen.setPreferredSize(new Dimension(50,50));
        jPanel4.add(labelImagen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // IngredienteDAO ingredienteDAO = new IngredienteDAO();
      // ingredienteBO= new IngredienteBO(ingredienteDAO);

    }
    public void guardar() throws IOException, NegocioException{
        String nombre= inputNombre.getText();
        Double stock;
        try{
            stock= Double.parseDouble(inputStock.getText());          
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ingrese un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        UnidadMedida unidad= this.unidadSeleccionada;
        String rutaImagen= campoRuta.getText();
        byte[]foto= Utileria.convertirImagenABytes(rutaImagen);
      
        IngredienteDTO ingredienteDTO= new IngredienteDTO(nombre, stock, unidad, foto);
    
        try{
        IngredienteDTO ingredienteRegistrado= control.agregarIngrediente(ingredienteDTO);
        JOptionPane.showMessageDialog(this, "Ingrediente guardado", "Exito", JOptionPane.INFORMATION_MESSAGE);
  
       
    } catch(NegocioException e){
        JOptionPane.showMessageDialog(this, "Error al guardar ingrediente" +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    
    
//    public void botonUnidadDinamico(){
//        ButtonGroup grupoBotones= new ButtonGroup();
//        
//        for (UnidadMedida unidad :UnidadMedida.values()){
//            JCheckBox boton= new JCheckBox(unidad.name());
//          //  boton.setPreferredSize(new Dimension(120,40));
//          boton.setBackground(Color.WHITE);
//          boton.setOpaque(true);
//          boton.setMargin(new Insets(2, 5, 2, 5));
//          boton.setPreferredSize(new Dimension(100, 30));
//            
//            boton.addActionListener(e ->{
//                unidadSeleccionada= unidad; 
//                System.out.println("Unidad seleccionada: "+unidadSeleccionada);
//            });
//            grupoBotones.add(boton);
//            jPanel4.add(boton);
//            jPanel4.revalidate();
//            jPanel4.repaint();
//            
//        }
//     //   this.add(jPanel4);
//        this.revalidate();;
//        this.repaint();
//       // System.out.println(getClass().getResource("/Resources/foto1.png"));
//
//    }
    private void buscarImagen(){
        JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filtro= new FileNameExtensionFilter("Imagen", "jpg", "png", "jpeg");
        chooser.setFileFilter(filtro);
        int resultado= chooser.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
            File file= chooser.getSelectedFile();
            String ruta= file.getAbsolutePath();
            campoRuta.setText(ruta);
            ImageIcon icon= new ImageIcon(ruta);
            if(icon.getImage()==null){
                System.out.println("Error al cargar la imagen");
            }
            Image imagen = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            labelImagen.setIcon(new ImageIcon(imagen));
            
        }
        
    }
    
    private void llenarCombo(){
        // unidadMedidaCombo.removeAllItems();
        unidadMedidaCombo.addItem("Seleccionar Unidad");
        
        for(UnidadMedida unidad: UnidadMedida.values()){
            unidadMedidaCombo.addItem(unidad.name());
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nombre = new javax.swing.JLabel();
        inputNombre = new javax.swing.JTextField();
        inputStock = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        registrarButton = new javax.swing.JButton();
        stock = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        unidad = new javax.swing.JLabel();
        agregarImagenButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        unidadMedidaCombo = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        labelImagen = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblIcono = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 220));

        jPanel2.setBackground(new java.awt.Color(245, 245, 220));

        nombre.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 0, 13)); // NOI18N
        nombre.setForeground(new java.awt.Color(51, 51, 51));
        nombre.setText("Nombre del Ingrediente:");

        inputNombre.setFont(new java.awt.Font("DialogInput", 0, 13)); // NOI18N
        inputNombre.setForeground(new java.awt.Color(153, 153, 153));
        inputNombre.setText("nombre...");
        inputNombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inputNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreIng(evt);
            }
        });
        inputNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNombreActionPerformed(evt);
            }
        });

        inputStock.setForeground(new java.awt.Color(153, 153, 153));
        inputStock.setText("stock...");
        inputStock.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inputStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stockFocusGained(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Agregar imagen:");

        registrarButton.setBackground(new java.awt.Color(0, 102, 102));
        registrarButton.setText("REGISTRAR");
        registrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarButtonActionPerformed(evt);
            }
        });

        stock.setForeground(new java.awt.Color(51, 51, 51));
        stock.setText("Cantidad en stock:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        unidad.setForeground(new java.awt.Color(51, 51, 51));
        unidad.setText("Unidad de medida:");

        agregarImagenButton.setBackground(new java.awt.Color(153, 153, 153));
        agregarImagenButton.setForeground(new java.awt.Color(255, 255, 255));
        agregarImagenButton.setText("Agregar...");
        agregarImagenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarImagenButtonActionPerformed(evt);
            }
        });

        unidadMedidaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        unidadMedidaCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadMedidaComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unidadMedidaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(unidadMedidaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        labelImagen.setText("jLabel2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelImagen)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(labelImagen)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(registrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                    .addComponent(inputNombre)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(unidad, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(106, 106, 106)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputStock, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarImagenButton))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(agregarImagenButton)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(stock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(registrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(unidad)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(lblIcono)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Devanagari MT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 128, 0));
        jLabel1.setText("AGREGAR INGREDIENTE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void registrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarButtonActionPerformed
        try {
            guardar();
        } catch (IOException ex) {
            Logger.getLogger(AgregarIngrediente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NegocioException ex) {
            Logger.getLogger(AgregarIngrediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registrarButtonActionPerformed

    private void inputNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNombreActionPerformed

    private void nombreIng(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreIng
        inputNombre.setText("");
        inputNombre.setForeground(Color.BLACK);
        inputNombre.setBackground(Color.WHITE);
    }//GEN-LAST:event_nombreIng

    private void stockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stockFocusGained
        inputStock.setText("");
        inputStock.setForeground(Color.BLACK);
        inputStock.setBackground(Color.WHITE);
    }//GEN-LAST:event_stockFocusGained

    private void agregarImagenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarImagenButtonActionPerformed
       buscarImagen();
       
    }//GEN-LAST:event_agregarImagenButtonActionPerformed

    private void unidadMedidaComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadMedidaComboActionPerformed
      String seleccion= (String) unidadMedidaCombo.getSelectedItem();
      if(!"Seleccionar unidad".equals(seleccion)) {
          unidadSeleccionada= UnidadMedida.valueOf(seleccion);
          System.out.println(unidadSeleccionada);
          
      } else{
          unidadSeleccionada= null;
      }
    }//GEN-LAST:event_unidadMedidaComboActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IManejadorDeObjetos manejador= new ManejadorDeObjetos();
                ControlNavegacion control= new ControlNavegacion(manejador);
                new AgregarIngrediente(control).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarImagenButton;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JTextField inputStock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel nombre;
    private javax.swing.JButton registrarButton;
    private javax.swing.JLabel stock;
    private javax.swing.JLabel unidad;
    private javax.swing.JComboBox<String> unidadMedidaCombo;
    // End of variables declaration//GEN-END:variables
}
