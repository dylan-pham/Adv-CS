import java.io.Serializable;

public class RGB implements Serializable {
	private int red;
	private int green;
	private int blue;

	public RGB(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
}
