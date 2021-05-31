package com.liangxu.interviewiuc.volatileDemo;

import javax.lang.model.SourceVersion;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuliang
 * @create 2021-05-27 11:23
 * volatile 是java虚拟机提供的轻量级的同步机制
 * 1.保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排序
 */
class MyData {

    volatile int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addTo60() {
        this.number = 60;
    }

    //请注意此时number 前是加了volatile关键字修饰的,验证volatile不保证原子性
    public void addPlusPlus() {
        number++;
    }

    public void addAtomic() {
        atomicInteger.incrementAndGet();
    }
}

/**
 * 1.验证volatile可见性
 * 1.1 int number =0   假设number变量之前根本没有加volatile关键字修饰
 * 1.2 添加volatile 可以解决可见性问题
 * <p>
 * 2.验证volatile 不保证原子性
 * 2.1原子性指的是什么意思？
 * 不可分割,完整性  指是是某个线程在完成具体业务时,中间不可加塞,不可被分割,需要整体完成,要么同事成功,要么同时失败
 * 2.2 解决方式
 * 2.2.1 sync
 * 2.2.2 AtomicInteger
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();


        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }


        while (Thread.activeCount() > 2) {
            Thread.yield();
        }


        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.atomicInteger);


    }


    private static void seeOkByVolatile() {
        MyData myData = new MyData();//资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in ");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();

            System.out.println(Thread.currentThread().getName() + "\t update number value: " + myData.number);
        }, "AAA").start();

        //第二个线程就是我们的main线程
        while (myData.number == 0) {
            //main 线程就在这里等待循环 直到number 不等于0

        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over ");
    }


}