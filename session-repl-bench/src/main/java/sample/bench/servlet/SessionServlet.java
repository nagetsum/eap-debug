package sample.bench.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        HttpSession session = req.getSession();
        stress(session, 100);

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("stress done, id " + session.getId());
    }

    private void stress(HttpSession session, int strength) {
        byte[] array = new byte[1024];
        Arrays.fill(array, Byte.valueOf("1"));

        for (int i = 0; i < strength; i++) {
            session.setAttribute("array" + i, array);
        }

//        for (int i = 0; i < strength; i++) {
//            session.getAttribute("array" + i);
//        }
    }
}
