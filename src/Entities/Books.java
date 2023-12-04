package Entities;

public class Books {

    private String id;
    private String name;
    private float price;
    private int quantity;
    private String pubID;
    private String status;

    public Books() {
    }

    public Books(String ID, String Name, float Price, int Quantity, String PubID, String Status) {
        this.id = ID;
        this.name = Name;
        this.price = Price;
        this.quantity = Quantity;
        this.pubID = PubID;
        this.status = Status;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float Price) {
        this.price = Price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

    public String getPubID() {
        return pubID;
    }

    public void setPubID(String PubID) {
        this.pubID = PubID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public double Subtotal() {
        return this.price * this.quantity;
    }

    @Override
    public String toString() {
        return "Book's ID: " + id + "\n"
                + "Book's Name: " + name + "\n"
                + "Book's Price: " + price + "\n"
                + "Book's Quantity: " + quantity + "\n"
                + "Book's Subtotal: " + Subtotal() + "\n"
                + "Publisher ID: " + pubID + "\n"
                + "Book's Status: " + status + "\n";
    }

}
