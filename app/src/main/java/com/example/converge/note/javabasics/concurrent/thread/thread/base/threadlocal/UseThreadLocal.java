package com.example.converge.note.javabasics.concurrent.thread.thread.base.threadlocal;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *类说明：演示ThreadLocal的使用
 */
public class UseThreadLocal {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
//	static MyThreadLocal<String> threadLocal = new MyThreadLocal<>();

    /**
     * 运行3个线程
     */
    public void StartThreadArray(){
        Thread[] runs = new Thread[3];
        for(int i=0;i<runs.length;i++){
            runs[i]=new Thread(new TestThread(i));
        }
        for(int i=0;i<runs.length;i++){
            runs[i].start();
        }
    }
    
    /**
     *类说明：测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，看看线程之间是否会互相影响
     */
    public static class TestThread implements Runnable{
        int id;
        public TestThread(int id){
            this.id = id;
        }
        public void run() {
            String threadName = Thread.currentThread().getName();
            threadLocal.set("线程"+id);
            if(id==1) {
                threadLocal2.set(id);//线程1才会执行
            }
            System.out.println(threadName+":"+threadLocal.get());
        }
    }

    public static void main(String[] args){
    	UseThreadLocal test = new UseThreadLocal();
        test.StartThreadArray();
    }
}
