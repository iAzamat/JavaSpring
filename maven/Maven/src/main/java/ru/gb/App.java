package ru.gb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.function.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "My App Servlet", urlPatterns = {"/App", "/App.do"}, initParams = {@WebInitParam(name = "id", value = "1"), @WebInitParam(name = "name", value = "pankaj")})
public class App extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String HTML_START = "<html><body>";
    public static final String HTML_END = "</body></html>";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public App() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Date date = new Date();
        out.println(HTML_START + "<h2>Maven project!</h2><br/><h3>Date=" + date + "</h3>" + HTML_END);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {
        Supplier<String> s = () -> "Hello World!";
        System.out.println(s.get());
    }

}
