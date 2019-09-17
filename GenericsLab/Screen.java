import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel {
    private ArrayList<Pair<Student, Schedule>> pairs;
    private JTextArea bio;

    public Screen() {
        setLayout(null);
        setFocusable(true);

        pairs = new ArrayList<Pair<Student, Schedule>>();

        Schedule s1 = new Schedule();
        s1.addClass(1, "history");

        pairs.add(new Pair<Student, Schedule>(new Student("Joe", "images/joe.jpg"), s1));

        bio = new JTextArea(pairs.get(0).getKey().toString());
        bio.setBounds(250, 100, 70, 20);
        bio.setEditable(false);
        this.add(bio);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 700);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

		pairs.get(0).getKey().drawPhoto(g, 100, 100);
    }
}