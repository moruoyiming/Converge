package com.example.converge.note.androidbasics.ui.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.converge.R;
import com.example.converge.note.androidbasics.ui.viewpager.base.LazyFragment5;


// T1  T2 T3 T4 T5 有他呈现的Fragment
public class MyFragment extends LazyFragment5 {

    private static final String TAG = "MyFragment";
    public static final String POSITION = "Position";

    ImageView imageView;
    TextView textView;
    int tabIndex;
    private CountDownTimer con;

    public static MyFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("Position", position);
        MyFragment fragment = new MyFragment();
        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, tabIndex + " fragment " + "onCreate: " );
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_lazy_loading;
    }

    @Override
    protected void initView(View view) {
        imageView = view.findViewById(R.id.iv_content);
        textView = view.findViewById(R.id.tv_loading);
        tabIndex = getArguments().getInt(POSITION);

        Log.d(TAG, tabIndex + " fragment " + "initView: " );
    }


    // 中断更新
    @Override
    public void onFragmentLoadStop() {
        super.onFragmentLoadStop();
        tabIndex = getArguments().getInt(POSITION);
        handler.removeMessages(10);
        if (con != null) {
            con.cancel();
        }
        Log.d(TAG, tabIndex + " fragment " + "暂停一切操作 pause" );
        //对UI操作的代表
        textView.setText("李元霸");
    }

    //加载数据
    @Override
    public void onFragmentLoad() {
        super.onFragmentLoad();
        getData();
        Log.d(TAG, tabIndex + " fragment " + "真正更新界面" );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }


    private void getData() {
        con= new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                handler.sendEmptyMessage(0);
            }
        };
        con.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, tabIndex + " fragment " + "onResume: " );

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, tabIndex + " fragment " + "onPause: " );

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        tabIndex = getArguments().getInt(POSITION);
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, tabIndex + " fragment " + "setUserVisibleHint: " + isVisibleToUser );

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, tabIndex + " fragment " + "onAttach: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, tabIndex + " fragment " + "onDetach: " );
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            textView.setVisibility(View.GONE);
            int id ;
            switch(tabIndex) {
                case 1:
                    id = R.drawable.a;
                    break;
                case 2:
                    id = R.drawable.b;
                    break;
                case 3:
                    id = R.drawable.c;
                    break;
                case 4:
                    id = R.drawable.d;
                    break;
                default:
                    id = R.drawable.a;
            }
            imageView.setImageResource(id);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setVisibility(View.VISIBLE);
            Log.d(TAG, tabIndex +" handleMessage: " );
            //模拟耗时工作
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (con != null) {
            con.cancel();
        }
    }
}
