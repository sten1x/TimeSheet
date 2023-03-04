package servlet;

import jdbc.dao.*;
import jdbc.dto.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    TimeTrackingDAO timeTrackingDAO = new TimeTrackingDAO();
    TaskTypeDAO taskTypeDAO = new TaskTypeDAO();
    StatusDAO statusDAO = new StatusDAO();
    EmployeeDAO employeeDAO = new EmployeeDAO();
    ProjectDAO projectDAO = new ProjectDAO();
    CustomerDAO customerDAO = new CustomerDAO();

    List<TimeTracking> timeTrackingList = timeTrackingDAO.GetAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskType> taskList = taskTypeDAO.GetAll();
        List<Status> statusList = statusDAO.GetAll();
        List<Employee> employeeList = employeeDAO.GetAll();
        List<Project> projectList = projectDAO.GetAll();
        List<Customer> customerList = customerDAO.GetAll();

        req.getSession().setAttribute("timeTrackingList", timeTrackingList);
        req.getSession().setAttribute("taskList", taskList);
        req.getSession().setAttribute("statusList", statusList);
        req.getSession().setAttribute("employeeList", employeeList);
        req.getSession().setAttribute("projectList", projectList);
        req.getSession().setAttribute("customerList", customerList);

        if("german".equals(req.getParameter("actionGerman"))){
            req.getSession().setAttribute("lang", "de");
        }

        if("english".equals(req.getParameter("actionEnglish"))){
            req.getSession().setAttribute("lang", "en");
        }

        if("romanian".equals(req.getParameter("actionRomanian"))){
            req.getSession().setAttribute("lang", "ro");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("add".equals(req.getParameter("actionAdd"))) {
            Employee employee = employeeDAO.getById((Integer) req.getSession().getAttribute("employeeId"));

            String project = req.getParameter("addProjectSelect");
            Integer projectId = Integer.parseInt(project.substring(0, project.indexOf(".")));
            Project project1 = projectDAO.getById(projectId);

            String task = req.getParameter("addTaskSelect");
            Integer taskId = Integer.parseInt(task.substring(0, task.indexOf(".")));
            TaskType task1 = taskTypeDAO.getById(taskId);

            String status = req.getParameter("addStatusSelect");
            Integer statusId = Integer.parseInt(status.substring(0, status.indexOf(".")));
            Status status1 = statusDAO.getById(statusId);

            String workHours = req.getParameter("addWorkHours");
            Integer hours = Integer.parseInt(workHours);

            LocalDate startDate = LocalDate.parse(req.getParameter("addStartDate"));

            LocalDate endDate = LocalDate.parse(req.getParameter("addEndDate"));

            String comment = req.getParameter("addComment");

            TimeTracking t = new TimeTracking(0, hours, startDate, endDate, comment, employee, project1, status1, task1);
            timeTrackingDAO.create(t);
        }

        if("edit".equals(req.getParameter("actionEdit"))) {
            String id = req.getParameter("tEditId");
            Integer id1 = Integer.valueOf(id);

            Employee employee = employeeDAO.getById((Integer) req.getSession().getAttribute("employeeId"));

            String project = req.getParameter("editProjectSelect");
            Integer projectId = Integer.parseInt(project.substring(0, project.indexOf(".")));
            Project project1 = projectDAO.getById(projectId);

            String task = req.getParameter("editTaskSelect");
            Integer taskId = Integer.parseInt(task.substring(0, task.indexOf(".")));
            TaskType task1 = taskTypeDAO.getById(taskId);

            String status = req.getParameter("editStatusSelect");
            Integer statusId = Integer.parseInt(status.substring(0, status.indexOf(".")));
            Status status1 = statusDAO.getById(statusId);

            String workHours = req.getParameter("editWorkHours");
            Integer hours = Integer.parseInt(workHours);

            LocalDate startDate = LocalDate.parse(req.getParameter("editStartDate"));

            LocalDate endDate = LocalDate.parse(req.getParameter("editEndDate"));

            String comment = req.getParameter("editComment");

            TimeTracking t = new TimeTracking(id1, hours, startDate, endDate, comment, employee, project1, status1, task1);
            timeTrackingDAO.update(t);
        }

        if ("delete".equals(req.getParameter("actionDelete"))) {
            for (TimeTracking timeTracking : timeTrackingList) {
                if (req.getParameter(timeTracking.getId().toString()) != null) {
                    timeTrackingDAO.delete(timeTrackingDAO.getById(timeTracking.getId()));
                }
            }
        }

        List<TimeTracking> timeTrackingList = timeTrackingDAO.GetAll();
        req.getSession().setAttribute("timeTrackingList", timeTrackingList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


}

