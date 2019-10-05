import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;;

// for testing purposes
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Screen extends JPanel implements ActionListener, MouseListener {
    private ArrayList<ShoppingCart<Item, Integer>> shoppingCart;
    private String itemList;
    private JTextArea itemListTextArea;
    private JScrollPane scrollPane;
    private JLabel statusBar;
    private Set<Item> dataDisplay;
    private Set<Item> dataStorage;

    public Screen() {
        setLayout(null);
        setFocusable(true);

        shoppingCart = new ArrayList<ShoppingCart<Item, Integer>>();

        itemList = "ITEM LIST\n";

        itemListTextArea = new JTextArea(760, 660);
        itemListTextArea.setEditable(false);
        this.add(itemListTextArea);

        scrollPane = new JScrollPane(itemListTextArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 20, 760, 660);
        this.add(scrollPane);
        
        dataDisplay = new TreeSet<Item>();
        dataStorage = new HashSet<Item>();

        try {
            Scanner fileScanner = new Scanner(new File("StoreA.txt"));

            while (fileScanner.hasNext()) {
                String[] temp = fileScanner.next().split(",");
                String name = temp[0];
                double price = Double.parseDouble(temp[1]);
                Item i = new Item(name, price);

                // shoppingCart.add(new ShoppingCart<Item, Integer>(new Item(name, price), 1));
                dataDisplay.add(i);
                dataStorage.add(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // for (ShoppingCart<Item, Integer> each : shoppingCart) {
        //     dataDisplay.add(each.getItem());
        //     dataStorage.add(each.getItem());
        // }

        for (Item each : dataDisplay) {
            itemList += each.toString() + "\n";
        }

        itemListTextArea.setText(itemList);

        statusBar = new JLabel("<status bar>");
        statusBar.setBounds(20, 680, 640, 20);
        this.add(statusBar);


        addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    public void actionPerformed(ActionEvent e) {
        
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