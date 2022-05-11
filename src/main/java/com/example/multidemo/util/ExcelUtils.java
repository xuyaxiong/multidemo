package com.example.multidemo.util;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.multidemo.annotation.ExcelColumn;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: 徐亚雄
 * @date: 2022/4/18 18:09
 * @description: 导出excel
 */
public class ExcelUtils {

    private static class Column {
        public String attrName;
        public String colName;
        public int sort;

        public Column(String attrName, String colName, int sort) {
            this.attrName = attrName;
            this.colName = colName;
            this.sort = sort;
        }
    }

    /**
     * 获取列名
     */
    private static HashMap<String, String> getColList(Class clazz) {
        HashMap<String, String> map = new LinkedHashMap<>(); // 保证相对顺序
        Field[] fieldList = clazz.getDeclaredFields();
        List<Column> list = Arrays.stream(fieldList).sequential()
                .filter(field -> { // 过滤不含ExcelColumn注解的属性
                            field.setAccessible(true);
                            return field.getAnnotation(ExcelColumn.class) != null;
                        }
                ).map(field -> {
                    ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                    return new Column(field.getName(), column.title(), column.sort());
                })
                .sorted(Comparator.comparingInt(a -> a.sort)) // 从小到大排序
                .collect(Collectors.toList());
        for (Column column : list) {
            map.put(column.attrName, column.colName);
        }
        return map;
    }

    /**
     * 通过反射获取属性字典
     */
    private static HashMap<String, String> reflect(Object obj) throws IllegalAccessException, IllegalArgumentException {
        if (obj == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(obj);
            map.put(field.getName(), o == null ? "" : o.toString().trim());
        }
        return map;
    }

    /**
     * 输出excel
     */
    public static <T> ExcelWriter excel(Class clazz, List<T> list, String title) throws IllegalAccessException {
        HashMap<String, String> headerMap = ExcelUtils.getColList(clazz);
        ExcelWriter writer = ExcelUtil.getBigWriter();
        Sheet sheet = writer.getSheet();
        for (int i = 0; i < headerMap.size(); ++i) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        writer.merge(headerMap.size() - 1, title);
        writer.writeRow(headerMap.values());
        int row = 1;
        for (T t : list) {
            Map<String, String> fieldMap = ExcelUtils.reflect(t);
            int currRow = ++row;
            int col = 0;
            for (String key : headerMap.keySet()) {
                String value = fieldMap.get(key);
                writer.writeCellValue(col++, currRow, value);
            }
        }
        return writer;
    }

    public static void writeExcel(String fileName, ExcelWriter writer, HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream()) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            response.setContentType("application/vnd.openmosix-officiated.spreadsheet.sheet");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            writer.flush(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

