public class Car {
    private String make;
    private String model;
    private int year;
    private double price;

    public Car(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public int hashCode() {
        char first = Character.toLowerCase(make.charAt(0));
        char second = Character.toLowerCase(make.charAt(1));
        char third = Character.toLowerCase(make.charAt(2));

        return (first - 23) * 10000 + (second - 23) * 100 + (third - 23);
    }

    public String getMake() {
        return make;
    }

    public String toString() {
        return "Make: " + make + " | Model: " + model + " | Year: " + year + " | Price: $" + price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}