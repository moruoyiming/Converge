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
        viewDataBinding.callable.setOnClickListener(this);
        viewDataBinding.join.setOnClickListener(this);
        viewDataBinding.deadlock.setOnClickListener(this);
        viewDataBinding.lock.setOnClickListener(this);
        viewDataBinding.threadLocal.setOnClickListener(this);
        viewDataBinding.executor.setOnClickListener(this);
        viewDataBinding.ticketseller.setOnClickListener(this);
        viewDataBinding.cyclicbarrier.setOnClickListener(this);
        viewDataBinding.countdownlatch.setOnClickListener(this);
        viewDataBinding.A1B2C3.setOnClickListener(this);
        viewDataBinding.ABCABC.setOnClickListener(this);
        viewDataBinding.T12A34B56C.setOnClickListener(this);
        viewDataBinding.producerconsumer.setOnClickListener(this);
        viewDataBinding.az.setOnClickListener(this);
        viewDataBinding.max.setOnClickListener(this);

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
            case R.id.callable:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/CallableTest.java"));
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
            case R.id.executor:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/ExecutorsTest.java"));
                break;
            case R.id.ticketseller:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/TicketSeller.java"));
                break;
            case R.id.cyclicbarrier:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/CyclicBarrierTest.java"));
                break;
            case R.id.countdownlatch:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/CountDownLatchTest.java"));
                break;
            case R.id.A1B2C3:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/A1B2C3.java"));
                break;
            case R.id.ABCABC:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/ABCABC.java"));
                break;
            case R.id.T12A34B56C:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/ThreadTest.java"));
                break;
            case R.id.producerconsumer:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/ProducerConsumerTest.java"));
                break;
            case R.id.az:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/顺序打印字母A到Z.java"));
                break;
            case R.id.max:
                viewDataBinding.tvContext.setText(ResourceUtils.readAssets2String("aligorithm/thread/顺序打印数字1到无穷大.java"));
                break;
        }
    }
}
