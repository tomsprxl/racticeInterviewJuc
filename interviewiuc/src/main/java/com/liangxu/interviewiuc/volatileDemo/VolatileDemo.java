package com.liangxu.interviewiuc.volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author xuliang
 * @create 2021-05-27 11:23
 *     volatile 是java虚拟机提供的轻量级的同步机制
 *     1.保证可见性
 *     2.不保证原子性
 *     3.禁止指令重排序
 */
class  MyData{

   volatile int number =0;

    public void addTo60(){
        this.number=60;
    }
}

/**
 * 1.验证volatile可见性
 * 1.1 int number =0   假设number变量之前根本没有加volatile关键字修饰
 */
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();//资源类

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in ");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();

            System.out.println(Thread.currentThread().getName()+"\t update number value: "+myData.number);
        },"AAA").start();

        //第二个线程就是我们的main线程
        while (myData.number==0){
            //main 线程就在这里等待循环 直到number 不等于0
            
        }

        System.out.println(Thread.currentThread().getName()+"\t mission is over ");


    }



}