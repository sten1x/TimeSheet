package jdbc.dao;

import jdbc.dto.Status;
import org.example.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO implements IDAO<Status> {

    @Override
    public List<Status> GetAll() {
        List<Status> statuses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM result_status")) {

            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt(1));
                status.setStatus(resultSet.getString(2));

                statuses.add(status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statuses;
    }

    @Override
    public Status getById(Integer id) {
        Status status = new Status();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from result_status where status_id=?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                status.setId(resultSet.getInt(1));
                status.setStatus(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean update(final Status object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE result_status SET status =? where status_id =?")) {

            preparedStatement.setString(1, object.getStatus());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Status with id " + object.getId() + " has been updated");
                return true;
            } else {
                Logger.info("There is no status with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(final Status object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO result_status(status) VALUES(?)")) {

            preparedStatement.setString(1, object.getStatus());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Status " + object.getStatus() + " has been added");
                return true;
            } else {
                Logger.info("Status " + object.getStatus() + " cannot be added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(final Status object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM result_status where status_id =?")) {

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
}
