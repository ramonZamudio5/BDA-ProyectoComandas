/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import dtos.ClienteFrecuenteDTO;
import dtos.ComandaDTO;
import java.time.LocalDate;



/**
 *
 * @author Cricri
 */
public class itext {
    
        private static void agregarTitulo(Document doc, String titulo) throws DocumentException {
        Paragraph encabezado = new Paragraph(titulo, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        encabezado.setSpacingAfter(10f);
        doc.add(encabezado);
    }
    private static void agregarFechaGeneracion(Document doc) throws DocumentException {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Paragraph parrafoFecha = new Paragraph("Fecha de generación: " + fecha,
                FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.DARK_GRAY));
        parrafoFecha.setAlignment(Element.ALIGN_RIGHT);
        parrafoFecha.setSpacingAfter(10f);
        doc.add(parrafoFecha);
    }

   public static void crearPDFClientes(List<ClienteFrecuenteDTO> clientes, String rutaDestino) throws Exception {
    Document doc = new Document();
    PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(rutaDestino));
    writer.setPageEvent(new NumeroPagina());
    try {
        doc.open();

        agregarTitulo(doc, "Resumen de Clientes Frecuentes");
        agregarFechaGeneracion(doc);

        PdfPTable tablaClientes = new PdfPTable(5); 

        tablaClientes.setWidthPercentage(100);
        tablaClientes.setSpacingBefore(15f);

        agregarEncabezadosClientes(tablaClientes);

        for (ClienteFrecuenteDTO cliente : clientes) {
            agregarFilaCliente(tablaClientes, cliente);
        }

        doc.add(tablaClientes);
    } finally {
        if (doc.isOpen()) {
            doc.close();
        }
    }
}

private static void agregarEncabezadosClientes(PdfPTable tabla) {
    String[] columnas = {
        "Nombre", 
        "Fecha Registro", 
        "Puntos", 
        "Total Gastado", 
        "Total Visitas"
    };

    for (String columna : columnas) {
        PdfPCell celda = new PdfPCell(new Phrase(columna));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        celda.setPadding(5);
        tabla.addCell(celda);
    }
}

private static void agregarFilaCliente(PdfPTable tabla, ClienteFrecuenteDTO cliente) {
     //
    tabla.addCell(cliente.getNombreCompleto());     
   

    tabla.addCell(
        cliente.getFechaRegistro() != null
            ? cliente.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            : "N/A"
    );

    tabla.addCell(String.valueOf(cliente.getPuntosObtenidos()));               
    tabla.addCell(String.format("$%,.2f", cliente.getGastoTotalAcumulado()));
    tabla.addCell(String.valueOf(cliente.getConteoVisitas()));                
}

    

    public static void crearPDFComandas(List<ComandaDTO> comandas, String rutaDestino) throws Exception {
        Document doc = new Document();
         PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(rutaDestino));
         writer.setPageEvent(new NumeroPagina());
        try {
            doc.open();

            agregarTitulo(doc, "Resumen de Comandas");
            agregarFechaGeneracion(doc);

            PdfPTable tablaComandas = new PdfPTable(5); 
            tablaComandas.setWidthPercentage(100);
            tablaComandas.setSpacingBefore(15f);

            agregarEncabezadosComanda(tablaComandas);

            for (ComandaDTO comanda : comandas) {
                agregarFilaComanda(tablaComandas, comanda);
            }

            doc.add(tablaComandas);
        } finally {
            if (doc.isOpen()) {
                doc.close();
            }
        }
    }

    private static void agregarEncabezadosComanda(PdfPTable tabla) {
        String[] columnas = {
            "Fecha y Hora",
            "Mesa",
            "Total Venta",
            "Estado",
            "Cliente"
        };

        for (String columna : columnas) {
            PdfPCell celda = new PdfPCell(new Phrase(columna));
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celda.setPadding(5);
            tabla.addCell(celda);
        }
    }

    private static void agregarFilaComanda(PdfPTable tabla, ComandaDTO comanda) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

      
        String fechaHora = comanda.getFechaHoraCreacion() != null
                ? comanda.getFechaHoraCreacion().format(formatter)
                : "N/A";
        tabla.addCell(fechaHora);

              
                tabla.addCell(
            comanda.getMesa() != null 
                ? "Mesa " + comanda.getMesa().getNumero() 
                : "Sin mesa"
        );


        tabla.addCell(String.format("$%,.2f", comanda.getTotalVenta()));

       
        tabla.addCell(comanda.getEstado() != null ? comanda.getEstado().name() : "N/A");

       
        tabla.addCell(comanda.getCliente() != null ? comanda.getCliente().getNombreCompleto() : "No registrado");
    }
}



