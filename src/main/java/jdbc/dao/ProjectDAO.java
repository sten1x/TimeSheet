package jdbc.dao;

import jdbc.dto.Customer;
import jdbc.dto.Project;
import org.example.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IDAO<Project> {

    @Override
    public List<Project> GetAll() {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedstatement = connection.prepareStatement("SELECT project_id, project_name, project_owner_name, customer_info.customer_id, customer_name, customer_contact_number, project_info.disabled FROM project_info INNER JOIN customer_info ON customer_info.customer_id = project_info.customer_id")) {

            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt(1));
                project.setProjectName(resultSet.getString(2));
                project.setOwnerName(resultSet.getString(3));
                project.setCustomer(new Customer(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), false));
                project.setDisabled(resultSet.getInt(7) == 1);

                projects.add(project);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    @Override
    public Project getById(Integer id) {
        Project project = new Project();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT project_id, project_name, project_owner_name, customer_info.customer_id, customer_name, customer_contact_number FROM project_info INNER JOIN customer_info ON customer_info.customer_id = project_info.customer_id where project_id=?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                project.setId(resultSet.getInt(1));
                project.setProjectName(resultSet.getString(2));
                project.setOwnerName(resultSet.getString(3));
                project.setCustomer(new Customer(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), false));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public boolean update(Project object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE project_info SET project_name =?, project_owner_name =?, customer_id =? where project_id =?")) {

            preparedStatement.setString(1, object.getProjectName());
            preparedStatement.setString(2, object.getOwnerName());
            preparedStatement.setInt(3, object.getCustomer().getId());
            preparedStatement.setInt(4, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Project with id " + object.getId() + " has been updated");
                return true;
            } else {
                Logger.info("There is no project with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(Project object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO project_info(project_name, project_owner_name, customer_id) VALUES(?,?,?)")) {

            preparedStatement.setString(1, object.getProjectName());
            preparedStatement.setString(2, object.getOwnerName());
            preparedStatement.setInt(3, object.getCustomer().getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Project with id " + object.getId() + " has been added");
                return true;
            } else {
                Logger.info("Project with id " + object.getId() + "cannot be added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Project object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM project_info where project_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Project with id " + object.getId() + " has been deleted");
                return true;
            } else {
                Logger.info("There is no project with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean disable(final Project object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE project_info SET disabled=1 where project_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Project with id " + object.getId() + " has been disabled");
                return true;
            } else {
                Logger.info("There is no project with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean enable(final Project object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE project_info SET disabled=0 where project_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Project with id " + object.getId() + " has been enabled");
                return true;
            } else {
                Logger.info("There is no project with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}