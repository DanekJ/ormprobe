package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Ownership;
import com.danekja.edu.ormprobe.mappers.OwnershipMapper;

/**
 * Created by witz on 14.3.15.
 */
public class OwnershipService extends SuperService<Ownership, OwnershipMapper> implements OwnershipMapper {
    public OwnershipService() {
        super(OwnershipMapper.class, Ownership.class);
    }
}
