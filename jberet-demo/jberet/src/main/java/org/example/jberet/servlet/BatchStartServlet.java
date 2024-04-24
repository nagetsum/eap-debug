package org.example.jberet.servlet;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/start")
public class BatchStartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        JobOperator jobOp = BatchRuntime.getJobOperator();
        long jobExecutionId = jobOp.start("dumpMessage", new Properties());
        JobStartedQueue.push(jobExecutionId);

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().write("job dumpMessage started");
    }
}
