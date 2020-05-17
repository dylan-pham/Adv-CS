import javax.swing.JFrame;
import java.io.*;

public class Client {

    public static void main(String args[]) throws IOException {

        JFrame frame = new JFrame("Pixel Art");

        Screen sc = new Screen();
        frame.add(sc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

		sc.poll();
    }
}
