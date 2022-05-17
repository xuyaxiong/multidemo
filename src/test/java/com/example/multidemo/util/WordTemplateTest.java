package com.example.multidemo.util;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class WordTemplateTest {

    @Test
    public void exportWord() throws FileNotFoundException {
        Map<String, Object> map = new HashMap<>();
        map.put("dishesName", "片制烤鸭");
        map.put("companyName", "食联网科技有限公司");
        map.put("testTime", "2022/05/17 11:50");
        WordTemplateUtils wordUtils = new WordTemplateUtils();
        FileOutputStream out = new FileOutputStream("test01.doc");
        wordUtils.exportWord("word-demo.ftl", map, out);
    }
}
