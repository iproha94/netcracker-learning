package ru.ncedu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ilya on 01.07.2015.
 */

public class HelloServlet extends HttpServlet {
    private List<String> colors;
    private AtomicInteger counter;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        if (!colors.contains(color)) {
            color = "yellow";
        }

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print("Hello!!");
        out.println("<h1 style=\"color: " + color + "; margin-left:40% \"> THIS IS SERVLET!!!!! POST METHOD</h1>");
        out.println("Counter = " + counter.get());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        if (!colors.contains(color)) {
            color = "yellow";
        }

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print("Hello!!");
        out.println("<h1 style=\"color: " + color + "; margin-left:40% \"> THIS IS SERVLET!!!!! GET METHOD</h1>");
        out.println("Counter = " + counter.get());
    }

    @Override
    public void init() throws ServletException {
        colors = Arrays.asList("green", "red", "blue");
        counter = new AtomicInteger(0);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter.incrementAndGet();
        super.service(req, resp);
    }
}
