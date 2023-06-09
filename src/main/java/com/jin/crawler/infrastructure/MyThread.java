package com.jin.crawler.infrastructure;

public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Called from thread " + Thread.currentThread().getName());
    }
}
