package org.example.jberet.batch.chunk;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class DummyProcessor implements ItemProcessor {
    @Override
    public Object processItem(Object o) throws Exception {
        Word word = (Word) o;
        if (word.isEndOfSentence()) {
            System.out.println("## DummyProcessor.processItem: add .");
            return word.getText() + ".";
        } else {
            System.out.println("## DummyProcessor.processItem: add [SP]");
            return word.getText() + " ";
        }
    }
}
