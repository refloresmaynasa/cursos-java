package me.rflores.clienteapp.services;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import me.rflores.clienteapp.models.entities.Cliente;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportService {

    public byte[] generatePdfReport(List<Cliente> clientes) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfDoc);

        PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");
        Paragraph title = new Paragraph("Lista de Clientes")
                .setFont(font)
                .setFontSize(18)
                .setMarginBottom(10);
        document.add(title);

        Table table = new Table(new float[]{1, 2, 2, 2, 2});
        table.setWidth(UnitValue.createPercentValue(100));

        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Apellido");
        table.addCell("Correo");
        table.addCell("Telefono");

        for (Cliente cliente : clientes) {
            table.addCell(String.valueOf(cliente.getId()));
            table.addCell(cliente.getNombre());
            table.addCell(cliente.getApellido());
            table.addCell(cliente.getCorreo());
            table.addCell(cliente.getTelefono());
        }

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}

