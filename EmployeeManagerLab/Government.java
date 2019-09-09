public class Government extends Employee {
    private String cityName;
    private Double salary;

    public Government(String name, String photoFile, String jobTitle, String cityName, double salary) {
        super(name, photoFile, jobTitle);
        this.cityName = cityName;
        this.salary = salary;
    }

    public String toString() {
        return super.toString() + "\nCity: " + cityName;
    }

    public double getSalary() {
        return salary;
    }
}