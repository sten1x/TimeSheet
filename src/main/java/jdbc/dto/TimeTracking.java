package jdbc.dto;

import org.example.Logger;

import java.time.LocalDate;

public class TimeTracking {
    private Integer id, workHours;
    private LocalDate startDate, endDate;
    private String comment;
    private Employee employee;
    private Project project;
    private Status status;
    private TaskType task;

    public TimeTracking() {
    }

    public TimeTracking(Integer id, Integer workHours, LocalDate startDate, LocalDate endDate, String comment, Employee employee, Project project, Status status, TaskType task) {
        this.id = id;
        this.workHours = workHours;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comment = comment;
        this.employee = employee;
        this.project = project;
        this.status = status;
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TaskType getTask() {
        return task;
    }

    public void setTask(TaskType task) {
        this.task = task;
    }
}