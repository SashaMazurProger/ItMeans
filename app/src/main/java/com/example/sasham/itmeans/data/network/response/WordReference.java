package com.example.sasham.itmeans.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class WordReference extends RealmObject implements Parcelable {

    @SerializedName("entry")
    @Expose
    private String entry;
    @SerializedName("request")
    @Expose
    private String request;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("relation")
    @Expose
    private WordRelation relation;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("result_code")
    @Expose
    private String resultCode;
    @SerializedName("result_msg")
    @Expose
    private String resultMsg;
    public final static Parcelable.Creator<WordReference> CREATOR = new Creator<WordReference>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WordReference createFromParcel(Parcel in) {
            return new WordReference(in);
        }

        public WordReference[] newArray(int size) {
            return (new WordReference[size]);
        }

    };

    protected WordReference(Parcel in) {
        this.entry = ((String) in.readValue((String.class.getClassLoader())));
        this.request = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
        this.relation = ((WordRelation) in.readValue((WordRelation.class.getClassLoader())));
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.resultCode = ((String) in.readValue((String.class.getClassLoader())));
        this.resultMsg = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WordReference() {
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public WordRelation getRelation() {
        return relation;
    }

    public void setRelation(WordRelation relation) {
        this.relation = relation;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(entry);
        dest.writeValue(request);
        dest.writeValue(response);
        dest.writeValue(relation);
        dest.writeValue(version);
        dest.writeValue(author);
        dest.writeValue(email);
        dest.writeValue(resultCode);
        dest.writeValue(resultMsg);
    }

    public int describeContents() {
        return 0;
    }

}