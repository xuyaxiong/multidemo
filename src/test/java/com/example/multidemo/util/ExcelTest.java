package com.example.multidemo.util;

import cn.hutool.poi.excel.ExcelWriter;
import com.example.multidemo.model.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ExcelTest {
    @Test
    public void exportExcel() throws IllegalAccessException {
        List<User> userList = Arrays.asList(
                new User("user1", 1),
                new User("user2", 2),
                new User("user3", 3)
        );
        ExcelWriter excelWriter = ExcelUtils.excel(User.class, userList, "用户表");
        System.out.printf(excelWriter.toString());
        excelWriter.setDestFile(new File("test.xlsx"));
        excelWriter.flush();
        excelWriter.close();
    }
}
