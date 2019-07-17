package sample;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.TimeUnit;

@WebListener
public class SlowInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            System.out.println("context initialized start");
            TimeUnit.MINUTES.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("context initialized done");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
