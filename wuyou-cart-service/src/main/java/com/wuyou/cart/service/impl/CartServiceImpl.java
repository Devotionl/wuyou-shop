package com.wuyou.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wuyou.cart.service.CartService;

import com.wuyou.mapper.TbItemMapper;
import com.wuyou.pojo.TbItem;
import com.wuyou.pojo.TbOrder;
import com.wuyou.pojo.TbOrderItem;
import com.wuyou.pojo.group.RecordNoUtils;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result additem(String username, Long itemid) {
        Map<String, List<TbItem>> listMap = (HashMap<String, List<TbItem>>) redisTemplate.boundHashOps("cart").get("cart");
        if (listMap == null) {
            listMap = new HashMap<String, List<TbItem>>();
        }
        List list = listMap.get(username);
        if (list == null) {
            list = new ArrayList();
        }
        for (Object object : list) {
            TbItem tbItem = (TbItem) object;
            if (tbItem.getId().longValue() == itemid.longValue()) {
                return new Result(true, "添加重复");
            }
        }
        list.add(tbItemMapper.selectByPrimaryKey(itemid));
        listMap.put(username, list);
        redisTemplate.boundHashOps("cart").put("cart", listMap);
        return new Result(true, "加入购物车成功");
    }

    @Override
    public List<TbItem> cart(String name) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, List<TbItem>> listMap = (Map<String, List<TbItem>>) redisTemplate.boundHashOps("cart").get("cart");
//        for (TbItem tbItem:listMap.get(name)){
//            System.out.println(tbItem.getStatus());
//        }
        return listMap.get(name);
    }

    @Override
    public Result redis(Long id, String name) {
        Map<String, List<TbItem>> listMap = (Map<String, List<TbItem>>) redisTemplate.boundHashOps("cart").get("cart");
        List<TbItem> list = listMap.get(name);
        for (TbItem tbItem : list) {
            if (id.equals(tbItem.getId())) {
                list.remove(tbItem);
                listMap.put(name, list);
                redisTemplate.boundHashOps("cart").put("cart", listMap);
                return new Result(true, "删除成功");
            }
        }
        return new Result(false, "没找到");
    }

    @Override
    public List<TbItem> findAll() {
        return tbItemMapper.selectByExample(null);
    }

    @Override
    public void modifyStatus(Long id, String name) {
        Map<String, List<TbItem>> listMap = (Map<String, List<TbItem>>) redisTemplate.boundHashOps("cart").get("cart");
        List<TbItem> list = listMap.get(name);
        for (TbItem tbItem : list) {
            if (tbItem.getId().longValue() == id.longValue()) {
                if (tbItem.getStatus().equals("1"))
                    tbItem.setStatus("2");
                else if (tbItem.getStatus().equals("2"))
                    tbItem.setStatus("1");
            }
        }
        listMap.put(name, list);
        redisTemplate.boundHashOps("cart").put("cart", listMap);

    }

    @Override
    public Long addTotalPrice(String name) {
        Long num1 = 0L;
        Map<String, List<TbItem>> listMap = (Map<String, List<TbItem>>) redisTemplate.boundHashOps("cart").get("cart");
        List<TbItem> list = listMap.get(name);
        List<TbItem> list2 = new ArrayList<>();
        for (TbItem tbItem : list) {
            if (tbItem.getStatus().equals("2")) {
                num1 = num1 + tbItem.getPrice().longValue();
            }
        }
        System.out.println("我是总价格:" + num1);
        return num1;
    }


    @Override
    public List<TbOrderItem> findState(String name, Long order_id) {
        Map<String, List<TbItem>> listMap = (Map<String, List<TbItem>>) redisTemplate.boundHashOps("cart").get("cart");
        System.out.println(name + order_id);
        List<TbItem> list = listMap.get(name);
        RecordNoUtils recordNoUtils = new RecordNoUtils();
        List<TbOrderItem> list1 = new ArrayList<>();
        for (TbItem tbItem : list) {
            if (tbItem.getStatus().equals("2")) {
//                System.out.println(tbItem.getTitle());
                TbOrderItem tbOrderItem = new TbOrderItem();
                tbOrderItem.setOrderId(order_id);
                tbOrderItem.setTitle(tbItem.getTitle());
                tbOrderItem.setItemId(tbItem.getId());
                tbOrderItem.setId(recordNoUtils.get());
                tbOrderItem.setPrice(tbItem.getPrice());
                tbOrderItem.setNum(1);
                tbOrderItem.setPicPath(tbItem.getImage());
                list1.add(tbOrderItem);
            }
        }
        Map<Long, List<TbOrderItem>> listMap1 = new HashMap<>();
        listMap1.put(order_id, list1);
        redisTemplate.boundHashOps("cart").put("order", listMap1);
//        for(TbOrderItem tbOrderItem: list1){
//            System.out.println(tbOrderItem);
//        }
//        System.out.println("你好订单项加载完毕");
        return list1;
    }

}