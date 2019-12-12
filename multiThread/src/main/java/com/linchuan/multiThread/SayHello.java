package com.linchuan.multiThread;

public class SayHello {
    public void sayHello() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Hello, " + Thread.currentThread().getName());
        }
    }
}
