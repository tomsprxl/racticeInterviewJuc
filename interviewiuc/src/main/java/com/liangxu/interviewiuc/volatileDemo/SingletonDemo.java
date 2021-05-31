package com.liangxu.interviewiuc.volatileDemo;

/**
 * @author xuliang
 * @create 2021-05-31 17:43
 */
public class SingletonDemo {

    private static volatile   SingletonDemo instance = null;

    public SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法 SingletonDemo () ");
    }

    //DCL (double check lock 双端检锁机制 )
    public static  SingletonDemo getInstance() {
        if (null == instance) {
            synchronized (SingletonDemo.class){
                if (null==instance){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
               getInstance();
            },String.valueOf(i)).start();
        }

    }

}