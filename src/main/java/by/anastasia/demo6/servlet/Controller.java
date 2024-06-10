package by.anastasia.demo6.servlet;

import java.io.*;

import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
//        String strNum = request.getParameter("number");
//        int resNum = 2 * Integer.parseInt(strNum);
//        request.setAttribute("result", resNum);
        String query = request.getRequestURI();
        query = query.split("/")[2];
        query = query.split("\\.")[0];
        request.setAttribute("queryStr", query);
        Command command = CommandType.define(query);
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);
    }

    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}