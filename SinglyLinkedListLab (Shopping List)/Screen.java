import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import java.util.*;

public class Screen extends JPanel implements ActionListener {
    private SLList items;
    private int timestamp;
    private double totalPrice;
    private String sortingAlgo;

    private JLabel totalPriceLabel;
    private JTextArea itemListTextArea;
    private JScrollPane itemListScrollPane;
    private JLabel itemNameLabel;
    private JLabel itemPriceLabel;
    private JTextField itemNameTextField;
    private JTextField itemPriceTextField;
    private JButton addButton;
    private JButton removeButton;
    private JButton sortByTimeButton;
    private JButton sortByNameButton;
    private JButton sortByPriceButton;
    
    public Screen() {
        items = new SLList();
        items.add(new Item("eggs", 2.99, 0));
        items.add(new Item("bread", 5.99, 1));
        items.add(new Item("apples", 1.99, 2));

        timestamp = 2;

        totalPrice = 0;

        sortingAlgo = "TIME";

        totalPriceLabel = new JLabel("Total Price: $10.97");
        totalPriceLabel.setBounds(20, 520, 400, 20);
        this.add(totalPriceLabel);

        itemListTextArea = new JTextArea(400, 500);
        itemListTextArea.setEditable(false);
        this.add(itemListTextArea);
        
        itemListScrollPane = new JScrollPane(itemListTextArea); 
        itemListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemListScrollPane.setBounds(20, 20, 400, 500);
        this.add(itemListScrollPane);

        itemNameLabel = new JLabel("Name: ");
        itemNameLabel.setBounds(440, 20, 200, 20);
        this.add(itemNameLabel);

        itemPriceLabel = new JLabel("Price: ");
        itemPriceLabel.setBounds(440, 40, 200, 20);
        this.add(itemPriceLabel);

        itemNameTextField = new JTextField();
        itemNameTextField.setBounds(480, 20, 200, 20);
        this.add(itemNameTextField);

        itemPriceTextField = new JTextField();
        itemPriceTextField.setBounds(480, 40, 200, 20);
        this.add(itemPriceTextField);

        addButton = new JButton("ADD");
        addButton.setBounds(480, 80, 100, 20);
        this.add(addButton);
        addButton.addActionListener(this);

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(600, 80, 100, 20);
        this.add(removeButton);
        removeButton.addActionListener(this);

        sortByTimeButton = new JButton("SORT BY TIME");
        sortByTimeButton.setBounds(480, 150, 120, 20);
        this.add(sortByTimeButton);
        sortByTimeButton.addActionListener(this);

        sortByNameButton = new JButton("SORT BY NAME");
        sortByNameButton.setBounds(480, 180, 120, 20);
        this.add(sortByNameButton);
        sortByNameButton.addActionListener(this);

        sortByPriceButton = new JButton("SORT BY PRICE");
        sortByPriceButton.setBounds(480, 210, 120, 20);
        this.add(sortByPriceButton);
        sortByPriceButton.addActionListener(this);

        setLayout(null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
     
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        itemListTextArea.setText(items.toString());
        totalPriceLabel.setText("Total Price: $" + items.getTotalPrice());
    }

    public void sortByTime() {
        boolean sorted = false;
        while (sorted == false) {
            sorted = true;
            for (int i = 0; i < items.size() - 1; i++) {
                for (int j = i + 1; j < items.size(); j++) {
                    if (items.get(i).getTimestamp() > items.get(j).getTimestamp()) {
                        Item i1 = items.get(i);
                        Item i2 = items.get(j);

                        items.set(i, i2);
                        items.set(j, i1);
                        sorted = false;
                    }
                }
            }
        }
    }

    public void sortByPrice() {
        boolean sorted = false;
        while (sorted == false) {
            sorted = true;
            for (int i = 0; i < items.size() - 1; i++) {
                for (int j = i + 1; j < items.size(); j++) {
                    if (items.get(i).getPrice() > items.get(j).getPrice()) {
                        Item i1 = items.get(i);
                        Item i2 = items.get(j);

                        items.set(i, i2);
                        items.set(j, i1);
                        sorted = false;
                    }
                }
            }
        }
    }

    public void sortByName() {
        boolean sorted = false;
        while (sorted == false) {
            sorted = true;
            for (int i = 0; i < items.size() - 1; i++) {
                for (int j = i + 1; j < items.size(); j++) {
                    if (items.get(i).getName().compareTo(items.get(j).getName()) > 0) {
                        Item i1 = items.get(i);
                        Item i2 = items.get(j);

                        items.set(i, i2);
                        items.set(j, i1);
                        sorted = false;
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            timestamp += 1;
            items.add(new Item(itemNameTextField.getText(), Double.parseDouble(itemPriceTextField.getText()), timestamp));

            if (sortingAlgo.equals("TIME")) {
                sortByTime();
            } else if (sortingAlgo.equals("NAME")) {
                sortByName();
            } else if (sortingAlgo.equals("PRICE")) {
                sortByPrice();
            }
        } else if (e.getSource() == removeButton) {
            items.remove(new Item(itemNameTextField.getText(), Double.parseDouble(itemPriceTextField.getText()), timestamp));
        } else if (e.getSource() == sortByNameButton) {
            sortByName();
            sortingAlgo = "NAME";
        } else if (e.getSource() == sortByPriceButton) {
            sortByPrice();
            sortingAlgo = "PRICE";
        } else if (e.getSource() == sortByTimeButton) {
            sortByTime();
            sortingAlgo = "TIME";
        }
    }
}