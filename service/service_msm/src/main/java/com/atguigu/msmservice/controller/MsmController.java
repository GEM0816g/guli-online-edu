package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.service.impl.MsmServiceImpl;
import com.atguigu.msmservice.utils.RandomUtil;
import com.atguigu.servicebase.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/edumsm/msm")

public class MsmController {
    @Autowired
    private RedisTemplate redisTemplate;
     MsmService msmService=new MsmServiceImpl();
     RedisConfig redisConfig=new RedisConfig();
     RedisConnectionFactory factory;


    //发送短信的方法
    @GetMapping("send/{phone}")
    public R senMsm(@PathVariable String phone){
        //1从redis获取验证码，如果获取到直接返回
        Integer code= (Integer) redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //生成随机值
        code= Integer.valueOf(RandomUtil.getFourBitRandom());
        if (phone.isEmpty()){
            return R.error().message("手机号不能为空");
        }
        Map<String,String> map=new HashMap<>();
        map.put("code", String.valueOf(code));
        try {
            msmService.sendSort(phone,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5分钟限制
        redisTemplate.opsForValue().set(phone,code,5,TimeUnit.MINUTES);
                return R.ok();
    }
}
