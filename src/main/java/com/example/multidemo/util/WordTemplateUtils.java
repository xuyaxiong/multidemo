package com.example.multidemo.util;

import freemarker.template.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class WordTemplateUtils {

    /**
     * 指定FreeMarker模板文件路径
     */
    private String templatePath = "/pdf";

    /**
     * 指定编码
     */
    private String encoding = "UTF-8";


    /**
     * @param templateFileName 模板名
     * @param map              数据
     * @param localPath        生成word文档的位置
     * @param title            word文档名
     */
    public void exportWord(String templateFileName, Map map, String localPath, String title) {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setClassForTemplateLoading(getClass(), templatePath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String path = localPath + "/word";
        //定义word文档名称
        String fileName = title + "_" + dateStr + ".doc";
        try {
            //创建文件夹
            File outFile = new File(path + "/" + fileName);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            //读取模板内容
            configuration.setEncoding(Locale.CHINA, encoding);
            Template template = configuration.getTemplate(templateFileName, encoding);
            Writer out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(outFile.toPath()), StandardCharsets.UTF_8));
            template.process(map, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param templateFileName 模板名
     * @param map              数据
     * @param out              输出流
     */
    public void exportWord(String templateFileName, Map map, OutputStream out) {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setClassForTemplateLoading(getClass(), templatePath);
        try {
            configuration.setEncoding(Locale.CHINA, encoding);
            Template template = configuration.getTemplate(templateFileName, encoding);
            Writer writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            template.process(map, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
