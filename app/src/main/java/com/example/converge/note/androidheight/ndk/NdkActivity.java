package com.example.converge.note.androidheight.ndk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.converge.R;

public class NdkActivity extends Activity implements View.OnClickListener {

    public static final String TAG = NdkActivity.class.getSimpleName();

    private Button bt1, bt2, bt3, bt4, bt5;

    // Used to load the 'native-lib' library on application startup.
    static {
//        System.load("d://xxx/xxx/xx/native-lib");//这种是可以绝对路径的加载动态链接库文件
        System.loadLibrary("native-lib"); //这种是从库目录遍历层级目录，去自动的寻找
    }

    static final int A = 12;
    public String name = "Derry";
    public static int age = 29;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        bt1 = findViewById(R.id.button1);
        bt1.setOnClickListener(this);
        bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        bt3 = findViewById(R.id.button3);
        bt3.setOnClickListener(this);
        bt4 = findViewById(R.id.button4);
        bt4.setOnClickListener(this);
        bt5 = findViewById(R.id.button5);
        bt5.setOnClickListener(this);
        changeName();
        changeAge();
        callAddMethod();
        tv.setText(name + "age=" + age);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    //Java 本地方法 实现在Native层
    public native String getStringPWD();

    public static native String getStringPWD2();

    public native void changeName();

    public static native void changeAge();

    public native void callAddMethod();

    //String 引用类型 数组
    public native void testArrayAction(int count, String textInfo, int[] ints, String[] strs);

    public native void putObject(Student student, String str);//传递引用类型，传递对象

    public native void insertObject();//凭空创建对象

    public native void testQuote();//测试引用

    public native void delQuote();//释放全局引用

    //提供函数给native层
    public int add(int number1, int number2) {
        Log.i("sdfasf", (number1 + number2) + "");
        return number1 + number2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                int[] ints = new int[]{1, 2, 3, 4, 5};
                String[] strs = new String[]{"李小龙", "李连杰", "成龙"};
                testArrayAction(99, "你好", ints, strs);
                for (int anInt : ints) {
                    Log.d(TAG, "test1:anInt" + anInt);
                }
                for (String st : strs) {
                    Log.d(TAG, "test1:st" + st);
                }
                break;
            case R.id.button2:
                Student student = new Student();
                student.name = "JRL";
                student.age = 88;
                putObject(student,"九阳神功");
                Log.d(TAG, "student getName" + student.getName());
                Log.d(TAG, "student getAge" + student.getAge()   );
                break;
            case R.id.button3:
                insertObject();
                break;
            case R.id.button4:
                testQuote();
                break;
            case R.id.button5:
                delQuote();
                break;
        }
    }
}

// 签名规则
//Java的boolean  --- Z
//Java的byte     --- B
//Java的char     --- C
//Java的short    --- S
//Java的int      --- I
//Java的long     --- J
//Java的float    --- F
//Java的double   --- D
//Java的void     --- V
//Java的引用类型   --- Lxxx/xxx/xx/类名
//Java的String   --- Ljava/lang/String
//Java的array    --- int[] ----> [I    double[][][] ----> [[[D

//javap -s -p xxx.class
// -s输出xxx.class的所有属性和方法的签名
// -p忽略私有公开的所有属性方法全部输出