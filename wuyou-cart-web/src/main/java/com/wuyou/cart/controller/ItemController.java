package com.wuyou.cart.controller;


import java.util.HashMap;
import java.util.List;


import org.springframework.security.core.context.SecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import com.wuyou.cart.service.CartService;
import com.wuyou.pojo.TbItem;
import entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/item")
public class ItemController {
    @Reference
    private CartService cartService;


    @RequestMapping(value = "/additem")
    public Result additem(Long itemid, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        String username = "xiaoxin";
        System.out.println(username);
        return cartService.additem(username, itemid);
    }

    @RequestMapping("/cart")
    public ModelAndView cart() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("items", cartService.cart(name));
        System.out.println(name);
        return modelAndView;
    }

    @RequestMapping("redis")
    public Result redis(Long id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartService.redis(id, name);
    }

    @RequestMapping("findAll")
    public List<TbItem> findAll() {
        return cartService.findAll();
    }

    @RequestMapping("addorder")
    public Result addrder(Long id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(id + name);
        cartService.modifyStatus(id, name);
        return new Result(true, "时间");
    }

}
