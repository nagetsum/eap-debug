package sample.ejb;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface LocalService extends Serializable {
    void debit(int fromAccountId, int amount);
}
