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

// for testing purposes
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Screen extends JPanel implements ActionListener, MouseListener {
    private JButton addMoreJButton;
    private JButton doneJButton;

    private JLabel label1;
    private JTextField textfield1;
    private JLabel label2;
    private JTextField textfield2;
    private JLabel label3;
    private JTextField textfield3;
    private JLabel label4;
    private JTextField textfield4;
    private JLabel label5;
    private JTextField textfield5;

    private JTextArea resumeTextArea;

    private int currentSection;

    private String name;
    private String address;
    private String email;
    private String objectives;
    private String skills;

    private ArrayList<Education> schools;
    private ArrayList<Job> jobs;

    private ListIterator<Education> schoolsIterator;
    private ListIterator<Job> jobsIterator;

    private String resume;

    private JScrollPane scrollPane;

    public Screen() {
        setLayout(null);
        setFocusable(true);

        addMoreJButton = new JButton("Add to resume.");
        addMoreJButton.setBounds(360, 50, 200, 200);
        this.add(addMoreJButton);
        addMoreJButton.addActionListener(this);

        doneJButton = new JButton("Done with section.");
        doneJButton.setBounds(575, 50, 200, 200);
        this.add(doneJButton);
        doneJButton.addActionListener(this);
        doneJButton.setVisible(false);

        label1 = new JLabel("Name:");
        label1.setBounds(25, 50, 150, 20);
        this.add(label1);

        textfield1 = new JTextField();
        textfield1.setBounds(170, 50, 175, 20);
        this.add(textfield1);

        label2 = new JLabel("Address:");
        label2.setBounds(25, 100, 150, 20);
        this.add(label2);

        textfield2 = new JTextField();
        textfield2.setBounds(170, 100, 175, 20);
        this.add(textfield2);

        label3 = new JLabel("Email:");
        label3.setBounds(25, 150, 150, 20);
        this.add(label3);

        textfield3 = new JTextField();
        textfield3.setBounds(170, 150, 175, 20);
        this.add(textfield3);

        label4 = new JLabel("Objectives:");
        label4.setBounds(25, 200, 150, 20);
        this.add(label4);

        textfield4 = new JTextField();
        textfield4.setBounds(170, 200, 175, 20);
        this.add(textfield4);

        label5 = new JLabel("Skills:");
        label5.setBounds(25, 250, 150, 20);
        this.add(label5);

        textfield5 = new JTextField();
        textfield5.setBounds(170, 250, 175, 20);
        this.add(textfield5);

        resumeTextArea = new JTextArea();
        resumeTextArea.setEditable(false);
        resumeTextArea.setVisible(false);
        resumeTextArea.setBounds(20, 20, 760, 660);
        this.add(resumeTextArea);

        currentSection = 1;

        schools = new ArrayList<Education>();
        jobs = new ArrayList<Job>();

        schoolsIterator = schools.listIterator();
        jobsIterator = jobs.listIterator();

        resume = "";

        scrollPane = new JScrollPane(resumeTextArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 20, 760, 660);
        scrollPane.setVisible(false);
 
        this.add(scrollPane);

        addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 700);
    }

    public void createResume() {
        resume += "Name: " + name + "\n";
        resume += "Address: " + address + "\n";
        resume += "Email: " + email + "\n";
        resume += "Objectives: " + objectives + "\n";
        resume += "Skills: " + skills + "\n";

        schoolsIterator = schools.listIterator();
        while (schoolsIterator.hasNext()) {
            resume += schoolsIterator.next().toString();
        }
        
        jobsIterator = jobs.listIterator();
        while (jobsIterator.hasNext()) {
            resume += jobsIterator.next().toString();
        }

        resumeTextArea.setVisible(true);
        resumeTextArea.setText(resume);

        scrollPane.setVisible(true);
    }

    public void showNextSection() {
        if (currentSection == 1) {
            // setting up section 2
            addMoreJButton.setText("Add more to resume.");
            doneJButton.setVisible(true);
            label1.setText("School Name:");
            label2.setText("Year of Graduation:");
            label3.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            textfield3.setVisible(false);
            textfield4.setVisible(false);
            textfield5.setVisible(false);
            textfield1.setText("");
            textfield2.setText("");
            textfield3.setText("");
            textfield4.setText("");
            textfield5.setText("");
        }

        if (currentSection == 2) {
            // setting up section 3
            label1.setText("Job Title:");
            label2.setText("Company Name:");
            label3.setText("Start Date (YYYY-MM):");
            label4.setText("End Date (YYYY-MM):");
            label5.setText("Job Description:");
            label3.setVisible(true);
            label4.setVisible(true);
            label5.setVisible(true);
            textfield1.setText("");
            textfield2.setText("");
            textfield3.setVisible(true);
            textfield4.setVisible(true);
            textfield5.setVisible(true);
        }

        if (currentSection == 3) {
            // setting up section 4
            label1.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            textfield1.setVisible(false);
            textfield2.setVisible(false);
            textfield3.setVisible(false);
            textfield4.setVisible(false);
            textfield5.setVisible(false);    
            addMoreJButton.setVisible(false);
            doneJButton.setVisible(false);

            createResume();
        }
    }

    public void addToSchoolsIterator(Education edu) {
        boolean added = false;

        schoolsIterator = schools.listIterator();
        while (schoolsIterator.hasNext()) {
            if (schoolsIterator.next().getGraduationDate() < edu.getGraduationDate()) {
                schoolsIterator.previous();
                schoolsIterator.add(edu);
                added = true;
                break;
            }
        }

        if (!added)
            schoolsIterator.add(edu);
    }

    public void addToJobsIterator(Job job) {
        boolean added = false;

        jobsIterator = jobs.listIterator();
        while (jobsIterator.hasNext()) {
            if (jobsIterator.next().getEndDate().compareTo(job.getEndDate()) < 0) {
                jobsIterator.previous();
                jobsIterator.add(job);
                added = true;
                break;
            }
        }

        if (!added)
            jobsIterator.add(job);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMoreJButton) {
            if (currentSection == 1) {
                try {
                    name = textfield1.getText();
                    address = textfield2.getText();
                    email = textfield3.getText();
                    objectives = textfield4.getText();
                    skills = textfield5.getText();
                    
                    showNextSection();
                    currentSection = 2;
                } catch (Exception exception) {
                    ;
                }
            }

            if (currentSection == 2) {
                try {
                    addToSchoolsIterator(new Education(textfield1.getText(), Integer.parseInt(textfield2.getText())));

                    textfield1.setText("");
                    textfield2.setText("");
                } catch (Exception exception) {
                    ;
                }
            }

            if (currentSection == 3) {
                try {
                    addToJobsIterator(new Job(textfield1.getText(), textfield2.getText(), textfield3.getText(), textfield4.getText(), textfield5.getText()));

                    textfield1.setText("");
                    textfield2.setText("");
                    textfield3.setText("");
                    textfield4.setText("");
                    textfield5.setText("");
                } catch (Exception exception) {
                    ;
                }
            }
        }

        if (e.getSource() == doneJButton) {
            showNextSection();
            currentSection++;
        }
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