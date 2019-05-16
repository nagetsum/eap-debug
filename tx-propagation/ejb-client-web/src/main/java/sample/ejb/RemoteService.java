package sample.ejb;

import javax.ejb.Remote;
import java.io.Serializable;
import org.jboss.ejb.client.annotation.ClientTransaction;
import org.jboss.ejb.client.annotation.ClientTransactionPolicy;

@Remote
//@ClientTransaction(ClientTransactionPolicy.NOT_SUPPORTED)
public interface RemoteService extends Serializable {
    void credit(int toAccountId, int amount);
}
