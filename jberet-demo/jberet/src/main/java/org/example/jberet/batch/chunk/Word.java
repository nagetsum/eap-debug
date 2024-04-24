package org.example.jberet.batch.chunk;

public class Word {
    private String text;
    private boolean isEndOfSentence;

    private Word(String text, boolean isEndOfSentence) {
        this.text = text;
        this.isEndOfSentence = isEndOfSentence;
    }

    public static Word of(String text, boolean isEndOfSentence) {
        return new Word(text, isEndOfSentence);
    }

    public String getText() {
        return text;
    }

    public boolean isEndOfSentence() {
        return isEndOfSentence;
    }
}
