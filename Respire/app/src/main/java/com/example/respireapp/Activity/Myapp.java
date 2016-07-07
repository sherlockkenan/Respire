package com.example.respireapp.Activity;

import android.app.Application;

/**
 * Created by piglet on 2016/7/5.
 */
public class Myapp  extends Application {
    public String sessionid;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

}
