package com.example.android.guardiannews.NewsClass;

/**
 * Created by jungh on 2/26/2018.
 */

public class News {

    private String mHead;
    private String mSectonName;
    private String mType;

    public News(String mHead, String mSectonName, String mType) {
        this.mHead = mHead;
        this.mSectonName = mSectonName;
        this.mType = mType;
    }

    public News(String mHead, String mSectonName) {
        this.mHead = mHead;
        this.mSectonName = mSectonName;
    }

    public String getmHead() {
        return mHead;
    }

    public String getmSectonName() {
        return mSectonName;
    }

    public String getmType() {
        return mType;
    }
}
