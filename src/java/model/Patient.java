
package model;

public class Patient {
    private String nic;
    private String name;
    private String phone;
    private String password;
    
    // Constructor
    public Patient(String nic, String name, String phone, String password) {
        this.nic = nic;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

