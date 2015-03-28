package com.danekja.edu;

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
        List<Ownership> ownerships = os.getAll();
        assertNotNull(ownerships);
        assertFalse(ownerships.isEmpty());
    }

    public void testGetById()
    {
        Ownership o = os.getById(1);
        assertNotNull(o);
        assertEquals(1l, o.id);
    }

    public void testInsert()
    {
        Ownership o = new OwnershipItem();
        List<Ownership> list = os.getAll();
        o.setUpper(new GroupService().getById(1));
        o.setLower(new ItemService().getById(1));
        os.insert(o);
        assertEquals(list.size() + 1, os.getAll().size());
    }

    public void testDelete()
    {
        List<Ownership> list = os.getAll();
        os.delete(1);
        assertEquals(list.size() - 1, os.getAll().size());
    }
}
