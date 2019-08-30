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
	private JTextField nameJTextField;
	private JTextField pinJTextField;
	private JTextField amountJTextField;
	private JTextArea textDisplayJTextArea;	
	private JButton loginJButton;
	private JButton logoutJButton;
	private JButton withdrawJButton;
	private JButton depositJButton;
	private Account a1;
	private Account a2;
	private Account a3;
	private Account a4;
	private Account a5;
	private ArrayList<Account> accounts;
	private Account currentAccount;	

	public Screen() {
		setLayout(null);
		setFocusable(true);

		a1 = new Account("Jennifer", 999.99, 1234);
		a2 = new Account("Jose", 500.01, 4321);
		a3 = new Account("Adam", 1.10, 5678);
		a4 = new Account("Sally", 5.20, 1357);
		a5 = new Account("Michael", 1000.00, 2468);

		accounts = new ArrayList<Account>();
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		accounts.add(a5);

		textDisplayJTextArea = new JTextArea("Welcome to the Bank");
		textDisplayJTextArea.setBounds(100, 100, 200, 100);
		this.add(textDisplayJTextArea);

		nameJTextField = new JTextField();
		nameJTextField.setBounds(300, 300, 100, 30);
		this.add(nameJTextField);

		pinJTextField = new JTextField();
		pinJTextField.setBounds(300, 350, 100, 30);
		this.add(pinJTextField);

		amountJTextField = new JTextField();
		amountJTextField.setBounds(300, 350, 100, 30);
		this.add(amountJTextField);
		amountJTextField.setVisible(false);
		
		loginJButton = new JButton("Login");
		loginJButton.setBounds(400, 300, 100, 100);
		this.add(loginJButton);
		loginJButton.addActionListener(this);

		logoutJButton = new JButton("Logout");
		logoutJButton.setBounds(700, 20, 100, 100);
		this.add(logoutJButton);
		logoutJButton.addActionListener(this);
		logoutJButton.setVisible(false);

		withdrawJButton = new JButton("Withdraw");
		withdrawJButton.setBounds(400, 350, 100, 50);
		this.add(withdrawJButton);
		withdrawJButton.addActionListener(this);
		withdrawJButton.setVisible(false);

		depositJButton = new JButton("Deposit");
		depositJButton.setBounds(400, 300, 100, 50);
		this.add(depositJButton);
		depositJButton.addActionListener(this);
		depositJButton.setVisible(false);

		currentAccount = null;
	}

	public Dimension getPreferredSize() {
        return new Dimension(800, 450);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 450);
	}

	public void attemptLogin(int pin, String name) {
		boolean loggedIn = false;
		for (int i = 0; i < accounts.size(); i++) {
			accounts.get(i).setAccess(pin, name);

			if (accounts.get(i).getAccess()) {
				textDisplayJTextArea.setText("Name: " + accounts.get(i).getName() + "\n" + "Balance: " + accounts.get(i).getBalance());
				loggedIn = true;
				currentAccount = accounts.get(i);
				
				pinJTextField.setText("");
				nameJTextField.setText("");
				
				logoutJButton.setVisible(true);
				withdrawJButton.setVisible(true);
				depositJButton.setVisible(true);
				loginJButton.setVisible(false);
				nameJTextField.setVisible(false);
				pinJTextField.setVisible(false);
				amountJTextField.setVisible(true);
			}
		}

		if (!loggedIn) {
			textDisplayJTextArea.setText("Check login info.");
		}
	}

	public void logout() {
		logoutJButton.setVisible(false);
		textDisplayJTextArea.setText("Welcome to the Bank");
		withdrawJButton.setVisible(false);
		depositJButton.setVisible(false);
		loginJButton.setVisible(true);
		nameJTextField.setVisible(true);
		pinJTextField.setVisible(true);
		amountJTextField.setVisible(false);
		currentAccount.logout();
		currentAccount = null;
	}

	public void updateAmount() {
		textDisplayJTextArea.setText("Name: " + currentAccount.getName() + "\n" + "Balance: " + currentAccount.getBalance());
	}

	public void transaction(String type, double amount) {
		if (type.equals("deposit")) {
			currentAccount.deposit(amount);
		} else if (type.equals("withdraw")) {
			currentAccount.withdraw(amount);
		}

		updateAmount();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginJButton) {
			try {
				String name = nameJTextField.getText();
				int pin = Integer.parseInt(pinJTextField.getText());

				attemptLogin(pin, name);
			} catch (Exception exception) {
				;
			}
		}

		if (e.getSource() == logoutJButton) {
			logout();
		}

		if (e.getSource() == withdrawJButton) {
			try {
				double amount = Double.parseDouble(amountJTextField.getText());
				transaction("withdraw", amount);
				amountJTextField.setText("");
			} catch (Exception exception) {
				;
			}
		}

		if (e.getSource() == depositJButton) {
			try {
				double amount = Double.parseDouble(amountJTextField.getText());
				transaction("deposit", amount);
				amountJTextField.setText("");
			} catch (Exception exception) {
				;
			}
		}
    }
}