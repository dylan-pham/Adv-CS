import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent; 
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
 
public class Screen extends JPanel implements MouseListener, ActionListener, ListSelectionListener {
    private BinarySearchTree<Account> accounts;
    private DefaultListModel listModel;
    private JList<String> choiceList;
    private String view;
    private JTextArea accountDataTextArea;
    private JLabel viewLabel;
    private JButton changeViewButton;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel pinLabel;
    private JLabel balanceLabel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField pinTextField;
    private JTextField balanceTextField;
    private JButton addButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton changeFirstNameButton;
    private JButton changeLastNameButton;
    private JButton changePinButton;
    private JButton changeBalanceButton;
    private JButton logInButton;
    private JButton logOutButton;
    private JLabel passCounter;
    private Account selectedAccount;
    private JButton depositButton;
    private JButton withdrawButton;
    private JTextField amountTextField;
    private JLabel amountLabel;
    
    public Screen() {
        accounts = new BinarySearchTree<Account>();
        
        listModel = new DefaultListModel();
        choiceList = new JList<String>(listModel);
        add(choiceList);
        choiceList.addListSelectionListener(this);

        // adding accounts to list model and binary tree
        try {
            Scanner scan = new Scanner(new FileReader("names.txt"));
            while (scan.hasNextLine()){
                String[] temp = scan.nextLine().split(",");
                String lastName = temp[0];
                String firstName = temp[1];
                int pin = (int)(Math.random() * 9000) + 1000;
                double accountBalance = Math.random() * 100000;
                Account account = new Account(lastName, firstName, pin, accountBalance);
                accounts.add(account);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] names = accounts.toString().split("\n");
        for (int i = 0; i < names.length; i+=3) {
            listModel.addElement(names[i]);
        }

        JScrollPane scrollPane = new JScrollPane(choiceList); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 10, 200, 300);
        this.add(scrollPane);

        view = "admin view";

        accountDataTextArea = new JTextArea();
        accountDataTextArea.setBounds(240, 10, 300, 300);
        this.add(accountDataTextArea);

        viewLabel = new JLabel(view);
        viewLabel.setBounds(680, 10, 100, 30);
        this.add(viewLabel);

        changeViewButton = new JButton("change view");
        changeViewButton.setBounds(670, 40, 100, 20);
        changeViewButton.addActionListener(this);
        this.add(changeViewButton);

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(10, 330, 100, 20);
        this.add(firstNameLabel);

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(10, 360, 100, 20);
        this.add(lastNameLabel);

        pinLabel = new JLabel("Pin");
        pinLabel.setBounds(10, 390, 100, 20);
        this.add(pinLabel);

        balanceLabel = new JLabel("Balance");
        balanceLabel.setBounds(10, 420, 100, 20);
        this.add(balanceLabel);

        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(100, 330, 100, 20);
        this.add(firstNameTextField);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(100, 360, 100, 20);
        this.add(lastNameTextField);

        pinTextField = new JTextField();
        pinTextField.setBounds(100, 390, 100, 20);
        this.add(pinTextField);

        balanceTextField = new JTextField();
        balanceTextField.setBounds(100, 420, 100, 20);
        this.add(balanceTextField);

        addButton = new JButton("Add");
        addButton.setBounds(10, 460, 100, 20);
        addButton.addActionListener(this);
        this.add(addButton);
        
        searchButton = new JButton("Search");
        searchButton.setBounds(130, 460, 100, 20);
        searchButton.addActionListener(this);
        this.add(searchButton);
        
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(250, 460, 100, 20);
        deleteButton.addActionListener(this);
        this.add(deleteButton);
        
        changeFirstNameButton = new JButton("Change First Name");
        changeFirstNameButton.setBounds(370, 460, 100, 20);
        changeFirstNameButton.addActionListener(this);
        this.add(changeFirstNameButton);

        changeLastNameButton = new JButton("Change Last Name");
        changeLastNameButton.setBounds(490, 460, 100, 20);
        changeLastNameButton.addActionListener(this);
        this.add(changeLastNameButton);

        changePinButton = new JButton("Change Pin");
        changePinButton.setBounds(610, 460, 100, 20);
        changePinButton.addActionListener(this);
        this.add(changePinButton);

        changeBalanceButton = new JButton("Change Balance");
        changeBalanceButton.setBounds(730, 460, 100, 20);
        changeBalanceButton.addActionListener(this);
        this.add(changeBalanceButton);
        
        logInButton = new JButton("Log In");
        logInButton.setBounds(10, 460, 100, 20);
        logInButton.addActionListener(this);
        this.add(logInButton);
        logInButton.setVisible(false);

        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(10, 460, 100, 20);
        logOutButton.addActionListener(this);
        this.add(logOutButton);
        logOutButton.setVisible(false);

        passCounter = new JLabel("Passes: ");
        passCounter.setBounds(680, 80, 100, 20);
        this.add(passCounter);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(130, 460, 100, 20);
        depositButton.addActionListener(this);
        this.add(depositButton);
        depositButton.setVisible(false);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(250, 460, 100, 20);
        withdrawButton.addActionListener(this);
        this.add(withdrawButton);
        withdrawButton.setVisible(false);

        amountTextField = new JTextField();
        amountTextField.setBounds(100, 420, 100, 20);
        this.add(amountTextField);
        amountTextField.setVisible(false);

        amountLabel = new JLabel("Amount");
        amountLabel.setBounds(10, 420, 100, 20);
        this.add(amountLabel);
        amountLabel.setVisible(false);;

        this.setLayout(null);
        this.setFocusable(true);
        addMouseListener(this);
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(850, 500);
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeViewButton) {
            if (view.equals("admin view")) {
                view = "customer view";
                logInButton.setVisible(true);
                addButton.setVisible(false);
                searchButton.setVisible(false);
                deleteButton.setVisible(false);
                changeFirstNameButton.setVisible(false);
                choiceList.setVisible(false);
                accountDataTextArea.setText("");
                changeBalanceButton.setVisible(false);
                changeFirstNameButton.setVisible(false);
                changeLastNameButton.setVisible(false);
                changePinButton.setVisible(false);
                balanceLabel.setVisible(false);
                balanceTextField.setVisible(false);
            } else {
                view = "admin view";
                choiceList.setVisible(true);
                logInButton.setVisible(false);
                addButton.setVisible(true);
                searchButton.setVisible(true);
                deleteButton.setVisible(true);
                changeFirstNameButton.setVisible(true);
                choiceList.setVisible(true);
                accountDataTextArea.setText("");
                changeBalanceButton.setVisible(true);
                changeFirstNameButton.setVisible(true);
                changeLastNameButton.setVisible(true);
                changePinButton.setVisible(true);
                balanceLabel.setVisible(true);
                balanceTextField.setVisible(true);
                amountLabel.setVisible(false);
                amountTextField.setVisible(false);
                depositButton.setVisible(false);
                withdrawButton.setVisible(false);
            }

            viewLabel.setText(view);
        } else if (e.getSource() == addButton) {
            try {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                int pin = Integer.parseInt(pinTextField.getText());
                double balance = Double.parseDouble(balanceTextField.getText());

                // listModel.addElement(lastName + ", " + firstName);
                accounts.add(new Account(lastName, firstName, pin, balance));
                passCounter.setText("Passes: " + accounts.getPassCounter());
                accounts.resetPassCounter();
            } catch (Exception err) {}
        } else if (e.getSource() == searchButton) {
            try {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                Account account = (Account)accounts.findData(new Account(lastName, firstName, 0, 0.0)); // filler data for pin and balance (only checking first and last name)
                
                selectedAccount = account;
                accountDataTextArea.setText(account.toString());
                passCounter.setText("Passes: " + accounts.getPassCounter());
                accounts.resetPassCounter();
            } catch (Exception err) {}
        } else if (e.getSource() == deleteButton) {
            try {
                accounts.remove(selectedAccount);
                listModel.removeElement(selectedAccount.getFullName());

                accountDataTextArea.setText("");
            } catch (Exception err) {}
        } else if (e.getSource() == changeFirstNameButton) {
            try {
                int index = listModel.indexOf(selectedAccount.getFullName());
                String newFirstName = firstNameTextField.getText();
                selectedAccount.setFirstName(newFirstName);

                listModel.set(index, selectedAccount.getFullName());
                accountDataTextArea.setText(selectedAccount.toString());
            } catch (Exception err) {}
        } else if (e.getSource() == changeLastNameButton) {
            try {
                int index = listModel.indexOf(selectedAccount.getFullName());
                String newLastName = lastNameTextField.getText();
                selectedAccount.setLastName(newLastName);

                listModel.set(index, selectedAccount.getFullName());
                accountDataTextArea.setText(selectedAccount.toString());
            } catch (Exception err) {}
        } else if (e.getSource() == changePinButton) {
            try {
                Account newAccount = selectedAccount;
                int newPin = Integer.parseInt(pinTextField.getText());
                selectedAccount.setPin(newPin);

                accountDataTextArea.setText(newAccount.toString());
            } catch (Exception err) {}
        } else if (e.getSource() == changeBalanceButton) {
            try {
                Account newAccount = selectedAccount;
                double newBalance = Double.parseDouble(balanceTextField.getText());
                selectedAccount.setAccountBalance(newBalance);

                accountDataTextArea.setText(newAccount.toString());
            } catch (Exception err) {}
        } else if (e.getSource() == logInButton) {
            try {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                int pin = Integer.parseInt(pinTextField.getText());
                Account account = (Account)accounts.findData(new Account(lastName, firstName, 0, 0.0)); // filler data for pin and balance (only checking first and last name)
                selectedAccount = account;
                passCounter.setText("Passes: " + accounts.getPassCounter());
                accounts.resetPassCounter();

                if (account.getPin() == pin) {
                    logInButton.setVisible(false);
                    logOutButton.setVisible(true);
                    accountDataTextArea.setText(account.toString());
                    changeFirstNameButton.setVisible(true);
                    changeLastNameButton.setVisible(true);
                    changePinButton.setVisible(true);
                    depositButton.setVisible(true);
                    withdrawButton.setVisible(true);
                    amountLabel.setVisible(true);
                    amountTextField.setVisible(true);
                }
            } catch (Exception err) {}
        } else if (e.getSource() == depositButton) {
            selectedAccount.deposit(Double.parseDouble(amountTextField.getText()));
            accountDataTextArea.setText(selectedAccount.toString());
        } else if (e.getSource() == withdrawButton) {
            selectedAccount.withdraw(Double.parseDouble(amountTextField.getText()));
            accountDataTextArea.setText(selectedAccount.toString());
        } else if (e.getSource() == logOutButton) {
            accountDataTextArea.setText("");
            logOutButton.setVisible(false);
            logInButton.setVisible(true);
            changeFirstNameButton.setVisible(false);
            changeLastNameButton.setVisible(false);
            changePinButton.setVisible(false);
            depositButton.setVisible(false);
            withdrawButton.setVisible(false);
            amountLabel.setVisible(false);
            amountTextField.setVisible(false);
        }
    }

    public void valueChanged(ListSelectionEvent e) { 
        String[] s = choiceList.getSelectedValue().split(", ");
        Account account = (Account)accounts.findData(new Account(s[0], s[1], 0, 0.0)); // filler data for pin and balance (only checking first and last name)
        selectedAccount = account;

        accountDataTextArea.setText(account.toString());
    }

    // for testing purposes
    public void mousePressed(MouseEvent e) {
        System.out.print("x: " + e.getX());
        System.out.println(" || y: " + e.getY());
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}