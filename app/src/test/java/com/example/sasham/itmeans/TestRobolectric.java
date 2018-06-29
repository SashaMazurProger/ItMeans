package com.example.sasham.itmeans;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.realm.Realm;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 26, manifest = "AndroidManifest.xml",shadows = {Realm.class})
public class TestRobolectric {



}
