package com.test.demo.modular.sys.service.impl;

import java.util.concurrent.Callable;

public class Task2 implements Callable {

//    @Override
//    public void run() {
//        try {
//            System.out.println("TASK2模拟执行复杂操作中......");
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("TASK2模拟结束");
//    }

    @Override
    public Object call() throws Exception {
        try {
            System.out.println("TASK2模拟执行复杂操作中......");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TASK2模拟结束");
        return "task2结束";
    }
}
