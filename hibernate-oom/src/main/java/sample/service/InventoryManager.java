package sample.service;

import org.hibernate.Session;
import sample.entity.Stock;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.IntStream;

@Singleton
@Startup
public class InventoryManager {

    @PersistenceContext
    EntityManager em;

    @Resource
    ManagedExecutorService executor;

    @PostConstruct
    public void prefill() {
        IntStream.range(0, 100)
                .mapToObj(i -> new Stock("name " + i, 100))
                .forEach(s -> em.persist(s));
        System.out.println("prefill Stock 100 records done");
    }

    public void search(int times) {
        for (int i = 0; i < times; i++) {
            executor.submit(() -> {
                Session session = em.unwrap(Session.class);
                List<Stock> stocks = session.createQuery("from Stock s where s.stockName = :name")
                        .setParameter("name", "name 1")
                        .list();
                System.out.println("Stock found : " + stocks.size() + " records.");
            });
        }
    }

}
