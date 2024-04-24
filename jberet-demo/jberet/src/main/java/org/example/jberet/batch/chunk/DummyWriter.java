package org.example.jberet.batch.chunk;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
public class DummyWriter extends AbstractItemWriter {
    @Override
    public void writeItems(List<Object> list) throws Exception {
        System.out.println("## DummyWriter.writeItems");
        list.forEach(System.out::print);
        System.out.println();
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
        System.out.println("## DummyWriter.open(Serializable checkpoint)");
    }

    @Override
    public void close() throws Exception {
        System.out.println("## DummyWriter.close()");
    }
}
