package com.example.hotfix.note.class05;


/**
 * synchronized 隐式锁，内部封装(锁定、解锁) 无法修改。 JDK 内置锁
 * 1.内置锁
 * 持有一把锁 ，静态方法上增加锁，持有的锁是GpsEngine.class对象。  ===  类锁
 */
public class GpsEngine {
    private static GpsEngine gpsEngine;

    public static GpsEngine getGpsEngine() {
        if (gpsEngine == null) {
//            1.thread -0 CPU执行器被操作系统调度释放 3.thread -0 cpu使用，继续创建了 对象。
            gpsEngine = new GpsEngine();// 2.Thread-1 执行，创建对象,cpu释放了。
        }
        return gpsEngine;
    }

    /**
     * 加锁
     *
     * @return
     */
    public static synchronized GpsEngine getGpsEngine2() {//函数锁定
        if (gpsEngine == null) {
//            1.thread -0 执行   thread -1 ... 执行不到
            gpsEngine = new GpsEngine();
        }
        return gpsEngine;
    }


    public static GpsEngine getInstance() {
        if (gpsEngine == null) {
            // Thread-1 Thread-2 Thread-3 都可以进来
            synchronized (GpsEngine.class) {// Thread-0 已经进来   Thread-1 Thread-2 Thread-3 进不去
                if (gpsEngine == null) {// thread-1 进入 判空
                    gpsEngine = new GpsEngine();
                }
            }//Thread-0 执行完毕 释放锁
        }
        return gpsEngine;

    }

}
