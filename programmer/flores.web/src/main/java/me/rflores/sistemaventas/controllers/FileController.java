package me.rflores.sistemaventas.controllers;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.rflores.sistemaventas.modelos.entidades.Cliente;
import me.rflores.sistemaventas.servicios.ClienteServicio;
import me.rflores.sistemaventas.servicios.impl.ClienteServicioImpl;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/file-generation")
public class FileController extends HttpServlet {

    private List<Cliente> clientes = new ArrayList<>();
    private ClienteServicio servicio;

    @Override
    public void init() throws ServletException {
        super.init();
        servicio = new ClienteServicioImpl();
        clientes = servicio.listar();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String format = request.getParameter("format");

        if ("pdf".equalsIgnoreCase(format)) {
            generatePdf(response);
        } else if ("excel".equalsIgnoreCase(format)) {
            generateExcel(response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported format");
        }
    }

    private void generatePdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=clientes.pdf");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDoc);

        // Add logo to the PDF
        String logoPath = getServletContext().getRealPath("/WEB-INF/logo.png");
        Image logo = new Image(ImageDataFactory.create(logoPath));
        logo.setHeight(50);
        logo.setWidth(100);
        document.add(logo);

        // Add Title
        PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");
        Paragraph title = new Paragraph("List of Clientes")
                .setFont(font)
                .setFontSize(18)
                .setMarginBottom(10);
        document.add(title);

        // Create Table
        Table table = new Table(new float[]{1, 2, 2, 2, 2, 2});
        table.setWidth(UnitValue.createPercentValue(100));

        // Header
        table.addHeaderCell(new Cell().add(new Paragraph("Código")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Nombre")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Apellido")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Email")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Teléfono")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Fecha Registro")).setBackgroundColor(ColorConstants.LIGHT_GRAY));

        // Data
        for (Cliente cliente : clientes) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(cliente.getCodigo()))));
            table.addCell(new Cell().add(new Paragraph(cliente.getNombre())));
            table.addCell(new Cell().add(new Paragraph(cliente.getApellido())));
            table.addCell(new Cell().add(new Paragraph(cliente.getEmail())));
            table.addCell(new Cell().add(new Paragraph(cliente.getTelefono())));
            table.addCell(new Cell().add(new Paragraph(cliente.getFechaRegistro().toString())));
        }

        document.add(table);
        document.close();

        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }

    private void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=clientes.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clientes");

        // Header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Apellido");
        headerRow.createCell(3).setCellValue("Email");
        headerRow.createCell(4).setCellValue("Teléfono");
        headerRow.createCell(5).setCellValue("Fecha Registro");

        // Data
        int rowNum = 1;
        for (Cliente cliente : clientes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cliente.getCodigo());
            row.createCell(1).setCellValue(cliente.getNombre());
            row.createCell(2).setCellValue(cliente.getApellido());
            row.createCell(3).setCellValue(cliente.getEmail());
            row.createCell(4).setCellValue(cliente.getTelefono());
            row.createCell(5).setCellValue(cliente.getFechaRegistro().toString());
        }

        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.flush();
        os.close();
    }
}
