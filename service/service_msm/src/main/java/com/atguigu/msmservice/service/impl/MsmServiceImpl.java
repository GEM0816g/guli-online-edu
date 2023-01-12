package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import com.aliyun.teautil.models.RuntimeOptions;
import com.atguigu.commonutils.R;
import com.atguigu.msmservice.Sample;
import com.atguigu.msmservice.service.MsmService;
import springfox.documentation.spring.web.json.Json;

import java.util.Map;

public class MsmServiceImpl implements MsmService{

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


    @Override
    public void sendSort(String phone, Map<String,String> parm) throws Exception {

        // 初始化 Client，采用 AK&SK 鉴权访问的方式，此方式可能会存在泄漏风险，建议使用 STS 方式。鉴权访问方式请参考：https://help.aliyun.com/document_detail/378657.html
        // 获取 AK 链接：https://usercenter.console.aliyun.com
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("LTAI5tDdwsMwwnJjfHXekW4L", "1fFKCID67SnuQpex72wpqh8enVIAaM");
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers("18576052427")
                .setTemplateParam(JSONObject.toJSONString(parm));
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }





    public void sendSort2(String phone, Map<String,String> parm) {


        com.aliyun.dysmsapi20170525.Client client = null;
        try {
            client = Sample.createClient("LTAI5tDdwsMwwnJjfHXekW4L", "1fFKCID67SnuQpex72wpqh8enVIAaM");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phone)
                .setTemplateParam(JSONObject.toJSONString(parm));
        RuntimeOptions runtime = new RuntimeOptions();
        SendSmsResponse resp = null;
        try {
            resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
