package jdbc.dto;

public class Customer {

    private Integer id;
    private String name;
    private String phone;
    private Boolean isDisabled;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Customer() {
    }

    public Customer(Integer id, String name, String phone, Boolean isDisabled) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
