package jdbc.dto;

public class TaskType {
    private Integer id;
    private String type;
    private Boolean isDisabled;

    public TaskType() {
    }

    public TaskType(Integer id, String type, Boolean isDisabled) {
        this.id = id;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TaskType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
