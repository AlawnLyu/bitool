package com.wtown.util.common.excelutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;

public class POIUtils {

    private static POIUtils pu = new POIUtils();
    private Workbook wb;
    private int curColIndex;
    private int curRowIndex;

    private POIUtils() {
    }

    public static POIUtils getInstance() {
        return pu;
    }

    private void initWorkbook() {
        if (this.wb == null) {
            this.wb = new SXSSFWorkbook(100);
        }
        this.curColIndex = 0;
        this.curRowIndex = 0;
    }

    public void exportDetailObj2Excel(List objs, Class clz, Map<String, String> map) {
        initWorkbook();
        handlerDetailObj2Excel(objs, clz, getRestName(objs, clz), map);
    }

    public void exportSummaryObj2Excel(List objs1, Class clz1, List objs2, Class clz2, Map<String, String> map) {
        initWorkbook();
        handlerSummaryObj2Excel(objs1, clz1, objs2, clz2, map);
    }

    private void handlerDetailObj2Excel(List objs, Class clz, String rName, Map<String, String> map) {
        try {
            Sheet sh = wb.createSheet(rName);
            // 设置字体
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 14);
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,
                    8);
            sh.addMergedRegion(cellRangeAddress);

            Row titleRow = createNewRow(sh);
            Cell cell = titleRow.createCell(0);
            cell.setCellType(CellType.STRING);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new XSSFRichTextString(map.get("title")));

            Row dateRow = createNewRow(sh);
            dateRow.createCell(0).setCellValue(map.get("date1"));
            dateRow.createCell(8).setCellValue(map.get("date2"));

            Row headerRow = createNewRow(sh);
            List<ExcelHeader> headers = getHeaderList(clz);
            Collections.sort(headers);
            //写标题
            for (int i = 0; i < headers.size(); i++) {
                headerRow.createCell(i).setCellValue(headers.get(i).getTitle());
            }
            //写数据
            for (Object obj : objs) {
                Row dataRow = this.createNewRow(sh);
                for (ExcelHeader eh : headers) {
                    Method m = clz.getDeclaredMethod(getMethodName(eh));
                    Object rel = m.invoke(obj);
                    createCell(rel, dataRow);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void handlerSummaryObj2Excel(List objs1, Class clz1, List objs2, Class clz2, Map<String, String> map) {
        try {
            Sheet sh = wb.createSheet("Worksheet");
            // 设置字体
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 14);
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,
                    10);
            sh.addMergedRegion(cellRangeAddress);

            Row titleRow = createNewRow(sh);
            Cell cell = titleRow.createCell(0);
            cell.setCellType(CellType.STRING);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new XSSFRichTextString(map.get("title")));

            Row dateRow = createNewRow(sh);
            dateRow.createCell(0).setCellValue(map.get("date1"));
            dateRow.createCell(10).setCellValue(map.get("date2"));

            Row headerRow = createNewRow(sh);
            List<ExcelHeader> headers1 = getHeaderList(clz1);
            Collections.sort(headers1);
            //写标题
            for (int i = 0; i < headers1.size(); i++) {
                createCell(headers1.get(i).getTitle(), headerRow);
            }
            List<ExcelHeader> headers2 = getHeaderList(clz2);
            Collections.sort(headers2);
            createCell("", headerRow);
            for (int i = 0; i < headers2.size(); i++) {
                createCell(headers2.get(i).getTitle(), headerRow);
            }
            //写数据
            for (int i = 0; i < Math.max(objs1.size(), objs2.size()); i++) {
                Row dataRow = this.createNewRow(sh);
                if (i < objs1.size()) {
                    for (ExcelHeader eh : headers1) {
                        Method m = clz1.getDeclaredMethod(getMethodName(eh));
                        Object rel = m.invoke(objs1.get(i));
                        createCell(rel, dataRow);
                    }
                }
                if (i < objs2.size()) {
                    createCell("", dataRow);
                    for (ExcelHeader eh : headers2) {
                        Method m = clz2.getDeclaredMethod(getMethodName(eh));
                        Object rel = m.invoke(objs2.get(i));
                        createCell(rel, dataRow);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String filepath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath);
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("写入的文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("写入数据失败:" + e.getMessage());
        } finally {
            dispose(fos);
        }
    }

    private void dispose(FileOutputStream fos) {
        try {
            if (fos != null) {
                fos.close();
                ((SXSSFWorkbook) wb).dispose();
                wb = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Row createNewRow(Sheet sh) {
        Row r = sh.createRow(curRowIndex);
        curRowIndex++;
        curColIndex = 0;
        return r;
    }

    private void createCell(Object param, Row r) {
        if (param instanceof Integer) {
            int value = ((Integer) param).intValue();
            createCell(value, r);
        } else if (param instanceof Double) {
            double d = ((Double) param).doubleValue();
            createCell(d, r);
        } else if (param instanceof Float) {
            float f = ((Float) param).floatValue();
            createCell(f, r);
        } else if (param instanceof Long) {
            long l = ((Long) param).longValue();
            createCell(l, r);
        } else if (param instanceof Boolean) {
            boolean b = ((Boolean) param).booleanValue();
            createCell(b, r);
        } else if (param instanceof Date) {
            Date d = (Date) param;
            createCell(d, r);
        } else {
            String s = (String) param;
            createCell(s, r);
        }
    }

    private void createCell(String value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue(value);
        curColIndex++;
    }

    private void createCell(int value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue((int) value);
        curColIndex++;
    }

    private void createCell(Date value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue(value);
        curColIndex++;
    }

    private void createCell(double value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue(value);
        curColIndex++;
    }

    private void createCell(float value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue(value);
        curColIndex++;
    }

    private void createCell(boolean value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue(value);
        curColIndex++;
    }

    private void createCell(Calendar value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue(value);
        curColIndex++;
    }

    private void createCell(BigInteger value, Row r) {
        Cell c = r.createCell(curColIndex);
        setCellStyle(c);
        c.setCellValue(value == null ? 0 : value.intValue());
        curColIndex++;
    }

    /**
     * 设置某个元素的样式
     *
     * @param c
     */
    private void setCellStyle(Cell c) {
//        if (styles.containsKey(curColIndex)) {
//            c.setCellStyle(styles.get(curColIndex));
//        } else {
//            c.setCellStyle(defaultStyle);
//        }
    }

    private String getRestName(List objs, Class clz) {
        try {
            Method m = clz.getDeclaredMethod("getRname");
            Object rel = m.invoke(objs.get(0));
            return (String) rel;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "Worksheet";
    }

    private String getMethodName(ExcelHeader eh) {
        return eh.getMethodName();
    }

    private String getFieldName(ExcelHeader eh) {
        String mn = eh.getMethodName().substring(3);
        mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
        return mn;
    }

    private List<ExcelHeader> getHeaderList(Class clz) {
        List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
        Method[] ms = clz.getDeclaredMethods();
        for (Method m : ms) {
            String mn = m.getName();
            if (mn.startsWith("get")) {
                if (m.isAnnotationPresent(ExcelResources.class)) {
                    ExcelResources er = m.getAnnotation(ExcelResources.class);
                    headers.add(new ExcelHeader(er.title(), er.order(), mn));
                }
            }
        }
        return headers;
    }
}
