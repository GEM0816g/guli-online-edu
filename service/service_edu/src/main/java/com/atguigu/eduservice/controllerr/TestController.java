package com.atguigu.eduservice.controllerr;


import com.alibaba.excel.EasyExcel;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Subject.OneSubject;
import com.atguigu.eduservice.entity.excel.TestData;
import com.atguigu.eduservice.listener.SubjectExcelListener1;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-05
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class TestController {
@Autowired
    private EduSubjectService subjectService;

//添加课程分类
    //获取上传过来文件，把文件内容读取出来

    @PostMapping("addSubjectt")
    public R addSubject(MultipartFile file){
        List<TestData> tmpList=new ArrayList<>();

        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
           tmpList = EasyExcel.read(in)
                    // 设置与Excel表映射的类
                    .head(TestData.class)
                    // 设置sheet,默认读取第一个
                    .sheet()
                    // 设置标题所在行数
                    .headRowNumber(1)
                    // 异步读取
                    .doReadSync();



        }catch(Exception e){
            e.printStackTrace();
        }
        //上传过来excel文件

        return R.ok().data("tmpList",tmpList);
    }



}

