package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.dao.TestDao;
import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;

import java.util.Set;

/**
 * Created by witz on 29.3.15.
 */
public class TestDaoService implements TestDao {
    @Override
    public Set<Group> listOwnershipCandidates(long bigGroupId) {
        GroupService gs = new GroupService();
        return gs.listOwnershipCandidates((int)bigGroupId);
    }

    @Override
    public Set<BigGroup> listItemsBigGroups(long itemId) {
        GroupService gs = new GroupService();
        return gs.listItemsBigGroups((int)itemId);
    }

    @Override
    public boolean isConnectedToBigGroup(long bigGroupId, long itemId) {
        OwnershipService os = new OwnershipService();
        return os.isConnectedToBigGroup((int)bigGroupId, (int)itemId);
    }

    @Override
    public Set<Item> listBigGroupsItems(long bigGroupId) {
        ItemService is = new ItemService();
        return is.listBigGroupsItems((int)bigGroupId);
    }
}
