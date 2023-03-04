package jdbc.dto;

public class Project {

    private Integer id;
    private String projectName;
    private String ownerName;
    private Customer customer;
    private Boolean isDisabled;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", customer=" + customer +
                '}';
    }

    public Project() {
    }

    public Project(Integer id, String projectName, String ownerName, Customer customer, Boolean isDisabled) {
        this.id = id;
        this.projectName = projectName;
        this.ownerName = ownerName;
        this.customer = customer;
        this.isDisabled = isDisabled;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
