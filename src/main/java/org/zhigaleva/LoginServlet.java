package org.zhigaleva;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User reqUser = new User(null, null, login, password);
        User user = Users.findByExample(reqUser);
        if (user == null) {
            resp.sendRedirect("https://www.youtube.com/watch?v=WE-BHZB2bt0");
            return;
        }

        req.getSession().setAttribute("currentUser", user);
        resp.sendRedirect(req.getContextPath() + "/main");
    }
}
