public class Education {
    private String schoolName;
    private int graduationDate;

    public Education(String schoolName, int graduationDate) {
        this.schoolName = schoolName;
        this.graduationDate = graduationDate;
    }

    public int getGraduationDate() {
        return graduationDate;
    }

    public String toString() {
        return "\nSchool Name: " + schoolName + "\nGraduation Date: " + graduationDate + "\n";
    }
}