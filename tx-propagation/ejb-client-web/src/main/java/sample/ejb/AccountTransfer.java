package sample.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Singleton
public class AccountTransfer {

    // local SFSB
    @EJB
    private LocalService local;

    // remote SLSB
    private RemoteService remote;

    @PostConstruct
    void init() {
        Hashtable<String, String> jndiProps = new Hashtable<>();
        jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        try {
            Context context = new InitialContext(jndiProps);
            this.remote = (RemoteService) context.lookup("ejb:/remote-ejb/RemoteServiceImpl!sample.ejb.RemoteService");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    // transaction propagation with XADataSource
    public void transfer(int fromId, int toId, int amount) {
        local.debit(fromId, amount);
        remote.credit(toId, amount);
    }
}
