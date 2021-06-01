package com.liangxu.interviewiuc.volatileDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @author xuliang
 * @create 2021-06-01 18:11
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /**
         * 1 故障现象
         *  java.util.ConcurrentModificationException
         *
         * 2 导致原因
         *
         * 3 解决方案
         * 3.1 new Vector<>()
         */

    }

}