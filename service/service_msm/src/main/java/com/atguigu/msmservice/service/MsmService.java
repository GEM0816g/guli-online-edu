package com.atguigu.msmservice.service;

import com.atguigu.commonutils.R;

import java.util.Map;

public interface MsmService {
    public void sendSort(String phone, Map<String,String> parm) throws Exception;
}
