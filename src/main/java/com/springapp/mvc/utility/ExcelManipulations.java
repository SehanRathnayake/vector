package com.springapp.mvc.utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by Sehan Rathnayake on 17/02/21.
 */
public class ExcelManipulations {
    public static void insertToSpreadsheet(String url, String excelFileName) {
        String chassiData = url + "\\2-datalog.txt";
        File excelFile = new File(url + "\\" + excelFileName);
        String axcelData = url + "\\1-datalog.txt";

        InputStream targetStream = null;
        BufferedReader br = null;

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("chassi");


            String sCurrentLine;
            br = new BufferedReader(new FileReader(chassiData));
            int i = 0;
            Row row;
            String[] parts;

            while ((sCurrentLine = br.readLine()) != null) {

                row = sheet.createRow(i);
                parts = sCurrentLine.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    row.createCell(j).setCellValue(Integer.valueOf(parts[j].trim()));
                }
                i++;
            }

            br.close();
            br = new BufferedReader(new FileReader(axcelData));
            sheet = workbook.createSheet("axcel");
            i = 0;
            while ((sCurrentLine = br.readLine()) != null) {

                row = sheet.createRow(i);
                parts = sCurrentLine.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    row.createCell(j).setCellValue(Integer.valueOf(parts[j].trim()));
                }
                i++;
            }
            br.close();

            // targetStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<double[]> getSignal(String url, String sheet) {
        File initialFile = new File(url);
        InputStream targetStream = null;
        ArrayList<double[]> signal = new ArrayList<double[]>();
        try {

            targetStream = new FileInputStream(initialFile);

            XSSFWorkbook workbook = new XSSFWorkbook(targetStream);

            XSSFSheet inputSheet = workbook.getSheet(sheet);
            Iterator<Row> rowIteratorRead = inputSheet.iterator();

            Row rowRead;
            double[] temp;
            while (rowIteratorRead.hasNext()) {
                rowRead = rowIteratorRead.next();
                temp = new double[]{rowRead.getCell(3).getNumericCellValue(), rowRead.getCell(0).getNumericCellValue(), rowRead.getCell(1).getNumericCellValue(), rowRead.getCell(2).getNumericCellValue()};
                signal.add(temp);
            }
            workbook.close();
            targetStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return signal;
    }

    public static void writeToExcel(String url, String sheetName, ArrayList<double[]> data) {
        File initialFile = new File(url);
        InputStream targetStream = null;
        ArrayList<double[]> signal = new ArrayList<double[]>();
        try {

            targetStream = new FileInputStream(initialFile);

            XSSFWorkbook workbook = new XSSFWorkbook(targetStream);
            XSSFSheet sheet = workbook.createSheet(sheetName);
            Iterator<double[]> iterator = data.iterator();
            double[] values;
            int i = 0;
            Row row;
            while (iterator.hasNext()) {
                values = iterator.next();
                row = sheet.createRow(i);
                for (int j = 0; j < values.length; j++) {
                    row.createCell(j).setCellValue(values[j]);
                }
                i++;
            }
            targetStream.close();
            FileOutputStream outputStream = new FileOutputStream(new File(url));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToExcel(String url, String sheetName, LinkedHashMap<String, Double> data) {
        File initialFile = new File(url);
        InputStream targetStream = null;
        ArrayList<double[]> signal = new ArrayList<double[]>();
        try {

            targetStream = new FileInputStream(initialFile);

            XSSFWorkbook workbook = new XSSFWorkbook(targetStream);
            XSSFSheet sheet = workbook.createSheet(sheetName);

            int i = 0;
            Row row;

            Iterator it = data.entrySet().iterator();
            while (it.hasNext()) {
                row = sheet.createRow(i);
                Map.Entry pair = (Map.Entry) it.next();
                row.createCell(0).setCellValue(pair.getKey().toString());
                row.createCell(1).setCellValue(Double.parseDouble(pair.getValue().toString()));
                it.remove();
                i++;
            }


            targetStream.close();
            FileOutputStream outputStream = new FileOutputStream(new File(url));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<double[]> getSignalFromText(String url) {

        InputStream targetStream = null;
        BufferedReader br = null;

        ArrayList<double[]> signal = new ArrayList<double[]>();

        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(url));
            int i = 0;
            Row row;
            String[] parts;
            double[] temp;
            while ((sCurrentLine = br.readLine()) != null) {


                parts = sCurrentLine.split(" ");
                temp = new double[4];
                temp[0] = Double.parseDouble(parts[3]);
                for (int j = 0; j < parts.length - 1; j++) {
                    temp[j + 1] = Double.parseDouble(parts[j]);
                }
                signal.add(temp);

            }

            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return signal;
    }

}
