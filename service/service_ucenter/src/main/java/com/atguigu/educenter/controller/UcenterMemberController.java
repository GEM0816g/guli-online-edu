package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.RegisterVo;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-19
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;


    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token =memberService.login(member);
        return R.ok().data("token",token);
    }


    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }


    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //调用jwt工具类的方法，根据request对象获取头信息，返回用户id
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberIdByJwtToken);
        return R.ok().data("userInfo",member);
    }

    //查询某一天注册的人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day){
       Integer count= memberService.countRegisterDay(day);
       return R.ok().data("countRegister",count);
    }
}

