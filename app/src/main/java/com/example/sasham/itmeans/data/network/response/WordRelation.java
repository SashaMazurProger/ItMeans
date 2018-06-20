package com.example.sasham.itmeans.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class WordRelation extends RealmObject implements Parcelable
{
    @SerializedName("broad_terms")
    @Expose
    private String broadTerms;
    @SerializedName("narrow_terms")
    @Expose
    private String narrowTerms;
    @SerializedName("related_terms")
    @Expose
    private String relatedTerms;
    @SerializedName("evocations")
    @Expose
    private String evocations;
    @SerializedName("synonyms")
    @Expose
    private String synonyms;
    @SerializedName("associations")
    @Expose
    private String associations;
    @SerializedName("derived_terms")
    @Expose
    private String derivedTerms;
    public final static Creator<WordRelation> CREATOR = new Creator<WordRelation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WordRelation createFromParcel(Parcel in) {
            return new WordRelation(in);
        }

        public WordRelation[] newArray(int size) {
            return (new WordRelation[size]);
        }

    }
            ;

    protected WordRelation(Parcel in) {
        this.broadTerms = ((String) in.readValue((String.class.getClassLoader())));
        this.narrowTerms = ((String) in.readValue((String.class.getClassLoader())));
        this.relatedTerms = ((String) in.readValue((String.class.getClassLoader())));
        this.evocations = ((String) in.readValue((String.class.getClassLoader())));
        this.synonyms = ((String) in.readValue((String.class.getClassLoader())));
        this.associations = ((String) in.readValue((String.class.getClassLoader())));
        this.derivedTerms = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WordRelation() {
    }

    public String getBroadTerms() {
        return broadTerms;
    }

    public void setBroadTerms(String broadTerms) {
        this.broadTerms = broadTerms;
    }

    public String getNarrowTerms() {
        return narrowTerms;
    }

    public void setNarrowTerms(String narrowTerms) {
        this.narrowTerms = narrowTerms;
    }

    public String getRelatedTerms() {
        return relatedTerms;
    }

    public void setRelatedTerms(String relatedTerms) {
        this.relatedTerms = relatedTerms;
    }

    public String getEvocations() {
        return evocations;
    }

    public void setEvocations(String evocations) {
        this.evocations = evocations;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getAssociations() {
        return associations;
    }

    public void setAssociations(String associations) {
        this.associations = associations;
    }

    public String getDerivedTerms() {
        return derivedTerms;
    }

    public void setDerivedTerms(String derivedTerms) {
        this.derivedTerms = derivedTerms;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(broadTerms);
        dest.writeValue(narrowTerms);
        dest.writeValue(relatedTerms);
        dest.writeValue(evocations);
        dest.writeValue(synonyms);
        dest.writeValue(associations);
        dest.writeValue(derivedTerms);
    }

    public int describeContents() {
        return 0;
    }

}
