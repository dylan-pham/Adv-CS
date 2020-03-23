public class Country {
    private String name;
    private String abbreviation;

    public Country(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int hashCode() {
        char first = Character.toLowerCase(abbreviation.charAt(0));
        char second = Character.toLowerCase(abbreviation.charAt(1));

        return (first - 23) * 100 + (second - 23);
    }

    public String toString() {
        return abbreviation + " - " + name; 
    }
}