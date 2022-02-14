package com.example.converge.note.androidadvanced.jetpack.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Outline;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.example.converge.R;
import com.example.converge.utils.glide.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    TextView tvRoom;
    Banner banner ;
    List<String> listBanner =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        banner = findViewById(R.id.banner);
        tvRoom = findViewById(R.id.tv_room);
        //数据库的操作应该是在子线程
        DbTest t=new DbTest();
        t.start();

        listBanner.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic11.nipic.com%2F20101110%2F4224370_074642005762_2.jpg&refer=http%3A%2F%2Fpic11.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634310030&t=33e0e5a6d0861fae9795686890cceb0d");
        listBanner.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic11.nipic.com%2F20101110%2F4224370_074642005762_2.jpg&refer=http%3A%2F%2Fpic11.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634310030&t=33e0e5a6d0861fae9795686890cceb0d");
        initAd();
    }

    public class DbTest extends Thread{
        @Override
        public void run() {
            //数据库的操作都在这里进行
            AppDatabase jettDB= Room.databaseBuilder(getApplicationContext()
                            ,AppDatabase.class
                            ,"jettDB").build();
            StudentDao dao=jettDB.studentDao();
            dao.insert(new Student("jett","123",1));
            dao.insert(new Student("jett1","123",2));
            dao.insert(new Student("jett2","123",3));
            dao.insert(new Student("jett3","123",4));

            List<Student> list=dao.getAll();
            Log.i("jett",list.toString());

            Student jett2=dao.findByName("jett3");
            Log.i("jett",jett2.toString());
            List<Student> allId=dao.getAllId(new int[]{2,3,4});
            Log.i("jett",allId.toString());

            List<StudentTuple> record=dao.getRecord();
            Log.i("jett",record.toString());

            tvRoom.setText(list.toString());
        }
    }
    private void initAd() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(listBanner);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);

        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),30);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        banner.start();
    }
}