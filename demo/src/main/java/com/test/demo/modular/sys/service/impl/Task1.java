package com.test.demo.modular.sys.service.impl;

import java.util.concurrent.Callable;

public class Task1 implements Callable {

    private String token;

    public Task1(String token){
        this.token = token;
    }

//    @Override
//    public void run() {
//        try {
//            System.out.println("TASK1模拟执行复杂操作中......");
//            System.out.println(token);
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("TASK1模拟结束");
//    }

    @Override
    public Object call() throws Exception {
        try {
            System.out.println("TASK1模拟执行复杂操作中......");
            System.out.println(token);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TASK1模拟结束");
        return "task1结束";
    }
}
