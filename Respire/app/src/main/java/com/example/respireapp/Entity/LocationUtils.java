package com.example.respireapp.Entity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

/**
 * 位置信息的Utils
 *
 * @author zsl
 *
 */
public class LocationUtils {

    // 纬度
    public static double latitude = 0.0;
    // 经度
    public static double longitude = 0.0;

    /**
     * 初始化位置信息
     *
     * @param context
     */
    public static void initLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        try {
            // Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            List<String> providers = locationManager.getProviders(true);
            Location location = null;
            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (location == null || l.getAccuracy() < location.getAccuracy()) {
                    // Found best last known location: %s", l);
                    location = l;
                }
            }
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        } catch (SecurityException e) {
            Log.e("PERMISSION_EXCEPTION","PERMISSION_NOT_GRANTED");
        }
    }
}
