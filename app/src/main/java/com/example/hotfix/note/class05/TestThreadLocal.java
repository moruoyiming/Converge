package com.example.hotfix.note.class05;

import androidx.annotation.Nullable;

public class TestThreadLocal {
    static ThreadLocal<String> threadLocal=new ThreadLocal<String>(){
        @Nullable
        @Override
        protected String initialValue() {
            return "波多野结衣";
        }
    };

    public static void main(String[] args) {
        new UserThread().start();
        new PersonThread().start();
        System.out.println("首次获取: main+  value= "+threadLocal.get());
    }

    private static class UserThread extends Thread {
        @Override
        public void run() {
            super.run();
            String threadName=currentThread().getName();
            System.out.println("首次获取: threadname="+threadName+"  value= "+threadLocal.get());
            threadLocal.set("小泽玛利亚");
            System.out.println("set后获取: threadname="+threadName+"  value= "+threadLocal.get());
        }
    }

    private static class PersonThread extends Thread {
        @Override
        public void run() {
            super.run();
            String threadName=currentThread().getName();
            System.out.println("首次获取: threadname="+threadName+"  value= "+threadLocal.get());
            threadLocal.set("芽森");
            System.out.println("set后获取: threadname="+threadName+"  value= "+threadLocal.get());
        }
    }
}
