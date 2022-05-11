package com.example.multidemo.util;

import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Locale;

/**
 * freemarker模板生成PDF工具类
 */
public class PDFTemplateUtils {

    /**
     * classpath路径
     */
    private String classpath = getClass().getResource("/").toString();

    /**
     * 指定FreeMarker模板文件路径
     */
    private String templatePath = "/pdf";

    /**
     * 图片路径 —— 默认是classpath下面的images文件夹
     */
    private String imagePath = "images/";

    /**
     * 字体资源文件路径
     */
    private String fontPath = "pdf/font/";

    /**
     * 字体   [宋体][simsun.ttc]   [黑体][simhei.ttf]
     */
    private String font = "simsun.ttc";

    /**
     * 指定编码
     */
    private String encoding = "UTF-8";

    /**
     * 生成pdf
     *
     * @param templateFileName 模板名
     * @param data             数据
     * @param out              输出流
     */
    public void createPDF(String templateFileName, Object data, OutputStream out) {
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        cfg.setClassForTemplateLoading(getClass(), templatePath);
        ITextRenderer renderer = new ITextRenderer();
        try {
            renderer.getFontResolver().addFont(classpath + fontPath + font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            cfg.setEncoding(Locale.CHINA, encoding);
            Template template = cfg.getTemplate(templateFileName, encoding);
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            String html = writer.toString();
            renderer.setDocumentFromString(html);
            renderer.getSharedContext().setBaseURL(classpath + imagePath);
            renderer.layout();
            renderer.createPDF(out, false);
            renderer.finishPDF();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

