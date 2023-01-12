package com.atguigu.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService{

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //Endpoint以杭州为例，其他Region请安实际情况填写
        //工具类值获取
        String endpoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        try {
            //创建oss实例
            OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);


            //上传文件流
            InputStream inputStream= null;

            //获取上传文件输入流
            inputStream = file.getInputStream();

            //获取文件名称
            String Filename = file.getOriginalFilename();

            //在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            Filename=uuid+Filename;

            //把文件按照日期进行分类

            String datePath=new DateTime().toString("yyyy/MM/dd");

            Filename=datePath+"/"+Filename;


            //调用oss方法是西安上传
            //第一个参数Bucket名称
            //第二个参数 上传到oss文件路径名称和文件名称，
            ossClient.putObject(bucketName,Filename,inputStream);

            //关闭OSSClient
            ossClient.shutdown();

            //吧上传之后文件路径返回
            String url="https://"+bucketName+"."+endpoint+"/"+Filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }






    }
}
