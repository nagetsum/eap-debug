package sample.ejb;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface RemoteService extends Serializable {
    void credit(int toAccountId, int amount);
}
