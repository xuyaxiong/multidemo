package com.example.multidemo.model;

import com.example.multidemo.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @ExcelColumn(title = "姓名", sort = 1)
    private String name;
    @ExcelColumn(title = "性别", sort = 2)
    private Integer sex;
}
