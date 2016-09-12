package com.avoscloud.leanchatlib_demo;

import android.content.res.Resources;

import com.avoscloud.leanchatlib.utils.ThirdPartUserUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wli on 15/12/4.
 */
public class CustomUserProvider implements ThirdPartUserUtils.ThirdPartDataProvider {

  private static List<ThirdPartUserUtils.ThirdPartUser> partUsers = new ArrayList<ThirdPartUserUtils.ThirdPartUser>();

  private static String avatar = "";
  static {
//    partUsers.add(new ThirdPartUserUtils.ThirdPartUser("wuwu", "user_wuwu", avatar));
//    for (int i = 0; i < 36; i++) {
//      partUsers.add(new ThirdPartUserUtils.ThirdPartUser(i + "", "user_" + i, avatar));
//    }
  }

  @Override
  public ThirdPartUserUtils.ThirdPartUser getSelf() {
    return new ThirdPartUserUtils.ThirdPartUser("daweibayu", "daweibayu",
      "");
  }

  @Override
  public void getFriend(String userId, ThirdPartUserUtils.FetchUserCallBack callBack) {
    for (ThirdPartUserUtils.ThirdPartUser user : partUsers) {
      if (user.userId.equals(userId)) {
        callBack.done(Arrays.asList(user), null);
        return;
      }
    }
    callBack.done(null, new Resources.NotFoundException("not found this user"));
  }

  @Override
  public void getFriends(List<String> list, ThirdPartUserUtils.FetchUserCallBack callBack) {
    List<ThirdPartUserUtils.ThirdPartUser> userList = new ArrayList<ThirdPartUserUtils.ThirdPartUser>();
    for (String userId : list) {
      for (ThirdPartUserUtils.ThirdPartUser user : partUsers) {
        if (user.userId.equals(userId)) {
          userList.add(user);
          break;
        }
      }
    }
    callBack.done(userList, null);
  }

  @Override
  public void getFriends(int skip, int limit, ThirdPartUserUtils.FetchUserCallBack callBack) {
    int begin = partUsers.size() > skip ? skip : partUsers.size();
    int end = partUsers.size() > skip + limit ? skip + limit : partUsers.size();
    callBack.done(partUsers.subList(begin, end), null);
  }
}
