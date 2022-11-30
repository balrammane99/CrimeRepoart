package com.intellicloudpps.crimerepoart.Healper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedSession {
    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public static void insertData(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void insertDataBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void insertDataInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void insertStringSet(Context context, String key, Set<String> value) {
        getPrefs(context).edit().putStringSet(key, value).commit();
    }

    public static String getStr(Context context, String key) {
        return getPrefs(context).getString(key, null);
    }

    public static int getDataInt(Context context, String key) {
        return getPrefs(context).getInt(key, 0);
    }

    public static boolean getDataBoolean(Context context, String key) {
        return getPrefs(context).getBoolean(key, true);
    }

    public static String retriveData(Context context, String key) {
        return getPrefs(context).getString(key, "no data found");
    }

    public static Set<String> getStringSet(Context context, String key) {
        return getPrefs(context).getStringSet(key, null);
    }

    public static void deleteData(Context context, String key) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.remove(key);
        editor.commit();
    }

    public static void removeall(Context context, String email) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.clear();
        editor.commit();

    }


}
