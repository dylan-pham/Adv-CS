public class ShoppingCart<K,V> {
    private K item;
    private V quantity;
    private Item i;
    private int q;

    public ShoppingCart(K item, V quantity) {
        this.item = item;
        this.quantity = quantity;

        i = (Item)item;
        q = (Integer)quantity;
    }

    public Item getItem() {
        return i;
    }

    public int getQuantity() {
        return q;
    }

    public double getPrice() {
        return i.getPrice() * q;
    }

    public String toString() {
        return i.toString() + " | Quantity: " + q;
    }
}