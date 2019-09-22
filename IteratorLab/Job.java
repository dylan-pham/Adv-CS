public class Job {
    private String jobTitle;
    private String companyName;
    private String startDate;
    private String endDate;
    private String jobDescription;

    public Job(String jobTitle, String companyName, String startDate, String endDate, String jobDescription) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobDescription = jobDescription;
    }

    public String getEndDate() {
        return endDate;
    }

    public String toString() {
        return "\nJob Title: " + jobTitle + "\nCompany Name: " + companyName + "\nStart Date: " + startDate + "\nEnd Date: " + endDate + "\nDescription: " + jobDescription + "\n";
    }
}