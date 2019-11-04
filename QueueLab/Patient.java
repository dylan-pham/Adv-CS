import java.util.Map;
import java.util.HashMap;

public class Patient implements Comparable<Patient> {
    private String name;
    private String illnessDescription;
    private int medicalPriority;
    private int ageGroup;
    private int timestamp;
    private String doctorsNote;
    private boolean discharged;
    private Map<Integer, String> m1;
    private Map<Integer, String> m2;

    public Patient(String name, String illnessDescription, int medicalPriority, int ageGroup, int timestamp) {
        this.name = name;
        this.illnessDescription = illnessDescription;
        this.medicalPriority = medicalPriority;
        this.ageGroup = ageGroup;
        this.timestamp = timestamp;
        this.discharged = false;
        
        m1 = new HashMap<Integer, String>();
        m1.put(1, "high");
        m1.put(2, "medium");
        m1.put(3, "low");

        m2 = new HashMap<Integer, String>();
        m2.put(1, "children");
        m2.put(2, "adult");
    }

    public Patient() {}

    public int getMedicalPriority() {
        return medicalPriority;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setDoctorsNote(String doctorsNote) {
        this.doctorsNote = doctorsNote;
    }

    public String getName() {
        return name;
    }

    public void setIllnessDescription(String illnessDescription) {
        this.illnessDescription = illnessDescription;
    }

    public void setMedicalPriority(int medicalPriority) {
        this.medicalPriority = medicalPriority;
    }

    public String toString() {
        if (!discharged)
            return "Name: " + name + " | Illness: " + illnessDescription + " | Priority: " + m1.get(medicalPriority) + " | Age Group: " + m2.get(ageGroup) + " | Timestamp: " + timestamp;

        return "Name: " + name + " | Doctor's Note: " + doctorsNote;
    }

    public int compareTo(Patient p) {
        // ordered by medical priority, then age group, and then timestamp
        
        if (medicalPriority == p.getMedicalPriority()) {
            if (ageGroup == p.getAgeGroup()) {
                return timestamp - p.getTimestamp();
            } else {
                return ageGroup - p.getAgeGroup();
            }
        }

        return medicalPriority - p.getMedicalPriority();
    }

    public boolean equals(Object o) {
        Patient p = (Patient)o;

        if (name.equals(p.getName()) && ageGroup == p.getAgeGroup() && timestamp == p.getTimestamp()) {
            return true;
        }

        return false;
    }
}