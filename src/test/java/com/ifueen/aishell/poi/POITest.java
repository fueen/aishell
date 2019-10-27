package com.ifueen.aishell.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class POITest {

    /**
     * 通过POI创建新的工作簿
     */
    @Test
    public void testCreatePOI() throws Exception {
        /**
         * 首先创建Excel文件
         */
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        //创建一张表
        Sheet sheet = workbook.createSheet("99乘法表");
        //先创建行
        for (int i = 1; i <=9 ; i++) {
            Row row = sheet.createRow(i-1);
            //创建列
            for (int j = 1; j <=i ; j++) {
                Cell cell = row.createCell(j-1);
                //往单元格里面添加数据
                cell.setCellValue(i+"x"+j+"="+(i*j));
            }
        }
        //通过流的方式输出
        FileOutputStream fileOutputStream = new FileOutputStream("S9财富密码.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    /**
     * 通过POI读取Excel文件
     */
    @Test
    public void testReadSheet() throws Exception {
        //将要读取的文件放到内存中
        Workbook workbook = WorkbookFactory.create(new FileInputStream("S9八强预测.xlsx"));
        //获取到第一张表
        Sheet sheetAt = workbook.getSheetAt(0);
        //拿到总行数
        int lastRowNum = sheetAt.getLastRowNum();
        //遍历
        for (int i = 1; i <=lastRowNum; i++) {
            Row row = sheetAt.getRow(i);
            //拿到总共的单元格
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j <lastCellNum ; j++) {
                //获取单元格里的数据
                Cell cell = row.getCell(j);
                //输出
                System.out.println(cell.getStringCellValue());
            }
        }
    }

}
