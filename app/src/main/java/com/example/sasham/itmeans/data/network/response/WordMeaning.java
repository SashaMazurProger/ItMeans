package com.example.sasham.itmeans.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Sasha M on 15.04.2018.
 */

public class WordMeaning extends RealmObject implements Parcelable
{

    @SerializedName("noun")
    @Expose
    private String noun;
    @SerializedName("verb")
    @Expose
    private String verb;
    @SerializedName("adverb")
    @Expose
    private String adverb;
    @SerializedName("adjective")
    @Expose
    private String adjective;
    public final static Parcelable.Creator<WordMeaning> CREATOR = new Creator<WordMeaning>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WordMeaning createFromParcel(Parcel in) {
            return new WordMeaning(in);
        }

        public WordMeaning[] newArray(int size) {
            return (new WordMeaning[size]);
        }

    }
            ;

    protected WordMeaning(Parcel in) {
        this.noun = ((String) in.readValue((String.class.getClassLoader())));
        this.verb = ((String) in.readValue((String.class.getClassLoader())));
        this.adverb = ((String) in.readValue((String.class.getClassLoader())));
        this.adjective = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WordMeaning() {
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getAdverb() {
        return adverb;
    }

    public void setAdverb(String adverb) {
        this.adverb = adverb;
    }

    public String getAdjective() {
        return adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("noun", noun).append("verb", verb).append("adverb", adverb).append("adjective", adjective).toString();
//    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(noun);
        dest.writeValue(verb);
        dest.writeValue(adverb);
        dest.writeValue(adjective);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Meaning{" +
                "noun='" + noun + '\'' +
                ", verb='" + verb + '\'' +
                ", adverb='" + adverb + '\'' +
                ", adjective='" + adjective + '\'' +
                '}';
    }
}