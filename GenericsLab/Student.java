import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Student {
    private String name;
    private String imageFile;
    private Image profilePic;

    public Student(String name, String imageFile) {
        this.name = name;
        this.imageFile = imageFile;
    }

    public void drawStudent(Graphics g, int x, int y) {

    }

    public String toString() {
        return "Name: " + name;
    }

    public void drawPhoto(Graphics g, int x, int y) {
        try {
            profilePic = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
	    }
        
        profilePic = profilePic.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        g.drawImage(profilePic, x, y, null);
    }
}