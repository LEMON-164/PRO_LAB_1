package Entities;

public class Publishers {

    private String id;
    private String name;
    private String phone;

    public Publishers() {
    }

    public Publishers(String ID, String Name, String Phone) {
        this.id = ID;
        this.name = Name;
        this.phone = Phone;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String Phone) {
        this.phone = Phone;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + "Name=" + name + ", " + "Phone: " + phone;
    }
}
