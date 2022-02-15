package com.example.converge.note.javabasics;


import android.view.View;

import com.cocos.base.activity.MvvmActivity;
import com.cocos.base.utils.ResourceUtils;
import com.cocos.base.utils.ToastUtil;
import com.cocos.base.viewmodel.MvvmBaseViewModel;
import com.example.converge.R;
import com.example.converge.databinding.ActivityJavabaseBinding;
import com.example.converge.note.javabasics.generic.NormalGeneric;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaBaseActivity extends MvvmActivity<ActivityJavabaseBinding, MvvmBaseViewModel> implements View.OnClickListener {
    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        viewDataBinding.javageneric.setOnClickListener(this);
        viewDataBinding.javageneric2.setOnClickListener(this);
        viewDataBinding.join.setOnClickListener(this);
        viewDataBinding.deadlock.setOnClickListener(this);
        viewDataBinding.lock.setOnClickListener(this);
        viewDataBinding.threadLocal.setOnClickListener(this);
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_javabase;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.javageneric:
                NormalGeneric generic = new NormalGeneric();
                generic.setData("123");
                viewDataBinding.tvContext.setText("public class NormalGeneric<T> {\n" +
                        "    private T t;\n" +
                        "\n" +
                        "    public NormalGeneric(T t) {\n" +
                        "        this.t = t;\n" +
                        "    }\n" +
                        "\n" +
                        "    public NormalGeneric() {\n" +
                        "\n" +
                        "    }\n" +
                        "\n" +
                        "    public T getData() {\n" +
                        "        return t;\n" +
                        "    }\n" +
                        "\n" +
                        "    public void setData(T t) {\n" +
                        "        this.t = t;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        NormalGeneric<String> generic = new NormalGeneric<>();\n" +
                        "        generic.setData(\"sdfas\");\n" +
                        "        System.out.println(generic.getData());\n" +
                        "    }\n" +
                        "}");
                break;
            case R.id.javageneric2:
                viewDataBinding.tvContext.setText(
                        "public interface GenericInterface<T> {\n" +
                        "\n" +
                        "    public T next();\n" +
                        "\n" +
                        "}\n");
                break;
            case R.id.join:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/JoinTest.java"));
                break;
            case R.id.lock:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/LockTest.java"));
                break;
            case R.id.deadlock:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/DeadLockTest.java"));
                break;
            case R.id.threadLocal:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/ThreadLocalTest.java"));
                break;
        }
    }
}
