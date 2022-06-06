package com.example.multidemo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class DocxTemplateTest {
    @Test
    void generateDocxTest() throws IOException {
        //The core API uses a minimalist design, only one line of code is required
        ConfigureBuilder builder = Configure.builder();
        builder.useSpringEL();
        XWPFTemplate.compile("template.docx", builder.build()).render(new HashMap<String, Object>() {{
            put("title", "这是标题");
            put("content", "这是正文");
            put("sex", true);
        }}).writeToFile("out_template.docx");
    }
}
