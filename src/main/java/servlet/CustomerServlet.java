package servlet;

import jdbc.dao.CustomerDAO;
import jdbc.dto.Customer;
import org.example.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerDAO.GetAll();
        req.getSession().setAttribute("customerList", customerList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customer.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerDAO.GetAll();

        if ("disable".equals(req.getParameter("actionDisable"))) {
            for (Customer customer : customerList) {
                if (req.getParameter(customer.getId().toString()) != null) {
                    if(!customerDAO.disable(customerDAO.getById(customer.getId())))
                        Logger.info("Error in database");
                }
            }
        }

        if ("enable".equals(req.getParameter("actionEnable"))) {
            for (Customer customer : customerList) {
                if (req.getParameter(customer.getId().toString()) != null) {
                    if(!customerDAO.enable(customerDAO.getById(customer.getId())))
                        Logger.info("Error in database");
                }
            }
        }

        if ("edit".equals(req.getParameter("actionEdit"))) {
            String id = req.getParameter("cusEditId");
            Integer id1 = Integer.valueOf(id);

            String name = req.getParameter("editCusName");
            String phone = req.getParameter("editCusPhone");
            Customer customer = new Customer(id1, name, phone, false);

            customerDAO.update(customer);
        }

        if ("add".equals(req.getParameter("actionAdd"))) {
            String name = req.getParameter("addCusName");
            String phone = req.getParameter("addCusPhone");
            Customer customer = new Customer(0, name, phone, false);

            customerDAO.create(customer);
        }

        customerList = customerDAO.GetAll();
        req.getSession().setAttribute("customerList", customerList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customer.jsp");
        requestDispatcher.forward(req, resp);
    }
}