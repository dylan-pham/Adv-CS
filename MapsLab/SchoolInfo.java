public class SchoolInfo {
    private String school;
    private String[] schedule;

    public SchoolInfo(String school, String[] schedule) {
        this.school = school;
        this.schedule = schedule;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchedule() {
        String s = "";
        for (int i = 0; i < schedule.length; i++) {
            s += schedule[i] + " ";

            System.out.println(schedule[i]);
        }

        return s;
    }

    public void addToSchedule(String _class) {
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i].equals("")) {
                schedule[i] = _class;
                break;
            }
        }
    }

    public void removeFromSchedule(String _class) {
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i].equals(_class)) {
                schedule[i] = "";
                break;
            }
        }
    }
}