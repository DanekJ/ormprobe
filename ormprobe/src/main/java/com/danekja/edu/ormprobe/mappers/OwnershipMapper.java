package com.danekja.edu.ormprobe.mappers;

import com.danekja.edu.ormprobe.domain.Ownership;

import java.util.List;

/**
 * Created by witz on 7.3.15.
 */
public interface OwnershipMapper {
    public List<Ownership> getAllOwnerships();

    public void insertOwnership();
}
