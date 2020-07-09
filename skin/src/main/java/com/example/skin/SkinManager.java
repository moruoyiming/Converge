package com.example.skin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;


import com.example.skin.utils.SkinResources;

import java.lang.reflect.Method;
import java.util.Observable;

/**
 * @author 享学课堂 jett
 */

public class SkinManager extends Observable {

    private volatile static SkinManager instance;
    /**
     * Activity生命周期回调
     */
    private ApplicationActivityLifecycle skinActivityLifecycle;
    private Application mContext;

    /**
     * 初始化 必须在Application中先进行初始化
     *
     * @param application
     */
    public static void init(Application application) {
        if (instance == null) {
            synchronized (SkinManager.class) {
                if (instance == null) {
                    instance = new SkinManager(application);
                }
            }
        }
    }

    private SkinManager(Application application) {
        mContext = application;
        //共享首选项 用于记录当前使用的皮肤
        SkinPreference.init(application);
        //资源管理类 用于从 app/皮肤 中加载资源
        SkinResources.init(application);
        //注册Activity生命周期,并设置被观察者
        skinActivityLifecycle = new ApplicationActivityLifecycle(this);
        application.registerActivityLifecycleCallbacks(skinActivityLifecycle);
        //加载上次使用保存的皮肤
        loadSkin(SkinPreference.getInstance().getSkin());
    }

    public static SkinManager getInstance() {
        return instance;
    }


    /**
     * 记载皮肤并应用
     *
     * @param skinPath 皮肤路径 如果为空则使用默认皮肤
     */
    public void loadSkin(String skinPath) {
        if (TextUtils.isEmpty(skinPath)) {
            //还原默认皮肤
            SkinPreference.getInstance().reset();
            SkinResources.getInstance().reset();
        } else {
            try {
                //宿主app的 resources;
                Resources appResource = mContext.getResources();
//
                //反射创建AssetManager 与 Resource
                AssetManager assetManager = AssetManager.class.newInstance();
                //资源路径设置 目录或压缩包
                Method addAssetPath = assetManager.getClass().getMethod("addAssetPath",
                        String.class);
                addAssetPath.invoke(assetManager, skinPath);

                //根据当前的设备显示器信息 与 配置(横竖屏、语言等) 创建Resources
                Resources skinResource = new Resources(assetManager, appResource.getDisplayMetrics
                        (), appResource.getConfiguration());

                //获取外部Apk(皮肤包) 包名
                PackageManager mPm = mContext.getPackageManager();
                PackageInfo info = mPm.getPackageArchiveInfo(skinPath, PackageManager
                        .GET_ACTIVITIES);
                String packageName = info.packageName;
                SkinResources.getInstance().applySkin(skinResource, packageName);

                //记录
                SkinPreference.getInstance().setSkin(skinPath);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //通知采集的View 更新皮肤
        //被观察者改变 通知所有观察者
        setChanged();
        notifyObservers(null);
    }

}
