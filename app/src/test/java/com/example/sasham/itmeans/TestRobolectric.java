package com.example.sasham.itmeans;

import com.example.sasham.itmeans.search.SearchActivity;
import com.example.sasham.itmeans.test.robolectric.LoginActivity;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.realm.Realm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 26, manifest = "AndroidManifest.xml",shadows = {Realm.class})
public class TestRobolectric {


    @Test
    public void testSActivity(){
        LoginActivity activity= Robolectric.setupActivity(LoginActivity.class);

        assertNotNull(activity);

//        searchActivity.onLoading();
//
//        verify(searchActivity).onLoading();
    }
}
