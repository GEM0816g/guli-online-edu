package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TestData {
    @ExcelProperty(value = "语文",index=0)
    private String yuwen;
    @ExcelProperty(value = "数学",index = 1)
    private String shuxue;
    @ExcelProperty(value = "英语",index = 2)
    private String yingyu;

}
