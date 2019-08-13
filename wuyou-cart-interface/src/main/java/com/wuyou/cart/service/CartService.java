package com.wuyou.cart.service;

import com.wuyou.pojo.TbItem;
import com.wuyou.pojo.TbOrderItem;
import org.springframework.web.servlet.ModelAndView;
import entity.Result;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Result additem(String username, Long itemid);

    List<TbItem> cart(String name);

    Result redis(Long id, String name);

    List<TbItem> findAll();

    void modifyStatus(Long id, String name);


    Long addTotalPrice(String name);


    List<TbOrderItem> findState(String name, Long order_id);
}
