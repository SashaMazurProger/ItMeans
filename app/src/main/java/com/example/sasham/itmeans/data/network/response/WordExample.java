package com.example.sasham.itmeans.data.network.response;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;


public class WordExample extends RealmObject implements Parcelable {

    @SerializedName("entry")
    @Expose
    private String entry;
    @SerializedName("request")
    @Expose
    private String request;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("example")
    @Expose
    private RealmList<String> example = null;
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
    public final static Parcelable.Creator<WordExample> CREATOR = new Creator<WordExample>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WordExample createFromParcel(Parcel in) {
            return new WordExample(in);
        }

        public WordExample[] newArray(int size) {
            return (new WordExample[size]);
        }

    };

    protected WordExample(Parcel in) {
        this.entry = ((String) in.readValue((String.class.getClassLoader())));
        this.request = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.example, (java.lang.String.class.getClassLoader()));
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.resultCode = ((String) in.readValue((String.class.getClassLoader())));
        this.resultMsg = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WordExample() {
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

    public List<String> getExample() {
        return example;
    }

    public void setExample(List<String> example) {
        this.example = (RealmList<String>) example;
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
        dest.writeList(example);
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
