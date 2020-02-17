import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
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
 
public class Screen extends JPanel implements ListSelectionListener, MouseListener, ActionListener {
    private JList<String> choiceList;
    private String text = "";
    private HashTable<Car> cars;
    private JTextArea infoTextArea;
    private JButton removeButton1;
    private JButton removeButton2;
    private JButton removeButton3;
    private JButton removeButton4;
    private JButton removeButton5;
    private JButton updateButton1;
    private JButton updateButton2;
    private JButton updateButton3;
    private JButton updateButton4;
    private JButton updateButton5;
    private JLabel newPriceLabel;
    private JTextField newPriceTextField;
    private JButton changeViewButton;
    private JLabel currentViewLabel;
    private String currentView;
    private JButton addCarButton;
    private JTextField carInfoTextField;
    private JLabel exampleLabel;
    private DefaultListModel listModel;
    private ArrayList<String> uniqueMakes;

    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);

        cars = new HashTable<Car>();

        infoTextArea = new JTextArea();
        infoTextArea.setBounds(250, 10, 500, 250);
        this.add(infoTextArea);

        removeButton1 = new JButton("Remove Car 1");
        removeButton1.setBounds(250, 280, 100, 50);
        this.add(removeButton1);
        removeButton1.addActionListener(this);

        removeButton2 = new JButton("Remove Car 2");
        removeButton2.setBounds(350, 280, 100, 50);
        this.add(removeButton2);
        removeButton2.addActionListener(this);

        removeButton3 = new JButton("Remove Car 3");
        removeButton3.setBounds(450, 280, 100, 50);
        this.add(removeButton3);
        removeButton3.addActionListener(this);

        removeButton4 = new JButton("Remove Car 4");
        removeButton4.setBounds(550, 280, 100, 50);
        this.add(removeButton4);
        removeButton4.addActionListener(this);

        removeButton5 = new JButton("Remove Car 5");
        removeButton5.setBounds(650, 280, 100, 50);
        this.add(removeButton5);
        removeButton5.addActionListener(this);

        updateButton1 = new JButton("Update Car 1");
        updateButton1.setBounds(250, 330, 100, 50);
        this.add(updateButton1);
        updateButton1.addActionListener(this);

        updateButton2 = new JButton("Update Car 2");
        updateButton2.setBounds(350, 330, 100, 50);
        this.add(updateButton2);
        updateButton2.addActionListener(this);

        updateButton3 = new JButton("Update Car 3");
        updateButton3.setBounds(450, 330, 100, 50);
        this.add(updateButton3);
        updateButton3.addActionListener(this);

        updateButton4 = new JButton("Update Car 4");
        updateButton4.setBounds(550, 330, 100, 50);
        this.add(updateButton4);
        updateButton4.addActionListener(this);

        updateButton5 = new JButton("Update Car 5");
        updateButton5.setBounds(650, 330, 100, 50);
        this.add(updateButton5);
        updateButton5.addActionListener(this);

        newPriceLabel = new JLabel("New Price: ");
        newPriceLabel.setBounds(15, 280, 100, 20);
        this.add(newPriceLabel);

        newPriceTextField = new JTextField();
        newPriceTextField.setBounds(90, 280, 100, 20);
        this.add(newPriceTextField);

        changeViewButton = new JButton("Change View");
        changeViewButton.setBounds(10, 370, 100, 20);
        this.add(changeViewButton);
        changeViewButton.addActionListener(this);

        currentViewLabel = new JLabel("Current View: Dealer");
        currentViewLabel.setBounds(10, 350, 200, 20);
        this.add(currentViewLabel);

        currentView = "dealer";

        exampleLabel = new JLabel("add new car in this format (including spaces) | \"<make>, <model>, <year>, <price>\"");
        exampleLabel.setBounds(10, 410, 800, 20);
        this.add(exampleLabel);

        carInfoTextField = new JTextField();
        carInfoTextField.setBounds(10, 440, 680, 20);
        this.add(carInfoTextField);

        addCarButton = new JButton("Add Car");
        addCarButton.setBounds(10, 470, 680, 20);
        this.add(addCarButton);
        addCarButton.addActionListener(this);

        cars.add(new Car("Toyota", "Corolla", 2010, 20000.23));
        cars.add(new Car("Toyota", "Camry", 2010, 20000.23));
        cars.add(new Car("Toyota", "Land Cruiser", 2010, 20000.23));
        cars.add(new Car("Honda", "Civic", 2010, 20000.23));
        cars.add(new Car("Honda", "Fit", 2010, 20000.23));
        cars.add(new Car("Honda", "Accord", 2010, 20000.23));
        cars.add(new Car("Chevrolet", "Volt", 2010, 20000.23));
        cars.add(new Car("Mercedes", "B-class", 2010, 20000.23));
        cars.add(new Car("Mercedes", "C-class", 2010, 20000.23));
        
        uniqueMakes = cars.getUniqueMakes();
        listModel = new DefaultListModel();
        for (String each : uniqueMakes) {
            listModel.addElement(each);
        }
        choiceList = new JList<String>(listModel);
        add(choiceList);
        choiceList.addListSelectionListener(this);
         
        JScrollPane scrollPane = new JScrollPane(choiceList); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 10, 200, 250);
        this.add(scrollPane);

        addMouseListener(this);
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 800, 600);
    }
 
    public void valueChanged(ListSelectionEvent e) { 
        infoTextArea.setText(cars.toString(choiceList.getSelectedValue()));

        repaint();
    } 

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton1) {
            cars.removeEntry(choiceList.getSelectedValue(), 0);
        } else if (e.getSource() == removeButton2) {
            cars.removeEntry(choiceList.getSelectedValue(), 1);
        } else if (e.getSource() == removeButton3) {
            cars.removeEntry(choiceList.getSelectedValue(), 2);
        } else if (e.getSource() == removeButton4) {
            cars.removeEntry(choiceList.getSelectedValue(), 3);
        } else if (e.getSource() == removeButton5) {
            cars.removeEntry(choiceList.getSelectedValue(), 4);
        } else if (e.getSource() == updateButton1 && !newPriceTextField.getText().equals("")) {
            double price = Double.parseDouble(newPriceTextField.getText());
            cars.getEntry(choiceList.getSelectedValue(), 0).setPrice(price);
        } else if (e.getSource() == updateButton2 && !newPriceTextField.getText().equals("")) {
            double price = Double.parseDouble(newPriceTextField.getText());
            cars.getEntry(choiceList.getSelectedValue(), 1).setPrice(price);
        } else if (e.getSource() == updateButton3 && !newPriceTextField.getText().equals("")) {
            double price = Double.parseDouble(newPriceTextField.getText());
            cars.getEntry(choiceList.getSelectedValue(), 2).setPrice(price);
        } else if (e.getSource() == updateButton4 && !newPriceTextField.getText().equals("")) {
            double price = Double.parseDouble(newPriceTextField.getText());
            cars.getEntry(choiceList.getSelectedValue(), 3).setPrice(price);
        } else if (e.getSource() == updateButton5 && !newPriceTextField.getText().equals("")) {
            double price = Double.parseDouble(newPriceTextField.getText());
            cars.getEntry(choiceList.getSelectedValue(), 4).setPrice(price);
        } else if (e.getSource() == changeViewButton) {
            if (currentView.equals("dealer")) {
                currentView = "consumer";
                currentViewLabel.setText("Current View: Consumer");

                updateButton1.setVisible(false);
                updateButton2.setVisible(false);
                updateButton3.setVisible(false);
                updateButton4.setVisible(false);
                updateButton5.setVisible(false);
                removeButton1.setText("Buy Car 1");
                removeButton2.setText("Buy Car 1");
                removeButton3.setText("Buy Car 1");
                removeButton4.setText("Buy Car 1");
                removeButton5.setText("Buy Car 1");
                addCarButton.setVisible(true);
                exampleLabel.setVisible(false);
                carInfoTextField.setVisible(false);
                addCarButton.setVisible(false);
                newPriceLabel.setVisible(false);
                newPriceTextField.setVisible(false);
            } else {
                currentView = "dealer";
                currentViewLabel.setText("Current View: Dealer");

                updateButton1.setVisible(true);
                updateButton2.setVisible(true);
                updateButton3.setVisible(true);
                updateButton4.setVisible(true);
                updateButton5.setVisible(true);
                removeButton1.setText("Remove Car 1");
                removeButton2.setText("Remove Car 1");
                removeButton3.setText("Remove Car 1");
                removeButton4.setText("Remove Car 1");
                removeButton5.setText("Remove Car 1");
                addCarButton.setVisible(true);
                exampleLabel.setVisible(true);
                carInfoTextField.setVisible(true);
                addCarButton.setVisible(true);
                newPriceLabel.setVisible(true);
                newPriceTextField.setVisible(true);
            }
        } else if (e.getSource() == addCarButton && !carInfoTextField.getText().equals("")) {
            String[] inputs = carInfoTextField.getText().split(", ");
            
            if (!uniqueMakes.contains(inputs[0])) {
                listModel.addElement(inputs[0]);
            }
            
            cars.add(new Car(inputs[0], inputs[1], Integer.parseInt(inputs[2]), Double.parseDouble(inputs[3])));
        }

        infoTextArea.setText(cars.toString(choiceList.getSelectedValue()));
        repaint();
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