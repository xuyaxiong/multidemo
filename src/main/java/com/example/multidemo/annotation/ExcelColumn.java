package com.example.multidemo.annotation;

import java.lang.annotation.*;

/**
 * @author: 徐亚雄
 * @date: 2022/4/19 12:34
 * @description: excel导出字段注解
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * 标题
     */
    String title() default "";

    /**
     * 排序
     */
    int sort() default 0;
}

