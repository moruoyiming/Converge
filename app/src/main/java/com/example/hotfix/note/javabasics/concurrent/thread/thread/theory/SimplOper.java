package com.example.hotfix.note.javabasics.concurrent.thread.thread.theory;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *类说明：简单的程序会有线程安全问题吗？
 */
public class SimplOper {

    private long count =0;//计数器

    private static Integer i = new Integer(10);

	//count进行累加
    public synchronized void incCount(){
        //synchronized (this){
            count = count + 1;//count++;
            //简单操作
        //}
        //Thread.sleep(12);

    }

    // SimplOper.class对象  类锁
    public static synchronized void intStatic(){

    }

    //count进行累加
    public void incCountInt(){
        synchronized (i){
        count = count + 1;//count++;
        }

    }

    //进行累加的线程
    private static class Count extends Thread{

        private SimplOper simplOper;

        public Count(SimplOper simplOper) {
            this.simplOper = simplOper;
        }

        @Override
        public void run() {
            for(int i=0;i<10000;i++){
                simplOper.incCount();//加10000
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimplOper simplOper = new SimplOper();
        //启动两个线程
        Count count1 = new Count(simplOper);
        Count count2 = new Count(simplOper);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simplOper.count);//=20000？
    }

}
