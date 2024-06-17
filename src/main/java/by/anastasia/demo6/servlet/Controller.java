package by.anastasia.demo6.servlet;

import java.io.*;

import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.command.CommandType;
import by.anastasia.demo6.exception.DaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String uriPart = CommandType.requestUri(request);
        request.setAttribute("uriPart", uriPart);
        Command command = CommandType.define(uriPart);
        String page;
        try {
            page = command.execute(request);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher(page).forward(request, response);
    }

    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}