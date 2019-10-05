public class Item implements Comparable {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Item: " + name + " | Price: " + price;
    }

    @Override
    public int compareTo(Object o) {
        Item i = (Item)o;

        if (name.equals(i.getName()) && price == i.getPrice()) {
            return 0;
        } else if (name.compareTo(i.getName()) == 0) {
            return (int)price-(int)i.getPrice();
        } else {
            return name.compareTo(i.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        Item i = (Item)o;

        if (price == i.getPrice() && name.equals(i.getName())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int total = 0;

        for (int i = name.length()-1; i > -1; i--) {
            char letter = name.charAt(i);

            total += letter * Math.pow(26, i);
        }

        return total;
    }
}