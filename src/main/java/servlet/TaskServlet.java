package servlet;

import jdbc.dao.TaskTypeDAO;
import jdbc.dto.Customer;
import jdbc.dto.Employee;
import jdbc.dto.TaskType;
import org.example.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    TaskTypeDAO taskDAO = new TaskTypeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskType> taskList = taskDAO.GetAll();
        req.getSession().setAttribute("taskList", taskList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/task.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskType> taskList = taskDAO.GetAll();

        if ("disable".equals(req.getParameter("actionDisable"))) {
            for (TaskType task : taskList) {
                if (req.getParameter(task.getId().toString()) != null) {
                    if(!taskDAO.disable(taskDAO.getById(task.getId())))
                        Logger.info("Error in database");
                }
            }
        }

        if ("enable".equals(req.getParameter("actionEnable"))) {
            for (TaskType task : taskList) {
                if (req.getParameter(task.getId().toString()) != null) {
                    if(!taskDAO.enable(taskDAO.getById(task.getId())))
                        Logger.info("Error in database");
                }
            }
        }

        if ("edit".equals(req.getParameter("actionEdit"))) {
            String id = req.getParameter("taskEditId");
            Integer id1 = Integer.valueOf(id);

            String name = req.getParameter("editTask");
            TaskType task = new TaskType(id1, name, false);

            taskDAO.update(task);
        }

        if ("add".equals(req.getParameter("actionAdd"))) {
            String name = req.getParameter("addTask");
            TaskType task = new TaskType(0, name, false);

            taskDAO.create(task);
        }

        taskList = taskDAO.GetAll();
        req.getSession().setAttribute("taskList", taskList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/task.jsp");
        requestDispatcher.forward(req, resp);
    }
}
