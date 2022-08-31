package ru.gb.ex2;

public class Counter {
    int counter = 0;


    void increase(String threadName) {
        counter++;
        System.out.println("Поток " + threadName + ": " + counter);
    }
}
