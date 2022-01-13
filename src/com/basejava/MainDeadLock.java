package com.basejava;

public class MainDeadLock {
    public static void main(String[] args) {
        Object x = new Object();
        Object y = new Object();
        deadLock(x, y);
        deadLock(y, x);
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(
                () -> {
                    synchronized (lock1) {
                        Thread.currentThread().getName();
                        System.out.println(Thread.currentThread().getName() + " get monitor " + lock1.hashCode());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName() + " try get monitor " + lock2.hashCode());
                        synchronized (lock2) {
                            System.out.println(Thread.currentThread().getName() + " get monitor " + lock2.hashCode());
                        }
                    }
                }).start();
    }
}