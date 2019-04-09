package org.jboss.example.mbean.support;

public class ConfigService implements ConfigServiceMBean {
    private int timeout;

    @Override
    public int timeout() {
        return timeout;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }


    @Override
    public void start() {
        //Create a random number between 3000 and 6000 milliseconds
        timeout = (int)Math.round(Math.random() * 3000) + 3000;
        System.out.println("Random timeout set to " + timeout + " seconds");
    }

    @Override
    public void stop() {
        timeout = 0;
    }
}