package me.rflores.clienteapp.controllers;

import me.rflores.clienteapp.models.entities.Cliente;
import me.rflores.clienteapp.services.ClienteService;
import me.rflores.clienteapp.services.ExcelReportService;
import me.rflores.clienteapp.services.PdfReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ExcelReportService excelReportService;
    private final PdfReportService pdfReportService;
    private final ClienteService clienteService;

    public ReportController(ExcelReportService excelReportService, PdfReportService pdfReportService, ClienteService clienteService) {
        this.excelReportService = excelReportService;
        this.pdfReportService = pdfReportService;
        this.clienteService = clienteService;
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> downloadExcelReport() throws IOException {
        List<Cliente> clientes = clienteService.listarTodos();
        byte[] excelBytes = excelReportService.generateExcelReport(clientes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes.xlsx");

        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> downloadPdfReport() throws IOException {
        List<Cliente> clientes = clienteService.listarTodos();
        byte[] pdfBytes = pdfReportService.generatePdfReport(clientes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}

