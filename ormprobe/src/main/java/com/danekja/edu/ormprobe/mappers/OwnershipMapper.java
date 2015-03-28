package com.danekja.edu.ormprobe.mappers;

import com.danekja.edu.ormprobe.domain.Ownership;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by witz on 7.3.15.
 */
public interface OwnershipMapper {
    public List<Ownership> getAll();

    public Ownership getById(@Param("id") Integer id);

    public void insert(Ownership o);

    public void delete(@Param("id") Integer id);
}
