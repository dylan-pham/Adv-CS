public class Item {
    private String name;
    private double price;
    private int timestamp;
    private int quantity;

    public Item(String name, double price, int timestamp) {
        this.name = name;
        this.price = price;
        this.timestamp = timestamp;
        this.quantity = 1;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Name: " + name + " | Price: $" + price + " | Quantity: " + quantity + " | Timestamp: " + timestamp;
    }

    public boolean equals(Object o) {
        Item i = (Item)o;
        return name.equals(i.getName()) && price == i.getPrice();
    }
}