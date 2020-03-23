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
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
 
public class Screen extends JPanel implements MouseListener, ActionListener, ListSelectionListener {
    private MyHashMap<String, String> abbCountryHmap;
    private MyHashMap<Country, MyImage> countryImageHmap;
    private DefaultListModel listModel;
    private JList<String> choiceList;
    private JLabel urlLabel;
    private JLabel countryAbbLabel;
    private JLabel landmarkNameLabel;
    private JTextField urlTextField;
    private JTextField countryAbbTextField;
    private JTextField landmarkNameTextField;
    private JButton addButton;
    private JButton searchAbbButton;
    private URL url;
    private Image image;
    private JLabel description;
    private JButton moveToNextImageButton;
    private JButton moveToPreviousImageButton;
    private JButton deleteImageButton;
    private Country currentCountry;
    private DLList<MyImage> images;
    private int currentIndex;

    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);

        abbCountryHmap = new MyHashMap<String, String>();
        countryImageHmap = new MyHashMap<Country, MyImage>();

        // adding abbreviation-country pairs to hash map
        try {
            Scanner scan = new Scanner(new FileReader("countries.txt"));
            while (scan.hasNextLine()){
               String[] temp = scan.nextLine().split(",");
               String abbreviation = temp[0];
               String countryName = temp[1];

               abbCountryHmap.put(abbreviation, countryName);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // adding filler countryImageHmap
        countryImageHmap.put(new Country("Mexico", "mx"), new MyImage("https://caribeviaggi.com/wp-content/uploads/2017/03/Xcaret-plus-caribe-viaggi.jpg", "Xcaret Park"));
        countryImageHmap.put(new Country("Mexico", "mx"), new MyImage("https://www.xelha.com/img/og/xelha-inlet.jpg", "Xel-Ha Park"));
        countryImageHmap.put(new Country("Mexico", "mx"), new MyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Chichen_Itza_%283326547826%29.jpg/1200px-Chichen_Itza_%283326547826%29.jpg", "El Castillo"));
        countryImageHmap.put(new Country("Italy", "it"), new MyImage("https://cdn.britannica.com/36/162636-050-932C5D49/Colosseum-Rome-Italy.jpg", "Colosseum"));
        countryImageHmap.put(new Country("Italy", "it"), new MyImage("https://www.tripsavvy.com/thmb/0yk1P8u-pfjbur_FJ7U0DQi_IB0=/3733x2800/smart/filters:no_upscale()/sistine-chapel-allegri-miserere-56a153c53df78cf77269ab3e.jpg", "Sistine Chapel"));
        countryImageHmap.put(new Country("Italy", "it"), new MyImage("https://upload.wikimedia.org/wikipedia/commons/7/77/Florence_Duomo_from_Michelangelo_hill.jpg", "Il Duomo"));
        countryImageHmap.put(new Country("France", "fr"), new MyImage("https://www.toureiffel.paris/themes/custom/tour_eiffel/img/picto_myGoogleBusiness_1.jpg", "Eiffel Tower"));
        countryImageHmap.put(new Country("France", "fr"), new MyImage("https://i.pinimg.com/originals/70/31/bf/7031bf6bc69d9f01b1de7169a91885ef.jpg", "Quartier Latin"));
        countryImageHmap.put(new Country("France", "fr"), new MyImage("https://media-cdn.tripadvisor.com/media/photo-s/10/c6/65/a4/stade-velodrome-in-mareille.jpg", "Stade Veledrome"));

        listModel = new DefaultListModel();
        choiceList = new JList<String>(listModel);
        add(choiceList);
        choiceList.addListSelectionListener(this);

        String s = "it - Italy - 3";
        listModel.addElement(s);
        s = "mx - Meixco - 3";
        listModel.addElement(s);
        s = "fr - France - 3";
        listModel.addElement(s);
         
        JScrollPane scrollPane = new JScrollPane(choiceList); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 10, 200, 250);
        this.add(scrollPane);

        urlLabel = new JLabel("URL");
        urlLabel.setBounds(10, 270, 100, 10);
        this.add(urlLabel);

        countryAbbLabel = new JLabel("Abb.");
        countryAbbLabel.setBounds(10, 290, 100, 10);
        this.add(countryAbbLabel);

        landmarkNameLabel = new JLabel("Landmark");
        landmarkNameLabel.setBounds(10, 310, 100, 10);
        this.add(landmarkNameLabel);

        urlTextField = new JTextField();
        urlTextField.setBounds(100, 270, 100, 20);
        this.add(urlTextField);

        countryAbbTextField = new JTextField();
        countryAbbTextField.setBounds(100, 290, 100, 20);
        this.add(countryAbbTextField);

        landmarkNameTextField = new JTextField();
        landmarkNameTextField.setBounds(100, 310, 100, 20);
        this.add(landmarkNameTextField);

        addButton = new JButton("ADD");
        addButton.setBounds(100, 340, 100, 20);
        this.add(addButton);
        addButton.addActionListener(this);

        searchAbbButton = new JButton("SEARCH");
        searchAbbButton.setBounds(100, 370, 100, 20);
        this.add(searchAbbButton);
        searchAbbButton.addActionListener(this);

        try {
            // default image
            url = new URL("https://awmaa.com/wp-content/uploads/2017/04/default-image.jpg");
            image = ImageIO.read(url).getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        } catch (IOException err) {
            System.out.println(err);
        }

        description = new JLabel("<country name> - <abbreviation> - <landmark name>");
        description.setBounds(325, 425, 400, 20);
        this.add(description);

        moveToNextImageButton = new JButton();
        moveToNextImageButton.setBounds(710, 200, 80, 80);
        try {
            Image img = ImageIO.read(getClass().getResource("img/right.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            moveToNextImageButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.add(moveToNextImageButton);
        moveToNextImageButton.addActionListener(this);

        moveToPreviousImageButton = new JButton();
        moveToPreviousImageButton.setBounds(215, 200, 80, 80);
        try {
            Image img = ImageIO.read(getClass().getResource("img/left.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            moveToPreviousImageButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.add(moveToPreviousImageButton);
        moveToPreviousImageButton.addActionListener(this);

        deleteImageButton = new JButton("DELETE");
        deleteImageButton.setBounds(415, 460, 200, 20);
        this.add(deleteImageButton);
        deleteImageButton.addActionListener(this);

        currentCountry = null;
        images = null;

        addMouseListener(this);
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 300, 10, null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            Country c = new Country(abbCountryHmap.get(countryAbbTextField.getText()).get(0), countryAbbTextField.getText());
            MyImage i = new MyImage(urlTextField.getText(), landmarkNameTextField.getText());
            
            if (!countryImageHmap.contains(c)) {
                String s = c.toString() + " - " + 1;
                listModel.addElement(s);
            } else {
                int newSize = countryImageHmap.get(c).size() + 1;
                String s = c.toString() + " - " + newSize;

                for (int j = 0; j < listModel.size(); j++) {
                    String s2 = (String)listModel.getElementAt(j);
                    if (s2.split(" - ")[0].equals(c.getAbbreviation()))
                        listModel.set(j, s);
                }
            }
            
            countryImageHmap.put(c, i);
        } else if (e.getSource() == searchAbbButton) { // check
            String abb = countryAbbTextField.getText();
            Country c = new Country(abbCountryHmap.get(abb).get(0), abb);
            
            if (abbCountryHmap.contains(abb) && countryImageHmap.get(c) != null) {
                currentCountry = c;
                images = countryImageHmap.get(currentCountry);
                currentIndex = 0;

                try {
                    url = new URL(images.get(currentIndex).getURL());
                    image = ImageIO.read(url).getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                    description.setText(currentCountry.getName() + " - " + currentCountry.getAbbreviation() + " - " + images.get(currentIndex).getLandmarkName());

                } catch (IOException err) {
                    System.out.println(err);
                }
            }
        } else if (e.getSource() == moveToNextImageButton) {
            if (currentIndex + 1 < images.size()) {
                currentIndex++;

                try {
                    url = new URL(images.get(currentIndex).getURL());
                    image = ImageIO.read(url).getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                    description.setText(currentCountry.getName() + " - " + currentCountry.getAbbreviation() + " - " + images.get(currentIndex).getLandmarkName());
                } catch (IOException err) {
                    System.out.println(err);
                }
            }
        } else if (e.getSource() == moveToPreviousImageButton) {
            if (currentIndex - 1 >= 0)
                currentIndex--;

                try {
                    url = new URL(images.get(currentIndex).getURL());
                    image = ImageIO.read(url).getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                    description.setText(currentCountry.getName() + " - " + currentCountry.getAbbreviation() + " - " + images.get(currentIndex).getLandmarkName());
                } catch (IOException err) {
                    System.out.println(err);
                }
        } else if (e.getSource() == deleteImageButton) {
            countryImageHmap.get(currentCountry).remove(images.get(currentIndex));
            if (currentIndex == images.size()) {
                currentIndex--;
            } else if (currentIndex == 0 && images.size() != 1) {
                currentIndex++;
            }
            
            try {
                url = new URL(images.get(currentIndex).getURL());
                image = ImageIO.read(url).getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                description.setText(currentCountry.getName() + " - " + currentCountry.getAbbreviation() + " - " + images.get(currentIndex).getLandmarkName());
            } catch (IOException err) {
                System.out.println(err);
            }
        }

        repaint();
    }

    public void valueChanged(ListSelectionEvent e) { 
        String[] s = choiceList.getSelectedValue().split(" - ");
        currentCountry = new Country(s[1], s[0]);
        images = countryImageHmap.get(currentCountry);
        currentIndex = 0;

        try {
            url = new URL(images.get(currentIndex).getURL());
            image = ImageIO.read(url).getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            description.setText(currentCountry.getName() + " - " + currentCountry.getAbbreviation() + " - " + images.get(currentIndex).getLandmarkName());
        } catch (IOException err) {
            System.out.println(err);
        }


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