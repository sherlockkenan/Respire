package com.example.respireapp.Entity;

/**
 * Created by 佳雯 on 2016/7/12.
 */
        import android.app.Application;

/**
 * Created by piglet on 2016/7/5.
 */
public class Myapp  extends Application {
    public String sessionid;
    public String url="http://202.120.40.178:22180";
    public String getUrl(){return url;};
    public void setUrl(String url){this.url=url;};
    public String getSessionid() {
        return sessionid;
    }
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

}
