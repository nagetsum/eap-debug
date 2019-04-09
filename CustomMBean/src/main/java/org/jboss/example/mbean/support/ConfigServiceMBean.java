package org.jboss.example.mbean.support;

public interface ConfigServiceMBean {
    int timeout();  // OK
    int getTimeout();  // EAP can't expose camel case operation. You can only use lowercase.
    void start();
    void stop();
}