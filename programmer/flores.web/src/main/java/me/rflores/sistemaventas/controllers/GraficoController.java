package me.rflores.sistemaventas.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.rflores.sistemaventas.modelos.entidades.Cliente;
import me.rflores.sistemaventas.servicios.ClienteServicio;
import me.rflores.sistemaventas.servicios.impl.ClienteServicioImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/chart-generation")
public class GraficoController extends HttpServlet {

    private ClienteServicio servicio = new ClienteServicioImpl(); // Assuming you've set up DAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=clientes-pie-chart.png");

        List<Cliente> clientes = servicio.listar();

        DefaultPieDataset dataset = new DefaultPieDataset();
        //DefaultCategoryDataset dataset = new DefaultCategoryDataset();s

        for (Cliente cliente : clientes) {
            dataset.setValue(cliente.getCodigo() + "-" + cliente.getNombre(), cliente.getTotalCompras());
            //dataset.addValue(cliente.getTotalCompras(), "Clientes", cliente.getNombre());
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Total Ventas por Cliente",
                dataset,
                true, true, false
        );

//        JFreeChart barChart = ChartFactory.createBarChart(
//                "Total Compras por Cliente",
//                "Clientes",
//                "Total Compras",
//                dataset
//        );

        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, pieChart, 800, 600);
        }
    }
}

