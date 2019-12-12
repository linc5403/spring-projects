package com.linchuan.multiThread;

class ThreadDefine extends Thread{
    private SayHello sayHello;

    ThreadDefine(SayHello sayHello) {
        this.sayHello = sayHello;
    }

    @Override
    public void run() {
        sayHello.sayHello();
    }
}

public class ThreadExtend {
    public static void main(String[] args) {
        ThreadDefine thread1 = new ThreadDefine(new SayHello());
        ThreadDefine thread2 = new ThreadDefine(new SayHello());
        thread1.start();
        thread2.start();
    }
}
