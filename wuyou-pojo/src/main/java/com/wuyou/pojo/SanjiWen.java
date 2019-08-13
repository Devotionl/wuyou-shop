package com.wuyou.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SanjiWen implements Serializable, Map<Long, List<TbItemCat>> {
    public Map<Long, List<TbItemCat>> map;

    public Map<Long, List<TbItemCat>> getMap() {
        return this.map;
    }


    public void setMap(Map<Long, List<TbItemCat>> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public List<TbItemCat> get(Object key) {
        return null;
    }

    @Override
    public List<TbItemCat> put(Long key, List<TbItemCat> value) {
        return null;
    }

    @Override
    public List<TbItemCat> remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Long, ? extends List<TbItemCat>> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Long> keySet() {
        return null;
    }

    @Override
    public Collection<List<TbItemCat>> values() {
        return null;
    }

    @Override
    public Set<Entry<Long, List<TbItemCat>>> entrySet() {
        return null;
    }
}
