package com.wuyou.content.service;

import com.wuyou.pojo.TbItemCat;
import com.wuyou.pojo.TbItemCatExample;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface ItemCatService {
    List<TbItemCat> findAll();

    List<TbItemCat> findByCondition(TbItemCatExample example);

    PageResult findPage(int pageNum, int pageSize);

    void add(TbItemCat itemCat);

    void update(TbItemCat itemCat);

    List<TbItemCat> findItemCat(Long parent_id);

    TbItemCat findOne(Long id);

    void delete(Long[] ids);

    PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize);


}
