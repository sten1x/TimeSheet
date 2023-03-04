package jdbc.dao;

import jdbc.dto.TimeTracking;
import org.example.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeTrackingDAO implements IDAO<TimeTracking> {
    @Override
    public boolean update(TimeTracking object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE time_tracking SET employee_id =?, project_id =?, task_id =?, worked_hours =?, start_date =?, end_date =?, comment =?, status_id =? WHERE time_tracking_id =?")) {

            preparedStatement.setInt(1, object.getEmployee().getId());
            preparedStatement.setInt(2, object.getProject().getId());
            preparedStatement.setInt(3, object.getTask().getId());
            preparedStatement.setInt(4, object.getWorkHours());
            preparedStatement.setString(5, object.getStartDate().toString());
            preparedStatement.setString(6, object.getEndDate().toString());
            preparedStatement.setString(7, object.getComment());
            preparedStatement.setInt(8, object.getStatus().getId());
            preparedStatement.setInt(9, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Time tracking with id " + object.getId() + " has been updated");
                return true;
            } else {
                Logger.info("There is no time tracking with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<TimeTracking> GetAll() {
        List<TimeTracking> timeTrackers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM time_tracking")) {

            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                TimeTracking timeTracking = new TimeTracking();
                timeTracking.setEmployee(new EmployeeDAO().getById(resultSet.getInt(1)));
                timeTracking.setProject(new ProjectDAO().getById(resultSet.getInt(2)));
                timeTracking.setTask(new TaskTypeDAO().getById(resultSet.getInt(3)));
                timeTracking.setWorkHours(resultSet.getInt(4));
                timeTracking.setStartDate(LocalDate.parse(resultSet.getString(5)));
                timeTracking.setEndDate(LocalDate.parse(resultSet.getString(6)));
                timeTracking.setComment(resultSet.getString(7));
                timeTracking.setStatus(new StatusDAO().getById(resultSet.getInt(8)));
                timeTracking.setId(resultSet.getInt(9));

                timeTrackers.add(timeTracking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTrackers;
    }

    @Override
    public TimeTracking getById(Integer id) {
        TimeTracking timeTracking = new TimeTracking();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM time_tracking where time_tracking_id=?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                timeTracking.setEmployee(new EmployeeDAO().getById(resultSet.getInt(1)));
                timeTracking.setProject(new ProjectDAO().getById(resultSet.getInt(2)));
                timeTracking.setTask(new TaskTypeDAO().getById(resultSet.getInt(3)));
                timeTracking.setWorkHours(resultSet.getInt(4));
                timeTracking.setStartDate(LocalDate.parse(resultSet.getString(5)));
                timeTracking.setEndDate(LocalDate.parse(resultSet.getString(6)));
                timeTracking.setComment(resultSet.getString(7));
                timeTracking.setStatus(new StatusDAO().getById(resultSet.getInt(8)));
                timeTracking.setId(resultSet.getInt(9));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTracking;
    }

    @Override
    public boolean create(TimeTracking object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO time_tracking(employee_id, project_id, task_id, worked_hours, start_date, end_date, comment, status_id) VALUES(?,?,?,?,?,?,?,?)")) {

            preparedStatement.setInt(1, object.getEmployee().getId());
            preparedStatement.setInt(2, object.getProject().getId());
            preparedStatement.setInt(3, object.getTask().getId());
            preparedStatement.setInt(4, object.getWorkHours());
            preparedStatement.setString(5, object.getStartDate().toString());
            preparedStatement.setString(6, object.getEndDate().toString());
            preparedStatement.setString(7, object.getComment());
            preparedStatement.setInt(8, object.getStatus().getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Time tracking with id " + object.getId() + " has been added");
                return true;
            } else {
                Logger.info("Time tracking from project " + object.getProject().getProjectName() + " and comment " + object.getComment() + "cannot be added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(TimeTracking object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM time_tracking WHERE time_tracking_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Time tracking with id " + object.getId() + " has been deleted");
                return true;
            } else {
                Logger.info("There is no time tracking with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
