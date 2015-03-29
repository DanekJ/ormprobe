package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.mappers.ItemMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.Set;

/**
 * Created by witz on 13.3.15.
 */
public class ItemService extends SuperService<Item, ItemMapper> implements ItemMapper {

    public ItemService() {
        super(ItemMapper.class, Item.class);
    }

    @Override
    public Set<Item> listBigGroupsItems(@Param("id") Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            final ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            Set<Item> result = mapper.listBigGroupsItems(id);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }
}
