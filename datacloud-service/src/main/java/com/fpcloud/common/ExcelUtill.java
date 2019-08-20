package com.fpcloud.common;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ExcelUtill {
    public  static String getCellValue(Cell cell) {

        String result = new String();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case Cell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_BLANK:
                result = "";
                break;
            default:
                result = "";
                break;
        }
        result =  result.replaceAll("'", "");
        return result;
    }
    public static String getCellValue(int colNum,Row row){
        Cell cell =  row.getCell(colNum);
        return  cell!=null?getCellValue(cell):"";
    }
    public static void copyRow(Row sourceRow, Row targetRow){
        int cellNum = sourceRow.getLastCellNum();
        for (int i = 0;i<cellNum;i++) {
            Cell targetCell = targetRow.createCell(i);
            targetCell.setCellValue(getCellValue(i,sourceRow));
        }

    }
    public static void copyRow(Row sourceRow, Row targetRow,String addfiled){
        int cellNum = sourceRow.getLastCellNum();
        for (int i = 1;i<cellNum+1;i++) {
            Cell targetCell = targetRow.createCell(i);
            targetCell.setCellValue(getCellValue(i,sourceRow));
        }
        Cell targetCell = targetRow.createCell(0);
        targetCell.setCellValue(addfiled);

    }
    public static void setStyle(Cell cell, String value, short fg, CellStyle style) {
        style.setFillForegroundColor(fg);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(style);
    }
}
