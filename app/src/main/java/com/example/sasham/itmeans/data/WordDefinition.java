package com.example.sasham.itmeans.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WordDefinition implements Parcelable {

    @SerializedName("entry")
    @Expose
    private String entry;
    @SerializedName("request")
    @Expose
    private String request;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("meaning")
    @Expose
    private Meaning meaning;
    @SerializedName("ipa")
    @Expose
    private String ipa;
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
    public final static Parcelable.Creator<WordDefinition> CREATOR = new Creator<WordDefinition>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WordDefinition createFromParcel(Parcel in) {
            return new WordDefinition(in);
        }

        public WordDefinition[] newArray(int size) {
            return (new WordDefinition[size]);
        }

    };

    protected WordDefinition(Parcel in) {
        this.entry = ((String) in.readValue((String.class.getClassLoader())));
        this.request = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
        this.meaning = ((Meaning) in.readValue((Meaning.class.getClassLoader())));
        this.ipa = ((String) in.readValue((String.class.getClassLoader())));
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.resultCode = ((String) in.readValue((String.class.getClassLoader())));
        this.resultMsg = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WordDefinition() {
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

    public Meaning getMeaning() {
        return meaning;
    }

    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
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

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("entry", entry).append("request", request).append("response", response).append("meaning", meaning).append("ipa", ipa).append("version", version).append("author", author).append("email", email).append("resultCode", resultCode).append("resultMsg", resultMsg).toString();
//    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(entry);
        dest.writeValue(request);
        dest.writeValue(response);
        dest.writeValue(meaning);
        dest.writeValue(ipa);
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
