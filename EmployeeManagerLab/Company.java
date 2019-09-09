public class Company extends Employee {
    private String companyName;
    private double salary;

    public Company(String name, String photoFile, String jobTitle, String companyName, double salary) {
        super(name, photoFile, jobTitle);
        this.companyName = companyName;
        this.salary = salary;
    }

    public String toString() {
        return super.toString() + "\nCompany: " + companyName;
    }

    public double getSalary() {
        return salary;
    }
}