import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener {
	private JTextField name;
	private JTextField pin;
	private JTextArea welcome;	

	public Screen() {
		setLayout(null);
		setFocusable(true);

		Account a1 = new Account("Jennifer", 999.99, 1234);
		Account a2 = new Account("Jose", 500.01, 4321);
		Account a3 = new Account("Adam", 1.10, 5678);
		Account a4 = new Account("Sally", 5.20, 1357);
		Account a5 = new Account("Michael", 1000.00, 2468);

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		accounts.add(a5);

		welcome = new JTextArea("Welcome to the Bank");
		welcome.setBounds(100, 100, 200, 100);
		this.add(welcome);

		name = new JTextField();
		name.setBounds(0, 0, 100, 30);
		this.add(name);
	}

	public Dimension getPreferredSize() {
        return new Dimension(800, 450);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 450);
	}

	public void actionPerformed(ActionEvent e) {
    }
}
