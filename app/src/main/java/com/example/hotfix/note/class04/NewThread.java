package com.example.hotfix.note.class04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        StudentThread studentThread=new StudentThread();
        studentThread.start();

        //任务不能继承 需要寄托
        PersonThread personThread=new PersonThread();
        new Thread(personThread).start();

        //有返回值 任务不能运行 需要寄托Thread
        Asyntask asyntask=new Asyntask();
        FutureTask<String> futureTask=new FutureTask<>(asyntask);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());//阻塞的
    }

    private static class StudentThread extends Thread{
        @Override
        public void run() {
            super.run();
        }
    }

    private static class PersonThread implements Runnable {

        @Override
        public void run() {

        }
    }

    private static class Asyntask implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "success";
        }
    }
}
