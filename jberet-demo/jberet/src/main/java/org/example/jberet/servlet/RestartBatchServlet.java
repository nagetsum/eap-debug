package org.example.jberet.servlet;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/restart")
public class RestartBatchServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        JobOperator jobOp = BatchRuntime.getJobOperator();
        long jobExecutionId = JobStartedQueue.poll();
        jobOp.restart(jobExecutionId, new Properties());

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().write("job restart : " + jobExecutionId);
    }
}
