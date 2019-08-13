package com.wuyou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuyou.pojo.TbBrand;
import com.wuyou.sellergoods.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("findAll")
    public List<TbBrand> findAll() {
        System.out.println("时间");
        return brandService.findAll();
    }
}
