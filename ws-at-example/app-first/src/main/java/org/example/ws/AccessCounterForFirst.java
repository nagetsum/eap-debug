package org.example.ws;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccessCounterForFirst implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private long accessCount = 0;

    public AccessCounterForFirst() {
    }

    public AccessCounterForFirst(long id) {
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
