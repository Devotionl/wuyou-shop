package com.wuyou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.wuyou.mapper.TbBrandMapper;
import com.wuyou.pojo.TbBrand;
import com.wuyou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        System.out.println("你好");
        return brandMapper.selectByExample(null);
    }
}
