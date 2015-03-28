package com.danekja.edu;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.service.GroupService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class GroupTest
    extends TestCase
{

    private GroupService gs;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GroupTest(String testName)
    {
        super( testName );
    }

    @Override
    public void setUp()
    {
        gs = new GroupService();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite ts = new TestSuite();
        ts.addTest(new GroupTest("testSelectAll"));
        ts.addTest(new GroupTest("testGroupInsertion"));
        ts.addTest(new GroupTest("testSelectById"));
        ts.addTest(new GroupTest("testUpdate"));
        ts.addTest(new GroupTest("testDeleteById"));
        return ts;
    }

    public void testSelectAll()
    {
        List<Group> groups = gs.getAll();
        assertNotNull(groups);
        assertTrue(groups.size() > 0);
    }

    /**
     * Rigourous Test :-)
     */
    public void testGroupInsertion()
    {
        List<Group> groups = gs.getAll();
        int size = groups.size();

        Group g = new BigGroup();
        g.setName("Fuu");
        gs.insert(g);

        groups = gs.getAll();
        assertTrue(groups.size() - size == 1);
    }

    public void testSelectById()
    {
        Group g = gs.getById(1);
        assertNotNull(g);
        assertTrue(g.getName().equals("Fuu"));
    }

    public void testUpdate()
    {
        Group g = gs.getById(1);
        g.setName("new");
        gs.update(g);
        g = gs.getById(1);
        assertEquals("new", g.getName());
    }

    public void testDeleteById()
    {
        gs.delete(1);
        Group g = gs.getById(1);
        assertNull(g);
    }
}
