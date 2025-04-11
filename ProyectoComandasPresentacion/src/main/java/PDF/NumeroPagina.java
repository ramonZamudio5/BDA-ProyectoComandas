/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
/**
 *
 * @author Cricri
 */
public class NumeroPagina  extends PdfPageEventHelper  {
     private Font font = FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.GRAY);

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        Phrase footer = new Phrase("Página " + writer.getPageNumber(), font);

        ColumnText.showTextAligned(
            cb,
            Element.ALIGN_CENTER,
            footer,
            (document.right() - document.left()) / 2 + document.leftMargin(),
            document.bottom() - 10,
            0
        );
    }
}
