package com.integrador1.cafekamila.service;

import com.integrador1.cafekamila.dto.response.PedidoResponseDTO;
import com.integrador1.cafekamila.model.Pedido;
import com.integrador1.cafekamila.repository.PedidoRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelExportService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private PedidoService pedidoService;

    public ByteArrayInputStream exportarPedidos() throws IOException {

        List<Pedido> pedidos = pedidoRepository.findAll();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Pedidos");

        Row encabezado = sheet.createRow(0);

        encabezado.createCell(0).setCellValue("ID");
        encabezado.createCell(1).setCellValue("Cliente");
        encabezado.createCell(2).setCellValue("Tipo");
        encabezado.createCell(3).setCellValue("Estado");
        encabezado.createCell(4).setCellValue("Fecha");
        encabezado.createCell(5).setCellValue("Total");

        int fila = 1;

        for (Pedido pedido : pedidos) {

            Row row = sheet.createRow(fila++);

            row.createCell(0).setCellValue(
                    pedido.getIdPedido()
            );

            row.createCell(1).setCellValue(
                    pedido.getNombreCliente()
            );

            row.createCell(2).setCellValue(
                    pedido.getTipoPedido()
            );

            row.createCell(3).setCellValue(
                    pedido.getEstado().name()
            );

            row.createCell(4).setCellValue(
                    pedido.getFechaHora().toString()
            );

            row.createCell(5).setCellValue(
                    pedido.getTotal()
            );
        }

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(
                out.toByteArray()
        );
    }
    public ByteArrayInputStream exportarHistorial() throws IOException {

        List<PedidoResponseDTO> historial =
                pedidoService.obtenerHistorial();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Historial");

        Row encabezado = sheet.createRow(0);

        encabezado.createCell(0).setCellValue("ID");
        encabezado.createCell(1).setCellValue("Cliente");
        encabezado.createCell(2).setCellValue("Tipo");
        encabezado.createCell(3).setCellValue("Estado");
        encabezado.createCell(4).setCellValue("Fecha");
        encabezado.createCell(5).setCellValue("Total");

        int fila = 1;

        for (PedidoResponseDTO pedido : historial) {

            Row row = sheet.createRow(fila++);

            row.createCell(0).setCellValue(
                    pedido.getIdPedido()
            );

            row.createCell(1).setCellValue(
                    pedido.getNombreCliente()
            );

            row.createCell(2).setCellValue(
                    pedido.getTipoPedido()
            );

            row.createCell(3).setCellValue(
                pedido.getEstado().name()
            );

            row.createCell(4).setCellValue(
                    pedido.getFechaHora().toString()
            );

            row.createCell(5).setCellValue(
                    pedido.getTotal()
            );
        }

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(
                out.toByteArray()
        );
    }
}