package com.uestc.mymoa.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by chao on 2015/7/27.
 */
public class UserUtil {
    public static final String STATE_LOGIN = "state_login";
    public static final String USER_ID = "user_id";

    public static boolean getLoginState(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getBoolean(STATE_LOGIN, false);
    }

    public static void setLoginState(Context context, boolean isLogin) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(STATE_LOGIN, isLogin);
        editor.commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getString(USER_ID, "-1");
    }

    public static void setUserId(Context context, String userId) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_ID, userId);
        editor.commit();
    }
}
