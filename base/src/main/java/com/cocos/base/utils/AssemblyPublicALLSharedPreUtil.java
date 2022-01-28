package com.cocos.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

public class AssemblyPublicALLSharedPreUtil implements SharedPreferences {

    public static final String ACCOUNT_PrivacyPolicy =  "privacy_policy_update_status";
    public static final String ACCOUNT_PrivacyPolicyVERSITION =  "privacy_policy_update_versition";

    private static AssemblyPublicALLSharedPreUtil mInstance = null;
    protected static SharedPreferences mSharedPreferences;
    protected static Editor mEditor;
    boolean exist = false;

    protected static String sSharedPreName = "PublicALL";

    protected AssemblyPublicALLSharedPreUtil() {
    }

    public static String getSharedPreName() {
        return sSharedPreName;
    }

    public static void setSharedPreName(String sSharedPreName) {
        AssemblyPublicALLSharedPreUtil.sSharedPreName = sSharedPreName;
        mInstance = null;
    }

    /**
     * 单体类获取
     *
     * @return ISharedPref对象
     */
    public synchronized static AssemblyPublicALLSharedPreUtil getInstance(Context context) {
        if (null == mInstance || mSharedPreferences == null)
            mInstance = new AssemblyPublicALLSharedPreUtil(context.getApplicationContext());
        return mInstance;
    }

    /**
     * 构造函数
     * <p/>
     * 配置文件名
     */
    private AssemblyPublicALLSharedPreUtil(Context context) {

        mSharedPreferences = context.getSharedPreferences(sSharedPreName,
                Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    /**
     * 写入字符串信息
     *
     * @param key
     * @param value
     */
    public void putStringValue(String key, String value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * 获取字符串信息
     *
     * @param username
     * @param key
     * @return
     */
    public String getStringValue(String key, String username) {
        return mSharedPreferences.getString(key, username);
    }

    /**
     * 写入long型数据
     *
     * @param key
     * @param value
     */
    public void putLongValue(String key, long value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * 获取long型数据
     *
     * @param key
     * @return
     */
    public long getLongValue(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    /**
     * 写入int数据
     *
     * @param key
     * @param value
     */
    public void putIntValue(String key, int value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     * 获取int数据
     *
     * @param key
     * @return
     */
    public int getIntValue(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    /**
     * 写入boolean值
     *
     * @param key
     * @param flag
     */
    public void putBooleanValue(String key, boolean flag) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, flag);
        mEditor.commit();
    }

    /**
     * 获取boolean值
     *
     * @param key
     * @return
     */
    public boolean getBooleanValue(String key) {
        return mSharedPreferences.getBoolean(key, true);
    }

    @Override
    public boolean contains(String key) {
        exist = mSharedPreferences.contains(key);
        return exist;
    }

    @Override
    public Editor edit() {
        return mSharedPreferences.edit();
    }

    @Override
    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public void setFloat(String speed, float speed1) {
        mEditor = mSharedPreferences.edit();
        mEditor.putFloat(speed, speed1);
        mEditor.commit();
    }

    @Override
    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    @Override
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    //保存配置信息
    public AssemblyPublicALLSharedPreUtil saveString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
        return this;
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(
            OnSharedPreferenceChangeListener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(
            OnSharedPreferenceChangeListener listener) {
        // TODO Auto-generated method stub

    }

    /***
     * 设置自定义的亮度
     */
    public void setScreenDefaultValue(int dafaultint) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt("screennight_setvalue", dafaultint);
        mEditor.commit();
    }

    /**
     * 获得自定义的亮度
     */
    public int getScreenValueDefault() {
        int dafaultvalue = mSharedPreferences.getInt("screennight_setvalue", 50);
        return dafaultvalue;
    }

    /***
     * 设置为夜间模式
     */
    public void setScreenNight() {
        mEditor = mSharedPreferences.edit();
        mEditor.putString("screennight_set", "night");
        mEditor.commit();
    }

    /**
     * 设置为日间模式
     */
    public void setScreenDefault() {
        mEditor = mSharedPreferences.edit();
        mEditor.putString("screennight_set", "default");
        mEditor.commit();
    }

    /**
     * 是否夜间模式
     */
    public boolean isScreenNight() {
        boolean isNight = false;
        String nightStatus = mSharedPreferences.getString("screennight_set", null);
        if (nightStatus != null) {
            if (nightStatus.equals("night") == true) {
                isNight = true;
            }
        }
        return isNight;
    }

    /**
     * 置空
     */
    public void setNull() {
        // if (mInstance != null) {
        mInstance = null;
        mSharedPreferences = null;
        // }
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        // TODO Auto-generated method stub
        return null;
    }


}
