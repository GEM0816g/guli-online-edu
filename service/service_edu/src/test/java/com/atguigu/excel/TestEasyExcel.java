package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作

        //1 设置写入文件家地址和excel文件名称
        String filenamne ="D:\\write.xlsx";

        EasyExcel.write(filenamne,DemoData.class).sheet("学生列表").doWrite(getData());

        //
    }
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setSname("AAA"+i);
            list.add(data);
        }
        return list;
    }
}
