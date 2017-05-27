package com.zigcloud.warehousing.entity.tvlive;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiang on 2017/5/25.
 */

public class Channel implements Serializable {
    public String currentUrl;
    public String icon;
    public String id;
    public String name;
    public int streamIndex;
    public ArrayList<String> streams;
    public String url;

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<String> streams) {
        this.streams = streams;
    }

    public int getStreamIndex() {
        return streamIndex;
    }

    public void setStreamIndex(int streamIndex) {
        this.streamIndex = streamIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}