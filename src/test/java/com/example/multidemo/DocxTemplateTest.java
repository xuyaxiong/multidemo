package com.example.multidemo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.Includes;
import com.example.multidemo.model.AddrModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            List<AddrModel> subData = new ArrayList<>();
            subData.add(new AddrModel("Hangzhou,China"));
            subData.add(new AddrModel("Shanghai,China"));
            put("nested", Includes.ofLocal("sub_template.docx").setRenderModel(subData).create());
        }}).writeToFile("out_template.docx");
    }
}
