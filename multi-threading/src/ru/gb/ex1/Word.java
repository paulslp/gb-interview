package ru.gb.ex1;

public class Word {

    private final String[] words;

    private int index;

    public Word(String[] words, int index) {
        this.words = words;
        this.index = index;
    }

    public String getCurrentLetter() {
        return words[index];
    }

    public int getIndex() {
        return index;
    }

    public void refreshIndex() {
        index = (index < words.length - 1) ? index + 1 : 0;
    }
}
