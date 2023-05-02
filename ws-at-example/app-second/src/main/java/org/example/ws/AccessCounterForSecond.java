package org.example.ws;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccessCounterForSecond {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private long accessCount = 0;

    public AccessCounterForSecond() {
    }

    public AccessCounterForSecond(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
    }

    public long increment() {
        this.accessCount++;
        return this.accessCount;
    }
}
