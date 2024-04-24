package org.example.jberet.servlet;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class JobStartedQueue {
    private static Queue<Long> started = new LinkedBlockingQueue<>();

    public static void push(long jobExecutionId) {
        started.add(jobExecutionId);
    }

    public static long poll() {
        return started.poll();
    }
}
