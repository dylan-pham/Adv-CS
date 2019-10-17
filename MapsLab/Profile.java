public class Profile implements Comparable {
    private String firstName;
    private String lastName;
    private int birthYear;
    private String schedule;

    public Profile(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public String toString() {
        return "Name: " + lastName + ", " + firstName + " | Birth Year: " + birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public boolean equals(Object o) {
        Profile p = (Profile)o;

        return hashCode() == p.hashCode();
    }

    @Override
    public int hashCode() {
        return 31 * (firstName.charAt(0)) + 17 * (lastName.charAt(0)) + (birthYear);
    }   
    
    @Override
    public int compareTo(Object o) {
        Profile p = (Profile)o;
        
        if (lastName.equals(p.getLastName()) && firstName.equals(p.getFirstName())) {
            return birthYear - p.getBirthYear();
        } else if (lastName.equals(p.getLastName())) {
            return firstName.compareTo(p.getFirstName());
        }

        return lastName.compareTo(p.getLastName());
    }
}