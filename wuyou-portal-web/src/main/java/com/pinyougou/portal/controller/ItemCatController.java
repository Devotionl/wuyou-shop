package com.pinyougou.portal.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.wuyou.content.service.ContentService;
import com.wuyou.content.service.GoodsService;
import com.wuyou.content.service.ItemCatService;
import com.wuyou.content.service.ItemService;
import com.wuyou.pojo.TbItem;
import com.wuyou.pojo.TbItemCat;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference(timeout = 50000)
    private ItemCatService itemCatService;

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
    public List<TbItemCat> findAll() {
        return itemCatService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return itemCatService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param itemCat
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbItemCat itemCat) {
        try {
            itemCatService.add(itemCat);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param itemCat
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbItemCat itemCat) {
        try {
            itemCatService.update(itemCat);
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
    public TbItemCat findOne(Long id) {
        return itemCatService.findOne(id);
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
            itemCatService.delete(ids);
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
    public PageResult search(@RequestBody TbItemCat itemCat, int page, int rows) {
        return itemCatService.findPage(itemCat, page, rows);
    }

    /**
     * 查询一级目录
     *
     * @return
     */
    @RequestMapping("findItemCat")
    public List<TbItemCat> findItemCat(Long parent_id) {
        return itemCatService.findItemCat(parent_id);
    }



    /**
     * 返回首页（三级列表）
     *
     * @return
     */
//	@RequestMapping("/itemList")
//	public Map<String, List<TbItemCat>> itemList(){
//		System.out.println("请求到了");
//		Map<String,List<TbItemCat>> map = new HashMap<String,List<TbItemCat>>();
//		if(redisTemplate.boundHashOps("Map").get("Map")==null){
//			System.out.println("mysql获取数据");
//			map.put("zero",itemCatService.findItemCat("zero"));
//			for (TbItemCat tbItemCat:map.get("zero")){
//				map.put(tbItemCat.getId(),itemCatService.findItemCat(tbItemCat.getId()));
//				for (TbItemCat tbItemCat1:map.get(tbItemCat.getId())){
//					map.put(tbItemCat1.getId(),itemCatService.findItemCat(tbItemCat1.getId()));
//				}
//			}
//			redisTemplate.boundHashOps("Map").put("Map",map);
//		}else{
//			System.out.println("redis获取数据");
//			map = (HashMap<String, List<TbItemCat>>)redisTemplate.boundHashOps("Map").get("Map");
//		}
//		return map;
//	}
    @Reference
    private ContentService contentService;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/itemList")
    public ModelAndView itemList() {

        ModelAndView modelAndView = new ModelAndView();

        Map<Long, List<TbItemCat>> map = new HashMap<Long, List<TbItemCat>>();
        if(redisTemplate.boundHashOps("Map").get("Map")==null){
            map.put(0L,itemCatService.findItemCat(0L));
            for (TbItemCat tbItemCat:map.get(0L)){
                map.put(tbItemCat.getId(),itemCatService.findItemCat(tbItemCat.getId()));
                for (TbItemCat tbItemCat1:map.get(tbItemCat.getId())){
                    map.put(tbItemCat1.getId(),itemCatService.findItemCat(tbItemCat1.getId()));
                }
            }
            redisTemplate.boundHashOps("Map").put("Map",map);
        }else{
            map = (HashMap<Long,List<TbItemCat>>)redisTemplate.boundHashOps("Map").get("Map");
        }

//		if(redisTemplate.boundHashOps("Map").get("Map")==null){
//			map.put(0L,itemCatService.findItemCat(0L));
//			for (TbItemCat tbItemCat:map.get(0L)){
//				map.put(tbItemCat.getId(),itemCatService.findItemCat(tbItemCat.getId()));
//				for (TbItemCat tbItemCat1:map.get(tbItemCat.getId())){
//					map.put(tbItemCat1.getId(),itemCatService.findItemCat(tbItemCat1.getId()));
//				}
//			}
//			redisTemplate.boundHashOps("Map").put("Map",map);
//			System.out.println("mysql读取数据");
//		}else{
//			map = (HashMap<Long,List<TbItemCat>>)redisTemplate.boundHashOps("Map").get("Map");
//			System.out.println("redis读取数据");
//		}
        modelAndView.addObject("contents",contentService.findByCategoryId(1L));
        modelAndView.addObject("Map", map);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * @return
     */
    @RequestMapping("/item")
    public ModelAndView item(Long itemid, Long Categoryid) {
        System.out.println("/localhost:9103");
        ModelAndView modelAndView = new ModelAndView();
        TbItemCat categoryid3 = itemCatService.findOne(Categoryid);
        modelAndView.addObject("categoryid3", categoryid3);
        TbItemCat categoryid2 = itemCatService.findOne(categoryid3.getParentId());
        modelAndView.addObject("categoryid2", categoryid2);
        TbItemCat categoryid1 = itemCatService.findOne(categoryid2.getParentId());
        modelAndView.addObject("categoryid1", categoryid1);
        TbItem tbItem = itemService.findOne(itemid);
        modelAndView.addObject("item", tbItem);
//		modelAndView.addObject("itemimage",itemImageService.findByItemid(itemid));
        modelAndView.setViewName("item");
        return modelAndView;
    }
}


