package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.mappers.GroupMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by witz on 9.3.15.
 */
public class GroupService implements GroupMapper {
    @Override
    public void insertGroup(Group group) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
            groupMapper.insertGroup(group);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Group getGroupById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
            Group result = groupMapper.getGroupById(id);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Group> getAllGroups() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
            List<Group> result = groupMapper.getAllGroups();
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(Group group) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
            groupMapper.update(group);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
            groupMapper.delete(id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
