package com.danekja.edu;

import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Ownership;
import com.danekja.edu.ormprobe.domain.OwnershipItem;
import com.danekja.edu.ormprobe.service.GroupService;
import com.danekja.edu.ormprobe.service.ItemService;
import com.danekja.edu.ormprobe.service.OwnershipService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Created by witz on 14.3.15.
 */
public class OwnershipTest extends TestCase {
    private OwnershipService os;

    public OwnershipTest(String testName)
    {
        super( testName );
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        os = new OwnershipService();
    }

    public static Test suite()
    {
        TestSuite ts = new TestSuite();
        ts.addTest(new OwnershipTest("testGetAll"));
        ts.addTest(new OwnershipTest("testGetById"));
        ts.addTest(new OwnershipTest("testInsert"));
        ts.addTest(new OwnershipTest("testDelete"));
        return ts;
    }

    public void testGetAll()
    {
        List<Ownership> ownerships = os.getAllOwnerships();
        assertNotNull(ownerships);
        assertFalse(ownerships.isEmpty());
    }

    public void testGetById()
    {
        Ownership o = os.getOwnershipById(1);
        assertNotNull(o);
        assertEquals(1l, o.id);
    }

    public void testInsert()
    {
        Ownership o = new OwnershipItem();
        List<Ownership> list = os.getAllOwnerships();
        o.setUpper(new GroupService().getGroupById(1));
        o.setLower(new ItemService().getItemById(1));
        os.insertOwnership(o);
        assertEquals(list.size() + 1, os.getAllOwnerships().size());
    }

    public void testDelete()
    {
        List<Ownership> list = os.getAllOwnerships();
        os.deleteOwnershipById(1);
        assertEquals(list.size() - 1, os.getAllOwnerships().size());
    }
}
