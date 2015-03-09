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
    public void insertGroup(Group item) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
            groupMapper.insertGroup(item);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Group getGroupById(Integer id) {
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public void update(Group item) {

    }

    @Override
    public void delete(Integer id) {

    }
}
