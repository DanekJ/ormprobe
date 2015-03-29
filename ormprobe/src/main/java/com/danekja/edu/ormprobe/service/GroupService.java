package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.mappers.GroupMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.Set;

/**
 * Created by witz on 9.3.15.
 */
public class GroupService extends SuperService<Group, GroupMapper> implements GroupMapper {
    public GroupService() {
        super(GroupMapper.class, Group.class);
    }

    @Override
    public Set<Group> listOwnershipCandidates(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            final GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
            Set<Group> result = mapper.listOwnershipCandidates(id);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Set<BigGroup> listItemsBigGroups(@Param("id") Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            final GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
            Set<BigGroup> result = mapper.listItemsBigGroups(id);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }
}
