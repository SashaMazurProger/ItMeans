package com.example.sasham.itmeans.data.network.db;

import java.io.Serializable;

public class FavoriteWord implements Serializable{

    private String entry;
    private long timeStamp;

    public FavoriteWord(String entry, long timeStamp) {
        this.entry = entry;
        this.timeStamp = timeStamp;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
