package sample.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Stateless
public class RemoteServiceImpl implements RemoteService {

    private static final long serialVersionUID = 1L;

    @Resource(lookup = "java:jboss/PostgresXADS")
    private DataSource ds;

    private int processCount = 0;

    @Override
    public void credit(int toAccounId, int amount) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE account SET balance = balance + ?  WHERE id = ?")) {

            ps.setInt(1, amount);
            ps.setInt(2, toAccounId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        processCount++;
        System.out.println("sample.ejb.RemoteServiceImpl processCount : " + processCount);
        sleep(1000);
    }

    private void sleep(long millisec) {
        try {
            System.out.println("sleep start " + millisec);
            Thread.sleep(millisec);
            System.out.println("sleep done " + millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
