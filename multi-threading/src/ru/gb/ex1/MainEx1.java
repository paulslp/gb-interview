package ru.gb.ex1;

public class MainEx1 {

    public static void main(String[] args) {

        final String[] words = {"ping", "pong"};

        final int START_LETTER_INDEX = 0;

        final Word word = new Word(words, START_LETTER_INDEX);
        for (int printIndex = 0; printIndex < words.length; printIndex++) {
            new Thread(new ThreadWord(word, printIndex)).start();
        }
    }
}