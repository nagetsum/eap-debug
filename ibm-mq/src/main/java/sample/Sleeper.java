package sample;

public class Sleeper {
    public static void sleep(long millisec) {
        System.out.println("sleep start: " + millisec + " millisec");
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep done: " + millisec + " millisec");
    }
}
