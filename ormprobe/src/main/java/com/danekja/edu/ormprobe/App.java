package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.domain.Ownership;
import com.danekja.edu.ormprobe.service.OwnershipService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OwnershipService os = new OwnershipService();
        Ownership o = os.getAllOwnerships().get(0);
        System.out.println(o.getUpper());
    }
}
