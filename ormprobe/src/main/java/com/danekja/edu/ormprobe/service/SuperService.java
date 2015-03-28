package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.BaseObject;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by witz on 13.3.15.
 */
public class SuperService<T extends BaseObject, M> {
    private final Class<M> mapper;
    private final Class<T> objectClass;

    public SuperService(Class<M> mapperClass, Class<T> objClass){
        mapper = mapperClass;
        objectClass = objClass;
    }

    public List<T> getAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            MapperMethod mm = new MapperMethod(mapper, mapper.getMethod("getAll"), sqlSession.getConfiguration());
            List<T> result = (List<T>) mm.execute(sqlSession, null);
            sqlSession.commit();
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } finally {
            sqlSession.close();
        }
    }

    public T getById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            MapperMethod mm = new MapperMethod(mapper, mapper.getMethod("getById", Integer.class), sqlSession.getConfiguration());
            T result = (T) mm.execute(sqlSession, new Object[]{id});
            sqlSession.commit();
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } finally {
            sqlSession.close();
        }
    }

    public void delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            MapperMethod mm = new MapperMethod(mapper, mapper.getMethod("delete", Integer.class), sqlSession.getConfiguration());
            mm.execute(sqlSession, new Object[]{id});
            sqlSession.commit();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public void insert(T item) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            MapperMethod mm = new MapperMethod(mapper, mapper.getMethod("insert", objectClass), sqlSession.getConfiguration());
            mm.execute(sqlSession, new Object[]{item});
            sqlSession.commit();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public void update(T item) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            MapperMethod mm = new MapperMethod(mapper, mapper.getMethod("update", objectClass), sqlSession.getConfiguration());
            mm.execute(sqlSession, new Object[]{item});
            sqlSession.commit();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
