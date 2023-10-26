package be.vinci.classes;

public class OrderLine {

    private String item;
    private int quantity;
    private double price;

    public OrderLine(String item, int quantity, double price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) { this.price = price; }
}
