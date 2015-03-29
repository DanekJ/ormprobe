package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Ownership;
import com.danekja.edu.ormprobe.mappers.OwnershipMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by witz on 14.3.15.
 */
public class OwnershipService extends SuperService<Ownership, OwnershipMapper> implements OwnershipMapper {
    public OwnershipService() {
        super(OwnershipMapper.class, Ownership.class);
    }

    @Override
    public boolean isConnectedToBigGroup(Integer bigGroupId, Integer itemId) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            final OwnershipMapper mapper = sqlSession.getMapper(OwnershipMapper.class);
            boolean result = mapper.isConnectedToBigGroup(bigGroupId, itemId);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }
}
