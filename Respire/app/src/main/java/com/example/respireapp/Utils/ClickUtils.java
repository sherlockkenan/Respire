package com.example.respireapp.Utils;

/**
 * Created by piglet on 2016/9/11.
 */
public class ClickUtils {
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
