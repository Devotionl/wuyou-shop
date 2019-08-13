package com.pinyougou.portal.controller;

import java.util.List;


import com.alibaba.dubbo.config.annotation.Reference;
import com.wuyou.content.service.GoodsService;
import com.wuyou.content.service.ItemService;
import com.wuyou.pojo.TbGoods;
import com.wuyou.pojo.TbItem;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @Reference
    private ItemService itemService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbGoods> findAll() {
        System.out.println("shijian");
        return goodsService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return goodsService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param goods
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbGoods goods) {
        try {
            goodsService.add(goods);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param goods
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbGoods goods) {
        try {
            goodsService.update(goods);
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
    public TbGoods findOne(Long id) {
        return goodsService.findOne(id);
    }


    //	@RequestMapping("/upload")
//	public Result upload(MultipartFile file){
//		System.out.println(file);
//		System
//
//	}
    @RequestMapping("/findSellerId")
    public List<TbGoods> findSellerId(String brandid) {
        return goodsService.findSellerId(brandid);
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
            goodsService.delete(ids);
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
    public PageResult search(@RequestBody TbGoods goods, int page, int rows) {
        return goodsService.findPage(goods, page, rows);
    }

    @ResponseBody
    @RequestMapping("/findBycategory1_id")
    public ModelAndView findBycategory1_id(Long id) {
//		return  goodsService.findBycategory1_id(id);
        ModelAndView modelAndView = new ModelAndView();
        List<TbGoods> list = goodsService.findBycategory1_id(id);
        modelAndView.addObject("ListTbGood", list);
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/findBycategory2_id")
    public List<TbGoods> findBycategory2_id(Long id) {
        return goodsService.findBycategory2_id(id);
    }

    @ResponseBody
    @RequestMapping("/findBycategory3_id")
    public ModelAndView findBycategory3_id(Long id, int Pagenum) {
        ModelAndView modelAndView = new ModelAndView("search");
        System.out.println(id + "   " + Pagenum);
        TbItem tbItem = new TbItem();
        tbItem.setCategoryid(id);
        modelAndView.addObject("PageResult", itemService.findPage(tbItem, Pagenum, 10));

        return modelAndView;
    }

    @RequestMapping("/item")
    public ModelAndView item() {
        return new ModelAndView();
    }


    @RequestMapping("/Sellwell")
    public PageResult Sellwell(String sellerid) {
        System.out.println("热销：" + sellerid);
        int id = (int) (Math.random() * 50);
        System.out.println(id);
        TbItem tbItem = new TbItem();
        tbItem.setSellerId(sellerid);
        return itemService.findPage(tbItem, id, 10);
    }

    @RequestMapping("Recommend")
    public PageResult Recommend(String sellerid) {
        System.out.println("推荐：" + sellerid);
        int id = (int) (Math.random() * 50);
        System.out.println(id);
        TbItem tbItem = new TbItem();
        tbItem.setSellerId(sellerid);
        return itemService.findPage(tbItem, id, 10);
    }

    @RequestMapping("Home_Page_Hot_Selling")
    public PageResult Home_Page_Hot_Selling() {
        int id = (int) (Math.random() * 50);
        TbItem tbItem = new TbItem();
        tbItem.setSellerId("baidu");
        return itemService.findPage(tbItem, id, 10);
    }

}
