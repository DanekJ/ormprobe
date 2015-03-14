package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.mappers.ItemMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by witz on 13.3.15.
 */
public class ItemService implements ItemMapper {

    @Override
    public List<Item> getAllItems() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ItemMapper groupMapper = sqlSession.getMapper(ItemMapper.class);
            List<Item> result = groupMapper.getAllItems();
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Item getItemById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ItemMapper groupMapper = sqlSession.getMapper(ItemMapper.class);
            Item result = groupMapper.getItemById(id);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
            itemMapper.delete(id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void insertItem(Item item) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ItemMapper groupMapper = sqlSession.getMapper(ItemMapper.class);
            groupMapper.insertItem(item);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateItem(Item item) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ItemMapper groupMapper = sqlSession.getMapper(ItemMapper.class);
            groupMapper.updateItem(item);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
