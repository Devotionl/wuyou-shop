package com.wuyou.search.repositories;

import com.wuyou.pojo.TbItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<TbItem, Integer> {
    List<TbItem> findByTitle(String title);

    List<TbItem> findByTitleLike(String title);

    List<TbItem> findByTitleLikeOrCategoryLikeOrBrandLikeOrSellerLike(
            String title, String category, String brand, String seller
    );
}
