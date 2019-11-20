package sample.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 1)
public class CounterBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cnt;
    private int[] hugeArray;

    @PostConstruct
    public void init() {
        System.out.println("##### callback sample.ejb.CounterBean#init()");
        // 50MB
        this.hugeArray = new int[1024 * 1024 * 50];
    }

    public int incrementAndGet() {
        cnt++;
        return cnt;
    }

    public int get() {
        return cnt;
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("##### callback sample.ejb.CounterBean#preDstroy()");
    }
}
