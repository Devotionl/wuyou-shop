package com.wuyou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wuyou.pojo.TbItem;
import com.wuyou.search.repositories.ItemRepository;
import com.wuyou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Map search(Map searchMap) {
        Map map = new HashMap();
        String word = (String) searchMap.get("keywords");
        List<TbItem> list = itemRepository.findByTitleLikeOrCategoryLikeOrBrandLikeOrSellerLike(word, word, word, word);
        ;
        map.put("rows", list);
        return map;
    }

    @Override
    public List<TbItem> searchByName(String name) {
        List<TbItem> list = itemRepository.findByTitle(name);
        return list;
    }

    @Override
    public List<TbItem> findAll() {
        Iterable<TbItem> iterable = itemRepository.findAll();
        List<TbItem> list = new ArrayList<>();
        for (TbItem goods : iterable) {
            list.add(goods);
        }
        return list;
    }

    @Override
    public List<TbItem> search2(String key) {
        return itemRepository.findByTitleLikeOrCategoryLikeOrBrandLikeOrSellerLike(key, key, key, key);
    }


}
