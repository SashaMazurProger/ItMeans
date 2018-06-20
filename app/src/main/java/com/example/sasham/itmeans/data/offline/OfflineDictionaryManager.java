package com.example.sasham.itmeans.data.offline;

import android.util.Log;

public class OfflineDictionaryManager {

    public OfflineDictionaryManager() {
        init();
    }

    public void init(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(getClass().getSimpleName(), "init: ok");
    }
}
