package me.rflores.clienteapp.services;

import me.rflores.clienteapp.models.entities.Cliente;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelReportService {

    public byte[] generateExcelReport(List<Cliente> clientes) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Clientes");

            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Nombre", "Apellido", "Correo", "Telefono"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowIdx = 1;
            for (Cliente cliente : clientes) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(cliente.getId());
                row.createCell(1).setCellValue(cliente.getNombre());
                row.createCell(2).setCellValue(cliente.getApellido());
                row.createCell(3).setCellValue(cliente.getCorreo());
                row.createCell(4).setCellValue(cliente.getTelefono());
            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }
}
