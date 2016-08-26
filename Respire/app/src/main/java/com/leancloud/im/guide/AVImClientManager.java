package com.leancloud.im.guide;

import android.text.TextUtils;
import android.util.Log;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationQuery;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;

import java.util.List;

/**
 * Created by wli on 15/8/13.
 */
public class AVImClientManager {

  private static AVImClientManager imClientManager;

  private AVIMClient avimClient;
  private String clientId;

  private String status;

  public synchronized static AVImClientManager getInstance() {
    if (null == imClientManager) {
      imClientManager = new AVImClientManager();
    }
    return imClientManager;
  }

  private AVImClientManager() {
  }

  public void open(String clientId, AVIMClientCallback callback) {
    this.clientId = clientId;
    avimClient = AVIMClient.getInstance(clientId);
    avimClient.open(callback);
  }

  public AVIMClient getClient() {
    return avimClient;
  }

  public String getClientId() {
    if (TextUtils.isEmpty(clientId)) {
      throw new IllegalStateException("Please call AVImClientManager.open first");
    }
    return clientId;
  }

//  public List<AVIMConversation> getConvs(int limit){
//    AVIMConversationQuery query = avimClient.getQuery();
//    List<AVIMConversation> result;
//    query.limit(limit);
//    query.findInBackground(new AVIMConversationQueryCallback(){
//      @Override
//      public void done(List<AVIMConversation> convs, AVIMException e){
//        if(e==null){
//          //convs就是获取到的conversation列表
//          //注意：按每个对话的最后更新日期（收到最后一条消息的时间）倒序排列
//        }
//      }
//    });
//    return
//  }
}
