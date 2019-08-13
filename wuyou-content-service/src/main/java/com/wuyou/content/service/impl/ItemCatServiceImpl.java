package com.wuyou.content.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.dubbo.config.annotation.Service;
import com.wuyou.content.service.ItemCatService;
import com.wuyou.mapper.TbItemCatMapper;

import com.wuyou.pojo.SanjiWen;
import com.wuyou.pojo.TbItemCat;
import com.wuyou.pojo.TbItemCatExample;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbItemCat> findAll() {
        return itemCatMapper.selectByExample(null);
    }

    /**
     * 按条件查询
     */
    @Override
    public List<TbItemCat> findByCondition(TbItemCatExample example) {
        return itemCatMapper.selectByExample(example);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbItemCat itemCat) {
        itemCatMapper.insert(itemCat);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbItemCat itemCat) {
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    /**
     * 根据praent_id挑选ItemCat
     */
    @Override
    public List<TbItemCat> findItemCat(Long parent_id) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parent_id);
        return itemCatMapper.selectByExample(tbItemCatExample);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbItemCat findOne(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        if (itemCat != null) {
            if (itemCat.getName() != null && itemCat.getName().length() > 0) {
                criteria.andNameLike("%" + itemCat.getName() + "%");
            }

        }

        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
//	@Override
//	public Map<Long,List<TbItemCat>> SanJi(){
//		Map<Long,List<TbItemCat>> map = new HashMap<Long,List<TbItemCat>>();
//		if(redisTemplate.boundHashOps("Map").get("Map")==null){
//			TbItemCatExample tbItemCatExample = new TbItemCatExample();
//			TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
//			criteria.andParentIdEqualTo(0L);
//			map.put(0L,itemCatMapper.selectByExample(tbItemCatExample));
//			for (TbItemCat tbItemCat:map.get(0L)){
//				System.out.println(tbItemCat.getId());
//				TbItemCatExample tbItemCatExample1 = new TbItemCatExample();
//				TbItemCatExample.Criteria criteria1 = tbItemCatExample.createCriteria();
//				criteria1.andParentIdEqualTo(tbItemCat.getId());
//				map.put(tbItemCat.getId(),itemCatMapper.selectByExample(tbItemCatExample1));
//				for (TbItemCat tbItemCat1:map.get(tbItemCat.getId())){
//					System.out.println(tbItemCat1.getId());
//					TbItemCatExample tbItemCatExample2 = new TbItemCatExample();
//					TbItemCatExample.Criteria criteria2= tbItemCatExample.createCriteria();
//					criteria2.andParentIdEqualTo(tbItemCat1.getId());
//					map.put(tbItemCat1.getId(),itemCatMapper.selectByExample(tbItemCatExample2));
//				}
//			}
//			redisTemplate.boundHashOps("Map").put("Map",map);
//			System.out.println("mysql读取数据");
//		}else{
//			map = (HashMap<Long,List<TbItemCat>>)redisTemplate.boundHashOps("Map").get("Map");
//			System.out.println("redis读取数据");
//		}
//
//		return  map;
//	}

}
