package com.atguigu.educms.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@RestController
@RequestMapping("/educms/banneradmin")

public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;
    //1.分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> bannerPage=new Page<>(page,limit);
        bannerService.page(bannerPage,null);

        return R.ok().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }


    //2 添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
    return R.ok();
    }
    @ApiOperation(value="获取banner")
    @GetMapping("getBanner/{id}")
    public R getBanner(@PathVariable String id){
        CrmBanner banner=bannerService.getById(id);
        return R.ok().data("item",banner);
    }
    //3 修改banner
    @ApiOperation(value="修改Banner")
    @PutMapping("updateBanner")
    public R updateBannerById(@RequestBody CrmBanner crmBanner){
        bannerService.updateById(crmBanner);
        return R.ok();
    }

    //4 删除banner
    @ApiOperation(value="删除Banner")
    @PostMapping("removeBanner{id}")
    public R deleteBanner(@PathVariable String id){
        bannerService.removeById(id);
        return R.ok();
    }
}

