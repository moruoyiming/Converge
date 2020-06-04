package com.example.news.api;

import com.cocos.network.beans.TecentBaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NewsChannelsBean extends TecentBaseResponse {
    @SerializedName("showapi_res_body")
    @Expose
    public ShowapiResBody showapiResBody;

    public class ChannelList {
        @SerializedName("channelId")
        @Expose
        public String channelId;
        @SerializedName("name")
        @Expose
        public String name;
    }

    public class ShowapiResBody {
        @SerializedName("totalNum")
        @Expose
        public Integer totalNum;
        @SerializedName("ret_code")
        @Expose
        public Integer retCode;
        @SerializedName("channelList")
        @Expose
        public List<ChannelList> channelList = null;
    }
}
