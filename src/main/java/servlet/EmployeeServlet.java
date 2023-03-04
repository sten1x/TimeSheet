package servlet;

import jdbc.dao.EmployeeDAO;
import jdbc.dao.UserDAO;
import jdbc.dto.Employee;
import jdbc.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeDAO.GetAll();
        req.getSession().setAttribute("employeeList", employeeList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/employee.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeDAO.GetAll();

        if ("delete".equals(req.getParameter("actionDelete"))) {
            List<User> users = new UserDAO().getAllUsers();

            empLoop: for (Employee employee : employeeList) {
                if (req.getParameter(employee.getId().toString()) != null) {
                    for (User user : users) {
                        if (user.getEmployeeId().equals(employee.getId()) && user.getAdmin()) {
                            req.setAttribute("error_message", "Cannot delete admin");
                            break empLoop;
                        }
                    }
                    employeeDAO.delete(employeeDAO.getById(employee.getId()));
                }
            }
        }

        if ("edit".equals(req.getParameter("actionEdit"))) {
            String id = req.getParameter("empEditId");
            Integer id1 = Integer.valueOf(id);

            String name = req.getParameter("editEmpName");
            Integer age = Integer.valueOf(req.getParameter("editEmpAge"));
            String phone = req.getParameter("editEmpPhone");
            Employee employee = new Employee(id1, name, age, phone);

            employeeDAO.update(employee);
        }

        if ("add".equals(req.getParameter("actionAdd"))) {
            String name = req.getParameter("addEmpName");
            Integer age = Integer.valueOf(req.getParameter("addEmpAge"));
            String phone = req.getParameter("addEmpPhone");
            Employee employee = new Employee(0, name, age, phone);

            employeeDAO.create(employee);
        }

        employeeList = employeeDAO.GetAll();
        req.getSession().setAttribute("employeeList", employeeList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/employee.jsp");
        requestDispatcher.forward(req, resp);
    }
}
