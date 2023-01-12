package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //生成有参数构造方法
@NoArgsConstructor //生成无参数构造
public class GuliException extends RuntimeException {
    private Integer code;

    private String msg;

}
