package jdbc.dto;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer employeeId;
    private Boolean isAdmin;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", employeeId=" + employeeId +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public User() {
    }

    public User(Integer id, String username, String password, Integer employeeId, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
