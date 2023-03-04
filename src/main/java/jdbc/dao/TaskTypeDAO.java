package jdbc.dao;

import jdbc.dto.Customer;
import jdbc.dto.TaskType;
import org.example.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskTypeDAO implements IDAO<TaskType> {

    @Override
    public List<TaskType> GetAll() {
        List<TaskType> taskTypes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM task_type")) {

            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                TaskType taskType = new TaskType();
                taskType.setId(resultSet.getInt(1));
                taskType.setType(resultSet.getString(2));
                taskType.setDisabled(resultSet.getInt(3) == 1);

                taskTypes.add(taskType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskTypes;
    }

    @Override
    public TaskType getById(Integer id) {
        TaskType taskType = new TaskType();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from task_type where task_id=?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                taskType.setId(resultSet.getInt(1));
                taskType.setType(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskType;
    }

    @Override
    public boolean update(final TaskType object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task_type SET type =? where task_id =?")) {

            preparedStatement.setString(1, object.getType());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Task type with id " + object.getId() + " has been updated");
                return true;
            } else {
                Logger.info("There is no task type with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(final TaskType object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task_type(task) VALUES(?)")) {

            preparedStatement.setString(1, object.getType());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Task type " + object.getType() + " has been added");
                return true;
            } else {
                Logger.info("Task type " + object.getType() + " cannot be added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(final TaskType object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM task_type where task_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Status with ID " + object.getId() + " has been deleted");
                return true;
            } else {
                Logger.info("There is no status with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean disable(final TaskType object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task_type SET disabled=1 WHERE task_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Task with id " + object.getId() + " has been disabled");
                return true;
            } else {
                Logger.info("There is no task with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean enable(final TaskType object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task_type SET disabled=0 WHERE task_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Task with id " + object.getId() + " has been enabled");
                return true;
            } else {
                Logger.info("There is no task with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
