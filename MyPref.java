package  com.pinnacle.sparkoff.prefs;

import android.content.Context;
import android.content.SharedPreferences;


/*Class id used to store the app data in share perfrence*/
public class MyPref {

    private static final String PERFERENCE_NAME = "sparkoff";
    private static SharedPreferences _sPrefs = null;
    private static SharedPreferences.Editor _editor = null;
    private static MyPref _instance = null;


    public MyPref() {
    }

    public MyPref(Context context) {
        _sPrefs = context.getSharedPreferences(PERFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public static MyPref getInstance(Context context) {

        _instance = new MyPref(context);

        return _instance;
    }

    public static void setInstance(MyPref instance) {
        // TODO Auto-generated method stub
        _instance = instance;

    }

    public String readPrefs(String pref_name) {
        return _sPrefs.getString(pref_name, "");

    }

    public String readPrefs(String pref_name, String defaultVaule) {
        return _sPrefs.getString(pref_name, defaultVaule);
    }

    public void writePrefs(String pref_name, String pref_val) {
        _editor = _sPrefs.edit();
        _editor.putString(pref_name, pref_val);
        _editor.commit();
    }

    public void clearPrefs() {
        _editor = _sPrefs.edit();
        _editor.clear();
        _editor.commit();
    }

    public boolean readBooleanPrefs(String pref_name) {
        return _sPrefs.getBoolean(pref_name, false);
    }

    public boolean readBooleanPrefs(String pref_name, boolean value) {
        return _sPrefs.getBoolean(pref_name, value);
    }

    public int readIntegerPrefs(String pref_name) {
        return _sPrefs.getInt(pref_name, -1);
    }

    public int readIntegerPrefs(String pref_name, int defaultValue) {
        return _sPrefs.getInt(pref_name, defaultValue);
    }

    public long readLongPrefs(String pref_name, long defaultValue) {
        return _sPrefs.getLong(pref_name, defaultValue);
    }

    public void writeBooleanPrefs(String pref_name, boolean pref_val) {
        _editor = _sPrefs.edit();
        _editor.putBoolean(pref_name, pref_val);
        _editor.commit();
    }

    public void writeIntegerPref(String pref_name, int pref_val) {
        _editor = _sPrefs.edit();
        _editor.putInt(pref_name, pref_val);
        _editor.commit();
    }

    public void writeLongPref(String pref_name, long pref_val) {
        _editor = _sPrefs.edit();
        _editor.putLong(pref_name, pref_val);
        _editor.commit();
    }

    public void removePref(String pref_name) {
        _editor = _sPrefs.edit();
        _editor.remove(pref_name);
        _editor.commit();
    }

}
