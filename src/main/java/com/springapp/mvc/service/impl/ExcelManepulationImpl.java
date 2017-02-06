package com.springapp.mvc.service.impl;

import com.springapp.mvc.service.ExcelManepulation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by Sehan Rathnayake on 16/12/14.
 */
@Service
public class ExcelManepulationImpl implements ExcelManepulation {

    public void loadExcelFile() {
        InputStream is = getClass().getResourceAsStream("/data/testData/test4.xlsx");
        URL url = getClass().getResource("/data/testData/test4.xlsx");

        List<Double> list = new ArrayList<Double>();
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet myExcelSheet = wb.getSheet("Sheet1");
            XSSFRow row;
            XSSFRow row2;
            double avgX, avgY, avgZ, avgTime;
            XSSFSheet sheet = wb.createSheet("Procesed");
            row2 = sheet.createRow(0);

            row2.createCell(0).setCellValue("TIme");
            row2.createCell(1).setCellValue("X");
            row2.createCell(2).setCellValue("Y");
            row2.createCell(3).setCellValue("Z");

            double x, y, z, time;
            for (int i = 0; i < 10800; i++) {
                avgX = 0;
                avgY = 0;
                avgZ = 0;
                avgTime = 0;
                for (int j = 0; j < 10; j++) {

                    row = myExcelSheet.getRow(i * 10 + j);

                    x = row.getCell(0).getNumericCellValue();
                    y = row.getCell(1).getNumericCellValue();
                    z = row.getCell(2).getNumericCellValue();
                    time = row.getCell(3).getNumericCellValue();
                    avgX += x;
                    avgY += y;
                    avgZ += z;
                    avgTime += time;

                }
                row2 = sheet.createRow(i + 1);
                row2.createCell(0).setCellValue(avgTime / 10000);
                row2.createCell(1).setCellValue(avgX / 10);
                row2.createCell(2).setCellValue(avgY / 10);
                row2.createCell(3).setCellValue(avgZ / 10);


            }


            is.close();


            FileOutputStream outputStream = new FileOutputStream(new File(url.getFile().replace("%20", " ")));
            wb.write(outputStream);
            wb.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();


        }


    }

    public void preprocessing() {
        InputStream is = getClass().getResourceAsStream("/data/testData/test4.xlsx");
        URL url = getClass().getResource("/data/testData/test4.xlsx");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            workbook = getZeroCrossings(workbook,0,0);
            is.close();


            FileOutputStream outputStream = new FileOutputStream(new File(url.getFile().replace("%20", " ")));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public XSSFWorkbook getZeroCrossings(XSSFWorkbook workbook,int columnRead, int columnWrite) {
        XSSFSheet inputSheet = workbook.getSheet("Raw");
        XSSFSheet outPutsheet = workbook.createSheet("ZeroCrossings");
        Iterator<Row> rowIteratorRead = inputSheet.iterator();
        Row rowRead, rowWrite;
        int rowNoWrite = 0,duration = 0, maximas = 0;
        double befValue, value, afterValue;
        rowRead = rowIteratorRead.next();
        boolean positiveSide = true;
        befValue = rowRead.getCell(columnRead).getNumericCellValue();
        value = rowRead.getCell(columnRead).getNumericCellValue();
        while (rowIteratorRead.hasNext()) {//finding the first zero crossing
            if (befValue * value < 0) {
                positiveSide = value > 0;
                break;
            } else {
                befValue = value;
                rowRead = rowIteratorRead.next();
                value = rowRead.getCell(columnRead).getNumericCellValue();
            }
        }
        while (rowIteratorRead.hasNext()) {
            rowRead = rowIteratorRead.next();
            afterValue = rowRead.getCell(columnRead).getNumericCellValue();
            if (value * afterValue < 0) {
                positiveSide = afterValue > 0;
                rowWrite = outPutsheet.createRow(rowNoWrite++);
                rowWrite.createCell(columnWrite).setCellValue(duration);
                rowWrite.createCell(columnWrite + 1).setCellValue(maximas);
                duration = 0;   maximas = 0;
            } else if ((positiveSide && befValue < value && afterValue < value) ||
                    (!positiveSide && befValue > value && afterValue > value)) {
                maximas++;
            }
            befValue = value;
            value = afterValue;
            duration++;
        }
        return workbook;
    }
}
