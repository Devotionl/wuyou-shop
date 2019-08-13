package com.wuyou.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.wuyou.pojo.TbItem;
import com.wuyou.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/itemSearch")
public class ItemSearchController {

    @Reference
    private ItemSearchService itemSearchService;

    @RequestMapping("/search")
    public Map search(@RequestBody Map searchMap) {
        System.out.println("search");
        return itemSearchService.search(searchMap);
    }

    @RequestMapping("/search1")
    public String search1() {
        return "shijian nihao";
    }

    @RequestMapping("/searchByName")
    public List<TbItem> searchByName(String name) {
        System.out.println("name=" + name);
        List<TbItem> list = itemSearchService.searchByName(name);
        System.out.println("list=" + list);
        return list;
    }

    @RequestMapping("/findAll")
    public List<TbItem> findAll() {
        return itemSearchService.findAll();
    }

    @RequestMapping("/search2")
    public List<TbItem> search2(String key) {
        return itemSearchService.search2(key);
    }

}
