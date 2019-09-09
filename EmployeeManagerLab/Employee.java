import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Employee {
    private String name;
    private String photoFile;
    private String jobTitle;
    private Image profilePic;

    public Employee(String name, String photoFile, String jobTitle) {
        this.name = name;
        this.photoFile = photoFile;
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getName() {
        return name;
    }

    public abstract double getSalary();

    public void drawPhoto(Graphics g, int x, int y) {
        try {
            profilePic = ImageIO.read(new File(photoFile));
        } catch (IOException e) {
            e.printStackTrace();
	    }
        
        profilePic = profilePic.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        g.drawImage(profilePic, x, y, null);
    }

    public String toString() {
        return "Name: " + name + "\nJob: " + jobTitle + "\nSalary: $" + getSalary();
    }
}