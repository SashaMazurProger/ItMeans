package com.example.sasham.itmeans;

import android.util.Log;

import com.example.sasham.itmeans.test.User;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class MyTest {

    private static User user;

    @BeforeClass
    public static void createUser(){
        System.out.println("BeforeClass");
        user=new User("Sasha");
    }
    @Before
    public void before(){
        System.out.println("Before");
    }
    @Test
    public void firstTest() {
        System.out.println("First test");
        Assert.assertEquals(1,(2-1));

    }
    @Test
    public void testUser(){
        System.out.println("Test user");
        Assert.assertNotNull(user);
        System.out.println("User name:"+user.name);
//        if(user.name=="Sasha"){
//            fail("Incorrect user name");
//        }
        assertTrue("User name error",user.name!="Sasha");
    }
    @After
    public void after(){
        System.out.println("After");
    }
    @AfterClass
    public static void destroyUser(){
        System.out.println("AfterClass");
        user=null;
        System.gc();
    }
}
