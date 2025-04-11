/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF;

// Clases de iText para trabajar con PDF
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

// Clases estándar de Java
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// DTOs personalizados
import dtos.ClienteFrecuenteDTO;
import dtos.ComandaDTO;



/**
 *
 * @author Cricri
 */
public class itext {
   
  
    public static void crearPDFClientes(List<ClienteFrecuenteDTO> clientes, String rutaDestino) throws Exception {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(rutaDestino));
        try {
            doc.open();

      
            agregarTitulo(doc, "Resumen de Clientes Frecuentes");
            agregarFechaGeneracion(doc);

            PdfPTable tablaClientes = new PdfPTable(6);
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


    private static void agregarTitulo(Document doc, String texto) throws DocumentException {
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.DARK_GRAY);
        Paragraph titulo = new Paragraph(texto, fuenteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        doc.add(titulo);
    }

    private static void agregarFechaGeneracion(Document doc) throws DocumentException {
        Font fuenteFecha = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
        Paragraph fecha = new Paragraph(
            "Generado el: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            fuenteFecha
        );
        fecha.setAlignment(Element.ALIGN_RIGHT);
        fecha.setSpacingAfter(10);
        doc.add(fecha);
    }

   
    private static void agregarEncabezadosClientes(PdfPTable tabla) {
        String[] columnas = {"Nombre", "Teléfono", "Correo", "Fecha Registro", "Puntos", "Gasto Total"};
        for (String columna : columnas) {
            PdfPCell celda = new PdfPCell(new Phrase(columna));
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celda.setPadding(5);
            tabla.addCell(celda);
        }
    }

   
    private static void agregarFilaCliente(PdfPTable tabla, ClienteFrecuenteDTO cliente) {
        tabla.addCell(cliente.getNombreCompleto());
        tabla.addCell(cliente.getTelefono());
        tabla.addCell(cliente.getCorreoElectronico());
        tabla.addCell(
            cliente.getFechaRegistro() != null
                ? cliente.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "N/A"
        );
        tabla.addCell(String.valueOf(cliente.getPuntosObtenidos()));
        tabla.addCell(String.format("$%,.2f", cliente.getGastoTotalAcumulado()));
    }
}


