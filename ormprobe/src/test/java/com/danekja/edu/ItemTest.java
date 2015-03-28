package com.danekja.edu;

import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.service.ItemService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Created by witz on 13.3.15.
 */
public class ItemTest extends TestCase {
    private ItemService is;

    public ItemTest(String testName)
    {
        super( testName );
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        is = new ItemService();
    }

    public static Test suite()
    {
        TestSuite ts = new TestSuite();
        ts.addTest(new ItemTest("testSelectAll"));
        ts.addTest(new ItemTest("testSelectById"));
        ts.addTest(new ItemTest("testInsert"));
        ts.addTest(new ItemTest("testDelete"));
        return ts;
    }

    public void testSelectAll()
    {
        List<Item> list = is.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    public void testSelectById()
    {
        Item item = is.getById(1);
        assertNotNull(item);
        assertEquals("Bar", item.getName());
    }

    public void testInsert()
    {
        List<Item> items = is.getAll();
        Item item = new Item();
        item.setName("Item1");
        is.insert(item);
        assertTrue(items.size() + 1 == is.getAll().size());
    }

    public void testUpdate()
    {
        Item item = is.getById(1);
        item.setName("Renamed");
        item = is.getById(1);
        assertEquals("Renamed", item.getName());
    }

    public void testDelete()
    {
        is.delete(1);
        assertNull(is.getById(1));
    }
}
