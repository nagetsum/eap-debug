package org.jboss.example;

public class Sleeper {
    public static void sleep(long milliseconds) {
        try {
            System.out.println("sleep start: " + milliseconds);
            Thread.sleep(milliseconds);
            System.out.println("sleep finish: " + milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
