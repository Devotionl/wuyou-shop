package com.wuyou.search.service;

import com.wuyou.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {


    public Map search(Map searchMap);

    public List<TbItem> searchByName(String name);

    public List<TbItem> findAll();

    public List<TbItem> search2(String key);

}
