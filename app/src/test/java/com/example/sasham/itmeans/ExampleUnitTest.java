package com.example.sasham.itmeans;

import com.example.sasham.itmeans.test.DaggerDataComponent;
import com.example.sasham.itmeans.test.DaggerTest;
import com.example.sasham.itmeans.test.DataComponent;
import com.example.sasham.itmeans.test.DataModule;
import com.example.sasham.itmeans.test.User;
import com.example.sasham.itmeans.test.arch.UserActivity;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void adapterTest() throws Exception {

        DataComponent daggerDataComponent = DaggerDataComponent.builder()
                // .dataModule(new DataModule())
                .build();

        DaggerTest daggerTest = new DaggerTest();
        daggerDataComponent.inject(daggerTest);
        // Assert.assertEquals(daggerTest.user.name, "Max");

//        Assert.assertNotNull(daggerTest.bindsTest);
//        Assert.assertNotNull(daggerTest.testRepo.getUser());
//        Assert.assertEquals(daggerTest.testRepo.getNumb(), 6);

//        UserActivity userActivity=new UserActivity();
//        Assert.assertNotNull(userActivity.testRepo);
//        Assert.assertEquals(userActivity.testRepo.getNumb(),8);


    }

//    @Test
//    public void streamApi(){
//        List<String> stringList=new ArrayList<>();
//        stringList.add("")
//        stringList.stream().
//
//    }
}


