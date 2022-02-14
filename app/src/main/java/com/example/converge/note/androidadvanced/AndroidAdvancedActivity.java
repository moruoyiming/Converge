package com.example.converge.note.androidadvanced;

import android.content.Intent;
import android.content.res.AssetManager;
import android.view.View;

import com.cocos.base.activity.MvvmActivity;
import com.cocos.base.viewmodel.MvvmBaseViewModel;
import com.example.converge.R;
import com.example.converge.activity.PermissionActivity;
import com.example.converge.databinding.ActivityAndroidadvancedBinding;
import com.example.converge.note.androidadvanced.frame.dagger2.FirstActivity;
import com.example.converge.note.androidadvanced.frame.dagger3.DaggerActivity;
import com.example.converge.note.androidadvanced.framework.binder.MMAPActivity;
import com.example.converge.note.androidadvanced.framework.binder.demo.client.ClientActivity;
import com.example.converge.note.androidadvanced.jetpack.databinding.DataBindingActivity;
import com.example.converge.note.androidadvanced.jetpack.lifecycle.LifeCycleActivity;
import com.example.converge.note.androidadvanced.jetpack.livedata.LiveDataActivity;
import com.example.converge.note.androidadvanced.jetpack.navigation.NavigationActivity;
import com.example.converge.note.androidadvanced.jetpack.paging.PagingActivity;
import com.example.converge.note.androidadvanced.jetpack.room.RoomActivity;
import com.example.converge.note.androidadvanced.jetpack.room.ViewModelActivity;
import com.example.converge.note.androidadvanced.jetpack.workmanager.WorkManagerActivity;
import com.example.converge.note.androidadvanced.ndk.NdkActivity;
import com.example.converge.note.androidadvanced.plugin.PluginTestActivity;
import com.example.converge.note.androidadvanced.opengl.OpenGLActivity;
import com.example.converge.note.androidbasics.AndroidbasicsActivity;
import com.example.converge.note.androidbasics.ui.fish.FishActivity;
import com.example.converge.note.androidbasics.ui.nestedscroll.ScrollActivity;
import com.example.converge.note.androidbasics.ui.recyclerview.RecyclerViewActivity;
import com.example.converge.note.androidbasics.ui.slideview.SlideCardActivity;
import com.example.converge.note.androidbasics.ui.viewpager.ViewPagerLazyActivity;
import com.example.converge.utils.Utils;
import com.example.dexdiff.DexdiffActivity;
import com.example.skin.SkinManager;

/**
 * @Date: 2022/2/14
 * @Time: 10:10 上午
 * @Author: Jian
 */
public class AndroidAdvancedActivity extends MvvmActivity<ActivityAndroidadvancedBinding,MvvmBaseViewModel> implements View.OnClickListener {


    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        viewDataBinding.skin.setOnClickListener(this);
        viewDataBinding.fish.setOnClickListener(this);
        viewDataBinding.mmap.setOnClickListener(this);
        viewDataBinding.aidl.setOnClickListener(this);
        viewDataBinding.permission.setOnClickListener(this);
        viewDataBinding.dexdiff.setOnClickListener(this);
        viewDataBinding.plugin.setOnClickListener(this);
        viewDataBinding.hotfix.setOnClickListener(this);
        viewDataBinding.dagger2.setOnClickListener(this);
        viewDataBinding.dagger3.setOnClickListener(this);
        viewDataBinding.lifecycle.setOnClickListener(this);
        viewDataBinding.livedata.setOnClickListener(this);
        viewDataBinding.databinding.setOnClickListener(this);
        viewDataBinding.room.setOnClickListener(this);
        viewDataBinding.viewmodel.setOnClickListener(this);
        viewDataBinding.navigation.setOnClickListener(this);
        viewDataBinding.paging.setOnClickListener(this);
        viewDataBinding.workmanager.setOnClickListener(this);
        viewDataBinding.opengl.setOnClickListener(this);
        viewDataBinding.ndk.setOnClickListener(this);
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_androidadvanced;
    }

    boolean change =false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skin:
                if (!change) {
                    change = true;
                    SkinManager.getInstance().loadSkin("/data/data/com.example.converge/skin/skins-debug.apk");
                } else {
                    change = false;
                    SkinManager.getInstance().loadSkin(null);
                }
                break;
            case R.id.permission:
                Intent per = new Intent(AndroidAdvancedActivity.this, PermissionActivity.class);
                startActivity(per);
                break;
            case R.id.fish:
                Intent fish = new Intent(AndroidAdvancedActivity.this, FishActivity.class);
                startActivity(fish);
                break;
            case R.id.mmap:
                Intent mmap = new Intent(AndroidAdvancedActivity.this, MMAPActivity.class);
                startActivity(mmap);
                break;
            case R.id.aidl:
                Intent aidl = new Intent(AndroidAdvancedActivity.this, ClientActivity.class);
                startActivity(aidl);
                break;
            case R.id.dexdiff:
                Intent dexdiff = new Intent(AndroidAdvancedActivity.this, DexdiffActivity.class);
                startActivity(dexdiff);
                break;
            case R.id.plugin:
                Intent plugin = new Intent(AndroidAdvancedActivity.this, PluginTestActivity.class);
                startActivity(plugin);
                break;
            case R.id.hotfix:
                Utils.what();
                break;
            case R.id.dagger2:
                Intent dagger2 = new Intent(AndroidAdvancedActivity.this, FirstActivity.class);
                startActivity(dagger2);
                break;
            case R.id.dagger3:
                Intent dagger3 = new Intent(AndroidAdvancedActivity.this, DaggerActivity.class);
                startActivity(dagger3);
                break;
            case R.id.lifecycle:
                Intent lifecycle = new Intent(AndroidAdvancedActivity.this, LifeCycleActivity.class);
                startActivity(lifecycle);
                break;
            case R.id.livedata:
                Intent livedata = new Intent(AndroidAdvancedActivity.this, LiveDataActivity.class);
                startActivity(livedata);
                break;
            case R.id.databinding:
                Intent databinding = new Intent(AndroidAdvancedActivity.this, DataBindingActivity.class);
                startActivity(databinding);
                break;
            case R.id.room:
                Intent room = new Intent(AndroidAdvancedActivity.this, RoomActivity.class);
                startActivity(room);
                break;
            case R.id.viewmodel:
                Intent viewmodel = new Intent(AndroidAdvancedActivity.this, ViewModelActivity.class);
                startActivity(viewmodel);
                break;
            case R.id.navigation:
                Intent navigation = new Intent(AndroidAdvancedActivity.this, NavigationActivity.class);
                startActivity(navigation);
                break;
            case R.id.paging:
                Intent paging = new Intent(AndroidAdvancedActivity.this, PagingActivity.class);
                startActivity(paging);
                break;
            case R.id.workmanager:
                Intent workmanager = new Intent(AndroidAdvancedActivity.this, WorkManagerActivity.class);
                startActivity(workmanager);
                break;
            case R.id.opengl:
                Intent opengl = new Intent(AndroidAdvancedActivity.this, OpenGLActivity.class);
                startActivity(opengl);
                break;
            case R.id.ndk:
                Intent ndk = new Intent(AndroidAdvancedActivity.this, NdkActivity.class);
                startActivity(ndk);
                break;

        }
    }
}
