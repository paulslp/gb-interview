package ru.gb.ex1;

class ThreadWord implements Runnable {

    private final int printIndex;

    private final Word word;

    ThreadWord(Word word, int printIndex) {
        this.word = word;
        this.printIndex = printIndex;
    }

    public void run() {
        synchronized (word) {
            for (int i = 1; i <= 10; i++) {
                while (word.getIndex() != printIndex) {
                    try {
                        word.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(word.getCurrentLetter());
                word.refreshIndex();
                word.notifyAll();
            }
        }
    }
}
