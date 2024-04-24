package org.example.jberet.servlet;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stop")
public class BatchStopServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        JobOperator jobOp = BatchRuntime.getJobOperator();
        long id = JobStartedQueue.poll();
        jobOp.stop(id);
        JobExecution execution = jobOp.getJobExecution(id);

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().write("job dumpMessage started");
    }
}
