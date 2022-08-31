package ru.gb.ex2;

import java.util.concurrent.locks.ReentrantLock;

public class MainEx2 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    lock.lock();
                    counter.increase(Thread.currentThread().getName());
                    lock.unlock();
                }
            }).start();
        }
    }
}

