import com.wuyou.mapper.TbItemMapper;
import com.wuyou.pojo.TbItem;
import com.wuyou.search.repositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class Test11 {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TbItemMapper goodsMapper;

    @Test
    public void test() {
        System.out.println("hello");
    }

    @org.junit.Test
    public void createIndex() throws Exception {
        //创建索引，并配置映射关系
        elasticsearchTemplate.createIndex(TbItem.class);
        //配置映射关系
        //elasticsearchTemplate.putMapping(Goods.class);
    }

    @org.junit.Test
    public void addDocument() throws Exception {
        //创建一个list集合
        List<TbItem> list = goodsMapper.selectByExample(null);
        //把文档写入索引库
        for (TbItem goods : list) {
            itemRepository.save(goods);
        }
    }

    @org.junit.Test
    public void findByName() throws Exception {
        List<TbItem> list = itemRepository.findByTitle("三星");
        for (TbItem tbItem : list) {
            System.out.println(tbItem);
        }
    }
}
