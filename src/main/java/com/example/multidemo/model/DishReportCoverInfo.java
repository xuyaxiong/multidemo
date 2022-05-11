package com.example.multidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishReportCoverInfo {
    /**
     * 菜名
     */
    private String dishName;
    /**
     * 报告人
     */
    private String reportPerson;
    /**
     * 报告时间
     */
    private String reportTime;
}
