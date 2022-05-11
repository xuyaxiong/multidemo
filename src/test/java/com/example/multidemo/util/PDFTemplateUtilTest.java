package com.example.multidemo.util;

import com.example.multidemo.model.DishReportCoverInfo;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class PDFTemplateUtilTest {
    @Test
    public void generatePdfTest() throws FileNotFoundException {
        Map<String, Object> data = new HashMap<>();
        FileOutputStream out = new FileOutputStream("test.pdf");
        PDFTemplateUtils pdfUtil = new PDFTemplateUtils();

        /** 装填数据 start **/
        data.put("staticPath", pdfUtil.getClass().getResource("/").getPath() + "static");

        /** 1. 封面数据 start **/
        DishReportCoverInfo coverInfo = new DishReportCoverInfo("鱼香肉丝", "徐亚雄", "2022年5月10日");
        data.put("company", "青岛食联网科技有限公司");
        data.put("coverInfo", coverInfo);
        /** 1. 封面数据 end **/

        /** 装填数据 end **/

        pdfUtil.createPDF("demo.ftl", data, out);
    }
}
