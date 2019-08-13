package com.wuyou.content.service;

import com.wuyou.pojo.TbGoods;
import entity.PageResult;

import java.util.List;

public interface GoodsService {
    List<TbGoods> findAll();

    PageResult findPage(int pageNum, int pageSize);

    void add(TbGoods goods);

    void update(TbGoods goods);

    TbGoods findOne(Long id);

    List<TbGoods> findshijian(String id);

    List<TbGoods> findBycategory1_id(Long id);

    List<TbGoods> findBycategory2_id(Long id);

    List<TbGoods> findBycategory3_id(Long id);

    void delete(Long[] ids);

    List<TbGoods> findSellerId(String id);

    PageResult findPage(TbGoods goods, int pageNum, int pageSize);
}
