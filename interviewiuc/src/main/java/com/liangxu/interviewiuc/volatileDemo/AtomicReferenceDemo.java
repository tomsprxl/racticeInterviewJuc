package com.liangxu.interviewiuc.volatileDemo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuliang
 * @create 2021-06-01 9:53
 */

class User {
    String userName;
    int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}


public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("z3",22);
        User li4 = new User("li4",25);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,li4)+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+atomicReference.get().toString());

    }

}