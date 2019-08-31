import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Account {
	private String name;
	private double balance;
	private int pin;
	private boolean access;
	private String pathToImage;

	public Account(String name, double balance, int pin, String pathToImage) {
		this.name = name;
		this.balance = balance;
		this.pin = pin;
		this.pathToImage = pathToImage;
	}
 
	public String getName() {
		return name;
	}

	public Double getBalance() {
		if (access) {
			return Math.round(balance * 100.0) / 100.0;
		}

		return 0.0;
	}

	public boolean getAccess() {
		return access;
	}

	public void setAccess(int pin, String name) {
		if (this.pin == pin && this.name.equals(name)) {
			access = true;
		}
	}

	public void withdraw(double amount) {
		if (access && balance - amount >= 0) {
			balance -= amount;
		}
	}

	public void deposit(double amount) {
		if (access) {
			balance += amount;
		}
	}

	public void logout() {
		access = false;
	}

	public Image getProfilePic() {
		Image profilePic = null;

		try {
            profilePic = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            e.printStackTrace();
		}

		return profilePic;
	}
}
