package com.wuyou.content.service;

import com.wuyou.pojo.TbItem;
import entity.PageResult;

import java.util.List;

public interface ItemService {
    List<TbItem> findAll();

    PageResult findPage(int pageNum, int pageSize);

    void add(TbItem item);

    void delete(Long id);

    void update(TbItem item);

    TbItem findOne(Long id);

    void delete(Long[] ids);

    PageResult findPage(TbItem item, int pageNum, int pageSize);

    List<TbItem> findbyItem(TbItem tbItem);
}
