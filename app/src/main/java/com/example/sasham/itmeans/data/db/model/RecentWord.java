package com.example.sasham.itmeans.data.db.model;

import io.realm.RealmObject;

public class RecentWord extends RealmObject{
    public static final String TIME_STAMP_FIELD = "timeStamp";
    public static final String ENTRY_FIELD = "entry";
    private String entry;
    private long timeStamp;

    public RecentWord() {
    }

    public RecentWord(String entry, long timeStamp) {
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
