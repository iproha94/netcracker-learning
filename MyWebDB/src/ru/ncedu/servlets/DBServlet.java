package ru.ncedu.servlets;

import ru.ncedu.db.SimpleDBInterface;
import ru.ncedu.model.State;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Ilya on 01.07.2015.
 */
public class DBServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();
        pw.print("Data: <br>");

        List<State> states = SimpleDBInterface.loadStates(req.getParameter("query"));

        for (State st : states) {
            pw.printf("ST = %S ; NAME = %S", st.getSt(), st.getName());
            pw.print("<br>");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();
        pw.print("Data: <br>");

        List<State> states = SimpleDBInterface.loadStates();

        for (State st : states) {
            pw.printf("ST = %S ; NAME = %S", st.getSt(), st.getName());
            pw.print("<br>");
        }
    }
}
