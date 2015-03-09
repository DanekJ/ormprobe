package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.service.GroupService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Group g = new Group();
        g.setName("ahoj");
        GroupService gs = new GroupService();
        gs.insertGroup(g);
        System.out.println( "Hello World!" );
    }
}
