package com.zigcloud.warehousing.adapter;

/**
 * Created by Jay on 2015/9/24 0024.
 */
public class Icon {
    private int iId;
    private String iName;
    public String currentUrl;
    public Icon() {
    }

    public Icon(int iId, String iName,String currentUrl) {
        this.iId = iId;
        this.iName = iName;
        this.currentUrl = currentUrl;
    }

    public int getiId() {
        return iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }
}
