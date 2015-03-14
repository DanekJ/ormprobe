package com.danekja.edu.ormprobe.mappers;

import com.danekja.edu.ormprobe.domain.Ownership;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by witz on 7.3.15.
 */
public interface OwnershipMapper {
    public List<Ownership> getAllOwnerships();

    public Ownership getOwnershipById(@Param("id")Integer id);

    public void insertOwnership(Ownership o);

    public void deleteOwnershipById(@Param("id")Integer id);
}
