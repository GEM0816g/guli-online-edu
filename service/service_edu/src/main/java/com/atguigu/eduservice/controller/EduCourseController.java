package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-08-19
 */

@RestController
@RequestMapping("/eduservice/educourse")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id=courseService.saveCourseInfo(courseInfoVo);
       return R.ok().data("courseId",id);
    }

    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }


    //根据课程id查询课程确认信息
    @GetMapping("getPublshCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo courseInfoVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",courseInfoVo);
    }


    //课程列表 基本实现
    //TODO  完善条件查询带分页
    @GetMapping("course/{current}/{limit}")
    public R getCourseList(@PathVariable long current,
                           @PathVariable long limit) {

        Page<EduCourse> pageTeacher = new Page<>(current,limit);

        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        courseService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduCourse> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("list",records).data("total",total);
    }
}

