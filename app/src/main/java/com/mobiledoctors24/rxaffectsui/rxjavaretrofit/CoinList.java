package com.mobiledoctors24.rxaffectsui.rxjavaretrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CoinList {
    @SerializedName("Response")
    @Expose
    private String response;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("BaseImageUrl")
    @Expose
    private String baseImageUrl;
    @SerializedName("BaseLinkUrl")
    @Expose
    private String baseLinkUrl;
    @SerializedName("Data")
    @Expose
    private Map<String,CryptoData> data;
    @SerializedName("Type")
    @Expose
    private Integer type;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    public String getBaseLinkUrl() {
        return baseLinkUrl;
    }

    public void setBaseLinkUrl(String baseLinkUrl) {
        this.baseLinkUrl = baseLinkUrl;
    }
    public Map<String,CryptoData> getData() {
        return data;
    }

    public void setData(Map<String,CryptoData> data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
