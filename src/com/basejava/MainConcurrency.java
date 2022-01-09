package com.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);

        Object x = new Object();
        Object y = new Object();
        deadLock(x, y);
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(
                () -> {
                    synchronized (lock1) {
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
        new Thread(
                () -> {
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + " get monitor " + lock2.hashCode());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName() + " try get monitor " + lock1.hashCode());
                        synchronized (lock1) {
                            System.out.println(Thread.currentThread().getName() + " get monitor " + lock1.hashCode());
                        }
                    }
                }).start();
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }
}