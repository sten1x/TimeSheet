package servlet;

import jdbc.dao.EmployeeDAO;
import jdbc.dao.UserDAO;
import jdbc.dto.User;
import org.example.Logger;

import javax.servlet.RequestDispatcher;
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
        Boolean authenticated = (Boolean) req.getSession().getAttribute("loggedIn");

        if (authenticated != null && authenticated) {
            resp.sendRedirect("home");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new UserDAO().getUsersByNameAndPassword(username, password);

        if (user.getId() > 0) {
            req.getSession().setAttribute("loggedIn", true);
            req.getSession().setAttribute("employeeId", user.getEmployeeId());
            req.getSession().setAttribute("employeeName", new EmployeeDAO().getById(user.getEmployeeId()).getName());
            req.getSession().setAttribute("isAdmin", user.getAdmin());
            Logger.info("Successful authentication for: " + username);
            resp.sendRedirect("home");
        } else {
            Logger.info("Unsuccessful authentication: " + username + " & " + password);
            req.setAttribute("error_message", "Authentification failed!");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}