package com.tcwong.utils;

import com.tcwong.bean.Position;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class POIUtils {

    public static ResponseEntity<byte[]> export(List<Position> positions) throws IOException {
        //创建一个excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建excel属性配置
        workbook.createInformationProperties();
        //获取并且配置文档属性
        DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();
        information.setCategory("职位表");
        information.setManager("管理员");
        //创建表单
        HSSFSheet sheet = workbook.createSheet("职位信息");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        cell0.setCellValue("编号");
        cell1.setCellValue("职位名称");
        cell2.setCellValue("创建时间");
        cell3.setCellValue("是否可用");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        for (int i = 0; i < positions.size(); i++) {
            Position position = positions.get(i);
            HSSFRow row1 = sheet.createRow(i + 1);
            HSSFCell c0 = row1.createCell(0);
            HSSFCell c1 = row1.createCell(1);
            HSSFCell c2 = row1.createCell(2);
            HSSFCell c3 = row1.createCell(3);
            c0.setCellValue(position.getId());
            c1.setCellValue(position.getName());
            c2.setCellValue(position.getCreatedate());
            c2.setCellStyle(cellStyle);
            c3.setCellValue(position.getEnabled() ? "是" : "否");
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
                new String("职位表.xls".getBytes("UTF-8"), "iso-8859-1"));
        return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.CREATED);
    }
}
