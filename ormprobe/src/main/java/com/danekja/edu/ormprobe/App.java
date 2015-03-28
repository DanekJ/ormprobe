package com.danekja.edu.ormprobe;

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
        MyBatisPersistUtil pu = new MyBatisPersistUtil();
        DataGenerator dg = new DataGenerator(pu);
        dg.generateData(true);
    }
}
