package com.wuyou.cart.controller;

import java.util.List;

import com.wuyou.cart.service.CartService;
import com.wuyou.pojo.TbAddress;
import com.wuyou.pojo.group.RecordNoUtils;
import com.wuyou.service.AddressService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;


import entity.PageResult;
import entity.Result;
import org.springframework.web.servlet.ModelAndView;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;


    @Reference
    private CartService cartService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbAddress> findAll() {
        return addressService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return addressService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param address
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbAddress address) {
        try {
            addressService.add(address);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param address
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbAddress address) {
        try {
            addressService.update(address);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbAddress findOne(Long id) {
        return addressService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            addressService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbAddress address, int page, int rows) {
        return addressService.findPage(address, page, rows);
    }


    @RequestMapping("/Settlement")
    public ModelAndView Settlement() {
        System.out.println("Settlement");
        ModelAndView modelAndView = new ModelAndView("getOrderInfo");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //添加雪花算法生成订单号
        RecordNoUtils recordNoUtils = new RecordNoUtils();
        Long order_id = recordNoUtils.get();
        modelAndView.addObject("order_id", order_id);
        //添加地址信息
        modelAndView.addObject("addresslist", addressService.findList(name));
        //添加订单项
        modelAndView.addObject("OrderItem", cartService.findState(name, order_id));
        //添加总价格
        modelAndView.addObject("totalPrice", cartService.addTotalPrice(name));
        return modelAndView;
    }


}
