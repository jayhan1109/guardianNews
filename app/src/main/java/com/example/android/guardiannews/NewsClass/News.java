package com.example.android.guardiannews.NewsClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jungh on 2/26/2018.
 */

public class News {

    private int mNumber;
    private String mHead;
    private String mSectonName;
    private String mDate;

    private String mUrl;

    public News(int mNumber, String mHead, String mSectonName, String mDate, String mUrl) {
        this.mHead = mHead;
        this.mSectonName = mSectonName;
        this.mNumber = mNumber;

        String tmp = mDate.replace(mDate.substring(mDate.indexOf('T'), mDate.length()), "");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tmp);
            tmp = new SimpleDateFormat("MMM dd", Locale.US).format(date);
            this.mDate = tmp;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        this.mUrl = mUrl;
    }

    public int getmNumber() {
        return mNumber;
    }

    public String getmHead() {
        return mHead;
    }

    public String getmSectonName() {
        return mSectonName;
    }

    public String getmDate() {

        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }
}
