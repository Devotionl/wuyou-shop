package com.wuyou.service;

import com.wuyou.pojo.TbAddress;
import entity.PageResult;

import java.util.List;

public interface AddressService {
    List<TbAddress> findAll();

    PageResult findPage(int pageNum, int pageSize);

    void delete(Long id);

    void add(TbAddress address);

    void update(TbAddress address);

    TbAddress findOne(Long id);

    void delete(Long[] ids);

    List<TbAddress> findList(String name);

    PageResult findPage(TbAddress address, int pageNum, int pageSize);
}
