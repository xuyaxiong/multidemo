package com.example.multidemo;

import com.deepoove.poi.XWPFTemplate;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class DocxTemplateTest {
    @Test
    void generateDocxTest() throws IOException {
        //The core API uses a minimalist design, only one line of code is required
        XWPFTemplate.compile("template.docx").render(new HashMap<String, Object>() {{
            put("title", "这是标题");
            put("content", "这是正文");
        }}).writeToFile("out_template.docx");
    }
}
