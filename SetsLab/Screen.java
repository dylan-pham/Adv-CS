import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Screen extends JPanel implements ActionListener, MouseListener {
    private String itemList;
    private JTextArea itemListTextArea;
    private JScrollPane itemListScrollPane;
    private ArrayList<ShoppingCart<Item, Integer>> shoppingCart;
    private String shoppingCartList;
    private JTextArea shoppingCartListTextArea;
    private JScrollPane shoppingCartListScrollPane;
    private Set<Item> dataDisplay;
    private Set<Item> dataStorage;
    private JLabel name;
    private JLabel price;
    private JLabel quantity;
    private JTextField nameInput;
    private JTextField priceInput;
    private JTextField quantityInput;
    private JButton addToCart;
    private JButton removeFromList;
    private JButton addToList;
    private int totalPrice;
    private JLabel totalPriceLabel;

    public Screen() {
        setLayout(null);
        setFocusable(true);
        
        itemList = "ITEM LIST\n";
        
        itemListTextArea = new JTextArea(380, 660);
        itemListTextArea.setEditable(false);
        this.add(itemListTextArea);
        
        itemListScrollPane = new JScrollPane(itemListTextArea); 
        itemListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemListScrollPane.setBounds(20, 20, 380, 660);
        this.add(itemListScrollPane);
        
        shoppingCart = new ArrayList<ShoppingCart<Item, Integer>>();

        shoppingCartList = "SHOPPING CART\n";

        shoppingCartListTextArea = new JTextArea();
        shoppingCartListTextArea.setText(shoppingCartList);
        shoppingCartListTextArea.setBounds(420, 20, 360, 660);
        shoppingCartListTextArea.setEditable(false);
        this.add(shoppingCartListTextArea);
        
        dataDisplay = new TreeSet<Item>();
        dataStorage = new HashSet<Item>();

        try {
            Scanner fileScanner = new Scanner(new File("StoreA.txt"));

            while (fileScanner.hasNext()) {
                String[] temp = fileScanner.next().split(",");
                String name = temp[0];
                double price = Double.parseDouble(temp[1]);
                Item i = new Item(name, price);

                dataDisplay.add(i);
                dataStorage.add(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Item each : dataDisplay) {
            itemList += each.toString() + "\n";
        }
        itemListTextArea.setText(itemList);

        name = new JLabel("Name: ");
        name.setBounds(20, 720, 100, 20);
        this.add(name);

        nameInput = new JTextField();
        nameInput.setBounds(70, 720, 100, 20);
        this.add(nameInput);
        
        price = new JLabel("Price: ");
        price.setBounds(200, 720, 100, 20);
        this.add(price);

        priceInput = new JTextField();
        priceInput.setBounds(250, 720, 100, 20);
        this.add(priceInput);

        quantity = new JLabel("Quantity: ");
        quantity.setBounds(380, 720, 100, 20);
        this.add(quantity);

        quantityInput = new JTextField();
        quantityInput.setBounds(450, 720, 100, 20);
        this.add(quantityInput);

        addToCart = new JButton("Add to Shopping Cart");
        addToCart.setBounds(570, 720, 200, 20);
        this.add(addToCart);
        addToCart.addActionListener(this);

        removeFromList = new JButton("Remove from Item List");
        removeFromList.setBounds(570, 780, 200, 20);
        this.add(removeFromList);
        removeFromList.addActionListener(this);

        addToList = new JButton("Add To Item List");
        addToList.setBounds(570, 750, 200, 20);
        this.add(addToList);
        addToList.addActionListener(this);

        totalPrice = 0;

        totalPriceLabel = new JLabel("Total Price: $0");
        totalPriceLabel.setBounds(420, 680, 200, 20);
        this.add(totalPriceLabel);

        addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 820);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToCart) {
            String name;
            double price;
            int quantity;
            ShoppingCart<Item, Integer> s;

            try {
                name = nameInput.getText();
                price = Double.parseDouble(priceInput.getText());
                quantity = Integer.parseInt(quantityInput.getText());
                s = new ShoppingCart<Item, Integer>(new Item(name, price), quantity);
                
                if (dataStorage.contains(s.getItem())) {
                    shoppingCart.add(s);
    
                    shoppingCartList += s.toString() + "\n";
                    shoppingCartListTextArea.setText(shoppingCartList);
    
                    totalPrice += s.getPrice();
                    setTotalPriceLabel();
                }
            } catch (Exception exception) {

            }

        } 
        
        if (e.getSource() == removeFromList) {
            String name;
            double price;

            try {
                name = nameInput.getText();
                price = Double.parseDouble(priceInput.getText());
            } finally {}

            Item i = new Item(name, price);

            if (dataStorage.contains(i)) {
                dataStorage.remove(i);
                dataDisplay.remove(i);
            }
            
            itemList = "ITEM LIST\n";
            for (Item each : dataDisplay) {
                itemList += each.toString() + "\n";
            }
            itemListTextArea.setText(itemList);
        }

        if (e.getSource() == addToList) {
            String name;
            double price;

            try {
                name = nameInput.getText();
                price = Double.parseDouble(priceInput.getText());
            } finally {}

            Item i = new Item(name, price);

            if (!dataStorage.contains(i)) {
                dataStorage.add(i);
                dataDisplay.add(i);
            }
            
            itemList = "ITEM LIST\n";
            for (Item each : dataDisplay) {
                itemList += each.toString() + "\n";
            }
            itemListTextArea.setText(itemList);
        }
    }

    public void setTotalPriceLabel() {
        totalPriceLabel.setText("Total Price: $" + totalPrice);
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