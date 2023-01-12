package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.educenter.entity.RegisterVo;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.management.Query;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-19
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
 @Autowired
 private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember member) {
        //获取登录手机号和密码
        String mobile =member.getMobile();
        String password=member.getPassword();
        String encryptpassword = MD5.encrypt(password);

        //手机号和密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登录失败,账号或密码为空");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember=baseMapper.selectOne(wrapper);

        //判断查询对象是否为空
        if (mobileMember==null){
            throw new GuliException(20001,"登陆失败，不存在");
        }
        //判断密码
        if (!encryptpassword.equals(mobileMember.getPassword())){
            throw new GuliException(20001,"登陆失败,密码错误");
        }
        //判断用户是否禁用
        if (mobileMember.getIsDisabled()){
            throw new GuliException(20001,"登录失败，账户被禁用");
        }

        //登陆成功
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());


        return jwtToken;
    }
    /*
    * 注册
    * */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code=registerVo.getCode();
        String mobile=registerVo.getMobile();
        String nickname=registerVo.getNickname();
        String password=registerVo.getPassword();

        //非空判断
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)
        || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)){
            throw new GuliException(20001,"注册失败,手机号或验证码或名称或密码为空");

        }

        //判断验证码
        //获取redis验证码

        String redisCode = redisTemplate.opsForValue().get(mobile);

        System.out.println(redisCode);
        System.out.println(code);

        if (!code.equals(redisCode)){
            throw new GuliException(20001,"注册失败，验证码错误");
        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer integer = baseMapper.selectCount(queryWrapper);
        if (integer>0){
            throw new GuliException(20001,"注册失败，手机号已经存在");
        }


        //数据添加道数据库中
        UcenterMember member=new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKxCqRzuYWQmpwiaqQEjNxbC7WicebicXQusU306jgmfoOzUcFg1qaDq5BStiblwBjw5dUOblQ2gUicQOQ/132");

        baseMapper.insert(member);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member=baseMapper.selectOne(wrapper);
        return member;
    }

    //查询某一天注册的人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
