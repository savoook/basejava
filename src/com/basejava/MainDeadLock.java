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
                        String treadName = Thread.currentThread().getName();
                        System.out.println(treadName + " get monitor " + lock1.hashCode());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(treadName + " try get monitor " + lock2.hashCode());
                        synchronized (lock2) {
                            System.out.println(treadName + " get monitor " + lock2.hashCode());
                        }
                    }
                }).start();
    }
}