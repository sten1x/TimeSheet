package servlet;

import jdbc.dao.CustomerDAO;
import jdbc.dao.ProjectDAO;
import jdbc.dto.Customer;
import jdbc.dto.Project;
import org.example.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
    ProjectDAO projectDAO = new ProjectDAO();
    CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projectList = projectDAO.GetAll();
        req.getSession().setAttribute("projectList", projectList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/project.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projectList = projectDAO.GetAll();

        if ("disable".equals(req.getParameter("actionDisable"))) {
            for (Project project : projectList) {
                if (req.getParameter(project.getId().toString()) != null) {
                    if(!projectDAO.disable(projectDAO.getById(project.getId())))
                        Logger.info("Error in database");
                }
            }
        }

        if ("enable".equals(req.getParameter("actionEnable"))) {
            for (Project project : projectList) {
                if (req.getParameter(project.getId().toString()) != null) {
                    if(!projectDAO.enable(projectDAO.getById(project.getId())))
                        Logger.info("Error in database");
                }
            }
        }

        if ("edit".equals(req.getParameter("actionEdit"))) {
            String id = req.getParameter("proEditId");
            Integer id1 = Integer.valueOf(id);

            String name = req.getParameter("editProName");
            String owner = req.getParameter("editProOwner");

            String customer = req.getParameter("editCustomerSelect");
            Integer projectId = Integer.parseInt(customer.substring(0, customer.indexOf(".")));
            Customer customer1 = customerDAO.getById(projectId);

            Project project = new Project(id1, name, owner, customer1, false);

            projectDAO.update(project);
        }

        if ("add".equals(req.getParameter("actionAdd"))) {
            String name = req.getParameter("addProName");
            String owner = req.getParameter("addProOwner");

            String customer = req.getParameter("addCustomerSelect");
            Integer projectId = Integer.parseInt(customer.substring(0, customer.indexOf(".")));
            Customer customer1 = customerDAO.getById(projectId);

            Project project = new Project(0, name, owner, customer1, false);

            projectDAO.create(project);
        }

        projectList = projectDAO.GetAll();
        req.getSession().setAttribute("projectList", projectList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/project.jsp");
        requestDispatcher.forward(req, resp);
    }
}