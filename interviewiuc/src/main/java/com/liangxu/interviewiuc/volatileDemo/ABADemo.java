package com.liangxu.interviewiuc.volatileDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xuliang
 * @create 2021-06-01 10:13
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    public static void main(String[] args) {
        System.out.println("=====以下是ABA问题的产生=========");
        AtomicInteger atomicInteger = new AtomicInteger(100);
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);

        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( atomicReference.compareAndSet(100,2019)+"\t "+atomicReference.get());

        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====以下是ABA问题的解决=========");

        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(100,1);

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号"+atomicStampedReference.getStamp());



        },"t3").start();
        new Thread(()->{



        },"t4").start();
























    }
}