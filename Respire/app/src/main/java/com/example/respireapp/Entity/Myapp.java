package com.example.respireapp.Entity;

/**
 * Created by 佳雯 on 2016/7/12.
 */
        import android.app.Application;
        import android.content.Context;

        import com.avos.avoscloud.AVOSCloud;
        import com.avos.avoscloud.im.v2.AVIMMessageManager;
        import com.avos.avoscloud.im.v2.AVIMTypedMessage;
        import com.avoscloud.leanchatlib.controller.ChatManager;
        import com.avoscloud.leanchatlib.utils.ThirdPartUserUtils;
        import com.avoscloud.leanchatlib_demo.CustomUserProvider;
        import com.leancloud.im.guide.MessageHandler;
        import com.nostra13.universalimageloader.core.ImageLoader;
        import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
        import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by piglet on 2016/7/5.
 */
public class Myapp  extends Application {
    public String sessionid;
    //public String url="http://59.78.45.13:8000";
    public String url="http://202.120.40.178:22180";
    //public String url="http://10.189.76.151:8000";
    public String flag="";
    public String getUrl(){return url;};
    public void setUrl(String url){this.url=url;};
    public String getSessionid() {
        return sessionid;
    }
    public String getFlag(){return flag;}
    public void setFlag(String flag){
        this.flag=flag;
    }
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "qfIj84Mv4URXbe0fPrGmMNbz-gzGzoHsz",
                "XaRqHOwqy6FNSSS72gtBN640");
        ChatManager.setDebugEnabled(true);// tag leanchatlib
        AVOSCloud.setDebugLogEnabled(true);  // set false when release
        initImageLoader(this);
        ChatManager.getInstance().init(this);
        ThirdPartUserUtils.setThirdPartUserProvider(new CustomUserProvider());
        AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class, new MessageHandler(this));
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
                //.memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
