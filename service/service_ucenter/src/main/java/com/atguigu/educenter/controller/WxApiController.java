package com.atguigu.educenter.controller;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.educenter.utils.ConstantPropertiesUtil;
import com.atguigu.educenter.utils.HttpClientUtils;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;
    //登录
    @GetMapping("login")
    public String getWxCode(){
        // 微信开放平台授权baseUrl
        //%s占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";


        String redirect_url=ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL;
        try {
            redirect_url= URLEncoder.encode(redirect_url,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url=String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirect_url,
                "atguigu"
        );
        //请求微信地址
        return "redirect:"+url;
            }


        //2获取扫描人信息，添加数据
        @GetMapping("callback")
        public String callback(String code,String state){


            //向认证服务器发送请求换取access_token
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数：id，密钥和code值

            String accessTokenUrl=String.format(
                    baseAccessTokenUrl,
                    ConstantPropertiesUtil.WX_OPEN_APP_ID,
                    ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                    code
            );
            String accessTokenInfo="";
            try {
                accessTokenInfo= HttpClientUtils.get(accessTokenUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson=new Gson();
            HashMap mapAccess_token=gson.fromJson(accessTokenInfo, HashMap.class);

            String access_token=(String)mapAccess_token.get("access_token");
            String openid=(String)mapAccess_token.get("openid");



            //吧扫描人信息添加到数据库里面
            //判断数据库表里面是否存在相同微信信息，根据openid判断
            try {
                UcenterMember member=memberService.getOpenIdMember(openid);
                if (member==null){ //member是空，表没有相同威胁你数据，进行添加


                    String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                            "?access_token=%s" +
                            "&openid=%s";
                    String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
                    String resultUserInfo = null;
                    try {
                        resultUserInfo = HttpClientUtils.get(userInfoUrl);
                        System.out.println("resultUserInfo==========" + resultUserInfo);
                    } catch (Exception e) {
                        throw new GuliException(20001, "获取用户信息失败");
                    }
                    HashMap userInfoMap=gson.fromJson(resultUserInfo,HashMap.class);
                    String nickname=(String)userInfoMap.get("nickname");
                    String headimgurl=(String)userInfoMap.get("headimgurl");

                    member=new UcenterMember();
                    member.setOpenid(openid);
                    member.setNickname(nickname);
                    member.setAvatar(headimgurl);
                    memberService.save(member);
                }
                String jwtToken=JwtUtils.getJwtToken(member.getId(),member.getNickname());
                return "redirect:http://localhost:3000?token="+jwtToken;
            }catch (Exception e){
                throw new GuliException(20001,"登陆失败");
            }
            //使用jwt根据member对象生成token字符串



        }
    }


