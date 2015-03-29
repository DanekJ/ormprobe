package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.service.TestDaoService;
import com.danekja.edu.ormprobe.utils.DataGenerator;
import com.danekja.edu.ormprobe.utils.MyBatisPersistUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        generateData();
        testDaoService();
    }

    public static void generateData()
    {
        MyBatisPersistUtil pu = new MyBatisPersistUtil();
        DataGenerator dg = new DataGenerator(pu);
        dg.generateData(true);
    }

    public static void testDaoService()
    {
        TestDaoService tds = new TestDaoService();
        for(Group g : tds.listOwnershipCandidates(9)){
            System.out.println(g.id);
        }
        System.out.println(tds.isConnectedToBigGroup(9, 5));
        System.out.println(tds.listItemsBigGroups(5));
        System.out.println(tds.listBigGroupsItems(9));
    }
}
