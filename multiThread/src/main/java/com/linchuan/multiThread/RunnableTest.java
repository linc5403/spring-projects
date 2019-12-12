package com.linchuan.multiThread;

class RunnableImp implements Runnable{
    private SayHello sayHello;

    public RunnableImp(SayHello sayHello) {
        this.sayHello = sayHello;
    }

    public void run() {
        sayHello.sayHello();
    }
}

public class RunnableTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnableImp(new SayHello()));
        Thread thread2 = new Thread(new RunnableImp(new SayHello()));
        thread1.start();
        thread2.start();
    }

}
