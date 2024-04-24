package org.example.jberet.batch.chunk;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

@Named
public class DummyReader extends AbstractItemReader {

    private final Iterator<String> messages;

    public DummyReader() {
        this.messages = Arrays.asList("Duke", "is", "the", "symbol", "of", "java").iterator();
    }

    @Override
    public Object readItem() throws Exception {
        System.out.println("## DummyReader.readItem()");
        if (!messages.hasNext()) {
            return null;
        }
        return Word.of(messages.next(), !messages.hasNext());
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
        System.out.println("## DummyReader.open(Serializable checkpoint)");
    }

    @Override
    public void close() throws Exception {
        System.out.println("## DummyReader.close()");
    }
}
