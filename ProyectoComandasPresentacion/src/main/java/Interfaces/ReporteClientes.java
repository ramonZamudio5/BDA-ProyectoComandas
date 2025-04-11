/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import ControlNavegacion.ControlNavegacion;
import dtos.ClienteFrecuenteDTO;
import interfaces.IManejadorDeObjetos;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import manejadorDeObjetos.ManejadorDeObjetos;

/**
 *
 * @author Cricri
 */

public class ReporteClientes extends JPanel {
        
    private JTextField inputNombreCliente;
    private JTextField inputVisitasMinimas;
    private JButton btnGenerarReporte;
    private JButton btnBuscar;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private ControlNavegacion controlador;
    private List<ClienteFrecuenteDTO> listaClientes;

    public ReporteClientes(ControlNavegacion controlador) {
        this.controlador = controlador;
        inicio();
    }

    private void inicio() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        
        JPanel panelFiltros = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFiltros.add(new JLabel("Nombre del cliente:"));
        inputNombreCliente = new JTextField();
        panelFiltros.add(inputNombreCliente);

        panelFiltros.add(new JLabel("Visitas mínimas:"));
        inputVisitasMinimas = new JTextField();
        panelFiltros.add(inputVisitasMinimas);

        btnBuscar = new JButton("Buscar Clientes");
        btnBuscar.addActionListener(e -> buscarClientes());
        panelFiltros.add(btnBuscar);

        btnGenerarReporte = new JButton("Generar Reporte en PDF");
        btnGenerarReporte.addActionListener(e -> generarReportePDF());
        btnGenerarReporte.setEnabled(false); 
        panelFiltros.add(btnGenerarReporte);

        add(panelFiltros, BorderLayout.NORTH);

     
            String[] columnas = { 
         "Nombre", 
         "Fecha Registro", 
         "Puntos", 
         "Total Gastado", 
         "Total Visitas",
         "Última Comanda"
     };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void buscarClientes() {
        try {
            String nombreCliente = inputNombreCliente.getText().trim();
            String visitasStr = inputVisitasMinimas.getText().trim();
            Integer visitas = visitasStr.isEmpty() ? null : Integer.parseInt(visitasStr);

            listaClientes = controlador.obtenerClientesPorParametros(nombreCliente, visitas);

            modeloTabla.setRowCount(0); 

            if (listaClientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron clientes con esos parámetros.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                btnGenerarReporte.setEnabled(false);
                return;
            }

          
            for (ClienteFrecuenteDTO cliente : listaClientes) {
                 modeloTabla.addRow(new Object[]{
                 cliente.getNombreCompleto(),
                 cliente.getFechaRegistro(),
                 cliente.getPuntosObtenidos(),
                 cliente.getGastoTotalAcumulado(),
                 cliente.getConteoVisitas(),
                 cliente.getFechaUltimaComanda() != null
                ? cliente.getFechaUltimaComanda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                : "N/A"});

            }

            btnGenerarReporte.setEnabled(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Las visitas deben ser un número entero.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar clientes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void generarReportePDF() {
        if (listaClientes == null || listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para generar el reporte.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona la ubicación del reporte");
        fileChooser.setSelectedFile(new File("reporte_clientes_frecuentes.pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
            if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
                rutaArchivo += ".pdf";
            }

            try {
                PDF.itext.crearPDFClientes(listaClientes, rutaArchivo);
                JOptionPane.showMessageDialog(this, "El reporte fue generado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hubo un problema al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

}
   
   
    
    

