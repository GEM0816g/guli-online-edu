package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.excel.TestData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener1 extends AnalysisEventListener<TestData> {

/*因为SubjectExcelListener不能交给spring进行管理，需要自己new 不能注入其他对象*/
    public EduSubjectService subjectService;
    public SubjectExcelListener1(){};
    public SubjectExcelListener1(EduSubjectService subjectService){
        this.subjectService=subjectService;
    }


    //读取excel内容，一行一行进行读取
    @Override
    public void invoke(TestData testData, AnalysisContext analysisContext) {



    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
