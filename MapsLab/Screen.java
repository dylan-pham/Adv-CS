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
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class Screen extends JPanel implements ActionListener, MouseListener {
    private HashMap<Profile, SchoolInfo> hmap;
    private TreeMap<Profile, SchoolInfo> tmap;
    private String[] schools = {"MVHS", "LAHS", "Fremont HS", "Homestead HS", "Bronx Sci", "IMSA", "Cupertino HS", "Monta Vista HS", "Saratoga HS", "Leland HS"};
    private String[] englishClasses = {"AP Comp", "Philosophy", "AmLit"};
    private String[] mathClasses = {"Geometry", "Calculus", "Statistics"};
    private String[] languageClasses = {"Spanish", "French", "German"};
    private String profileList;
    private JTextArea profileListTextArea;
    private JScrollPane profileListScrollPane;
    private JLabel firstNameJLabel;
    private JLabel lastNameJLabel;
    private JLabel birthYearJLabel;
    private JLabel schoolNameJLabel;
    private JLabel statusBar;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField birthYearInput;
    private JTextField schoolNameInput;
    private JButton searchButton;
    private JButton removeProfileButton;
    private JButton changeProfileButton;
    private JButton addProfileButton;
    private JButton addToProfile;
    private JButton addToSchedule;
    private JButton removeFromSchedule;
    private JTextField classInput;
    private JLabel adminViewLabel;
    private JTextArea description;

    public Screen() {
        setLayout(null);
        setFocusable(true);
        
        hmap = new HashMap<Profile, SchoolInfo>();
        tmap = new TreeMap<Profile, SchoolInfo>();
        profileList = "PROFILE LIST\n";
        
        profileListTextArea = new JTextArea(450, 670);
        profileListTextArea.setEditable(false);
        this.add(profileListTextArea);
        
        profileListScrollPane = new JScrollPane(profileListTextArea); 
        profileListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        profileListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        profileListScrollPane.setBounds(20, 20, 450, 670);
        this.add(profileListScrollPane);

        try {
            Scanner fileScanner = new Scanner(new File("names.txt"));

            while (fileScanner.hasNext()) {
                String firstfirstName = fileScanner.next();
                String lastfirstName = fileScanner.next();
                int birthYear = (int)(Math.random() * 10 + 2000);
                String english = englishClasses[(int)(Math.random() * englishClasses.length)];
                String math = mathClasses[(int)(Math.random() * mathClasses.length)];
                String lang = languageClasses[(int)(Math.random() * languageClasses.length)];
                String[] schedule = new String[5];
                schedule[0] = english;
                schedule[1] = math;
                schedule[2] = lang;
                schedule[3] = "";
                schedule[4] = "";

                tmap.put(new Profile(firstfirstName, lastfirstName, birthYear), new SchoolInfo(schools[(int)(Math.random() * schools.length)], schedule));
                hmap.put(new Profile(firstfirstName, lastfirstName, birthYear), new SchoolInfo(schools[(int)(Math.random() * schools.length)], schedule));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        updateProfileList();

        firstNameJLabel = new JLabel("First name: ");
        firstNameJLabel.setBounds(480, 50, 100, 20);
        this.add(firstNameJLabel);

        firstNameInput = new JTextField();
        firstNameInput.setBounds(580, 50, 100, 20);
        this.add(firstNameInput);
        
        lastNameJLabel = new JLabel("Last name: ");
        lastNameJLabel.setBounds(480, 90, 100, 20);
        this.add(lastNameJLabel);

        lastNameInput = new JTextField();
        lastNameInput.setBounds(580, 90, 100, 20);
        this.add(lastNameInput);

        birthYearJLabel = new JLabel("Birth year: ");
        birthYearJLabel.setBounds(480, 130, 100, 20);
        this.add(birthYearJLabel);
        
        birthYearInput = new JTextField();
        birthYearInput.setBounds(580, 130, 100, 20);
        this.add(birthYearInput);

        schoolNameJLabel = new JLabel("School name: ");
        schoolNameJLabel.setBounds(480, 170, 100, 20);
        this.add(schoolNameJLabel);
        
        schoolNameInput = new JTextField();
        schoolNameInput.setBounds(580, 170, 100, 20);
        this.add(schoolNameInput);

        statusBar = new JLabel("<status bar>");
        statusBar.setBounds(20, 690, 600, 20);
        this.add(statusBar);

        searchButton = new JButton("Search");
        searchButton.setBounds(480, 220, 100, 20);
        searchButton.addActionListener(this);
        this.add(searchButton);

        changeProfileButton = new JButton("Change");
        changeProfileButton.setBounds(480, 250, 100, 20);
        changeProfileButton.addActionListener(this);
        this.add(changeProfileButton);

        addProfileButton = new JButton("Add");
        addProfileButton.setBounds(480, 280, 100, 20);
        addProfileButton.addActionListener(this);
        this.add(addProfileButton);

        removeProfileButton = new JButton("Remove");
        removeProfileButton.setBounds(480, 310, 100, 20);
        removeProfileButton.addActionListener(this);
        this.add(removeProfileButton);

        addToSchedule = new JButton("Add to schedule");
        addToSchedule.setBounds(20, 770, 200, 20);
        addToSchedule.addActionListener(this);
        this.add(addToSchedule);

        removeFromSchedule = new JButton("Remove from schedule");
        removeFromSchedule.setBounds(300, 770, 200, 20);
        removeFromSchedule.addActionListener(this);
        this.add(removeFromSchedule);

        classInput = new JTextField();
        classInput.setBounds(20, 740, 480, 20);
        this.add(classInput);

        adminViewLabel = new JLabel("ADMIN VIEW");
        adminViewLabel.setBounds(480, 25, 200, 20);
        this.add(adminViewLabel);

        description = new JTextArea("Search requirements:\n-first name\n-last name\n-birth year\n\nChange requirements:\n-first name\n-last name\n-birth year\n-school name\n\nAdd requirements:\n-first name\n-last name\n-birth year\n-school name\n\nRemove requirements:\n-first name\n-last name\n-birth year");
        description.setBounds(480, 340, 300, 350);
        this.add(description);

        addMouseListener(this);
    }

    public void updateProfileList() {
        profileList = "";

        for (Profile p : tmap.keySet()) {
            profileList += p.toString() + "\n";
        }

        profileListTextArea.setText(profileList);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 820);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                int birthYear = Integer.parseInt(birthYearInput.getText());
                Profile p = new Profile(firstName, lastName, birthYear);

                if (hmap.containsKey(p)) {
                    statusBar.setText(p.getFirstName() + " " + p.getLastName() + " attends " + hmap.get(p).getSchool() + " | Schedule: " + hmap.get(p).getSchedule());
                } else {
                    statusBar.setText("Check inputs...");
                }

            } catch (Exception exception) {}
        } else if (e.getSource() == changeProfileButton) {
            try {
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                int birthYear = Integer.parseInt(birthYearInput.getText());
                Profile p = new Profile(firstName, lastName, birthYear);
                String schoolName = schoolNameInput.getText();

                if (hmap.containsKey(p)) {
                    tmap.get(p).setSchool(schoolName);
                    hmap.get(p).setSchool(schoolName);
                    statusBar.setText(firstName + "'s school changed to " + schoolName);
                } else {
                    statusBar.setText("Check inputs...");
                }

            } catch (Exception exception) {}
        } else if (e.getSource() == removeProfileButton) {
            try {
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                int birthYear = Integer.parseInt(birthYearInput.getText());
                Profile p = new Profile(firstName, lastName, birthYear);

                if (tmap.remove(p) != null && hmap.remove(p) != null) {
                    statusBar.setText(firstName + " " + lastName + " removed from student list");
                } else {
                    statusBar.setText("Check inputs...");
                }

            } catch (Exception exception) {}
        } else if (e.getSource() == addProfileButton) {
            try {
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                int birthYear = Integer.parseInt(birthYearInput.getText());
                Profile p = new Profile(firstName, lastName, birthYear);
                String schoolName = schoolNameInput.getText();

                if (!schoolName.equals("") && !hmap.containsKey(p)) {
                    String[] filler = {};
                    tmap.put(p, new SchoolInfo(schoolName, filler));
                    hmap.put(p, new SchoolInfo(schoolName, filler));
                    statusBar.setText(firstName + " " + lastName + " added to student list");
                } else {
                    statusBar.setText("Check inputs...");
                }
            } catch (Exception exception) {}
        } else if (e.getSource() == addToSchedule) {
            try {
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                int birthYear = Integer.parseInt(birthYearInput.getText());
                Profile p = new Profile(firstName, lastName, birthYear);

                hmap.get(p).addToSchedule(classInput.getText());
                tmap.get(p).addToSchedule(classInput.getText());

                statusBar.setText(p.getFirstName() + " " + p.getLastName() + " attends " + hmap.get(p).getSchool() + " | Schedule: " + hmap.get(p).getSchedule());

                repaint();
            } catch (Exception exception) {}
        } else if (e.getSource() == removeFromSchedule) {
            try {
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                int birthYear = Integer.parseInt(birthYearInput.getText());
                Profile p = new Profile(firstName, lastName, birthYear);

                hmap.get(p).removeFromSchedule(classInput.getText());
                tmap.get(p).removeFromSchedule(classInput.getText());
                
                statusBar.setText(p.getFirstName() + " " + p.getLastName() + " attends " + hmap.get(p).getSchool() + " | Schedule: " + hmap.get(p).getSchedule());

                repaint();
            } catch (Exception exception) {}
        }

        updateProfileList();
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