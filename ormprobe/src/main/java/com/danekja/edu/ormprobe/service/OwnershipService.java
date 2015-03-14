package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Ownership;
import com.danekja.edu.ormprobe.mappers.OwnershipMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by witz on 14.3.15.
 */
public class OwnershipService implements OwnershipMapper {
    @Override
    public List<Ownership> getAllOwnerships() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            OwnershipMapper ownershipMapper = sqlSession.getMapper(OwnershipMapper.class);
            List<Ownership> result = ownershipMapper.getAllOwnerships();
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void insertOwnership(Ownership o) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            OwnershipMapper ownershipMapper = sqlSession.getMapper(OwnershipMapper.class);
            ownershipMapper.insertOwnership(o);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Ownership getOwnershipById(Integer id){
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            OwnershipMapper ownershipMapper = sqlSession.getMapper(OwnershipMapper.class);
            Ownership result = ownershipMapper.getOwnershipById(id);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deleteOwnershipById(Integer id)
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            OwnershipMapper ownershipMapper = sqlSession.getMapper(OwnershipMapper.class);
            ownershipMapper.deleteOwnershipById(id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
