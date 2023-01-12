package com.atguigu.educms.service;

import com.atguigu.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
public interface CrmBannerService extends IService<CrmBanner> {
    //查询所有banner
    public List<CrmBanner> selectAllBanner();
}
