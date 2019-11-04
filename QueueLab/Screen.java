import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Screen extends JPanel implements MouseListener, ActionListener {
    private int currentTimeStamp;
    private Map<String, Integer> m1;
    private Map<String, Integer> m2;
    private PriorityQueue<Patient> waitingPatients;
    private JTextArea patientsTextField;
    private Queue<Patient> dischargedPatients;
    private JLabel viewTypeLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel illnessDescriptionLabel;
    private JTextField illnessDescriptionTextField;
    private JLabel medicalPriorityLabel;
    private JComboBox<String> medicalPriorityCombo;
    private JLabel ageGroupLabel;
    private JComboBox<String> ageGroupCombo;
    private JButton addPatientButton;
    private JButton changePatientInfoButton;
    private JButton changeViewButton;
    private JButton dischargePatientButton;
    private JLabel doctorsNoteLabel;
    private JTextField doctorsNoteTextField;
    private String currentView;
    
    public Screen() {
        setLayout(null);

        currentTimeStamp = 0;
        
        m1 = new HashMap<String, Integer>();
        m1.put("high", 1);
        m1.put("medium", 2);
        m1.put("low", 3);
        m2 = new HashMap<String, Integer>();
        m2.put("child", 1);
        m2.put("adult", 2);

        waitingPatients = new PriorityQueue<Patient>();
        addPatient("Billy", "fever", "low", "adult");
        addPatient("Omar", "broke arm", "high", "child");
        addPatient("Carlos", "concussion", "medium", "adult");

        patientsTextField = new JTextArea();
        patientsTextField.setBounds(20, 220, 600, 300);
        this.add(patientsTextField);

        dischargedPatients = new LinkedList<Patient>();

        viewTypeLabel = new JLabel("Nurse's View");
        viewTypeLabel.setBounds(20, 20, 100, 20);
        this.add(viewTypeLabel);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 50, 150, 20);
        this.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(160, 50, 100, 20);
        this.add(nameTextField);

        illnessDescriptionLabel = new JLabel("Illness Description: ");
        illnessDescriptionLabel.setBounds(20, 80, 200, 20);
        this.add(illnessDescriptionLabel);

        illnessDescriptionTextField = new JTextField();
        illnessDescriptionTextField.setBounds(160, 80, 100, 20);
        this.add(illnessDescriptionTextField);

        medicalPriorityLabel = new JLabel("Medical Priority: ");
        medicalPriorityLabel.setBounds(20, 110, 200, 20);
        this.add(medicalPriorityLabel);

        String[] medicalPriorityChoices = {"high", "medium", "low"};
        medicalPriorityCombo = new JComboBox<String>(medicalPriorityChoices);
        medicalPriorityCombo.setBounds(160, 110, 100, 20);
        this.add(medicalPriorityCombo);

        ageGroupLabel = new JLabel("Age Group: ");
        ageGroupLabel.setBounds(20, 140, 200, 20);
        this.add(ageGroupLabel);

        String[] ageGroupChoices = {"adult", "child"};
        ageGroupCombo = new JComboBox<String>(ageGroupChoices);
        ageGroupCombo.setBounds(160, 140, 100, 20);
        this.add(ageGroupCombo);

        addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds(20, 180, 150, 20);
        addPatientButton.addActionListener(this);
        this.add(addPatientButton);

        changePatientInfoButton = new JButton("Change Patient Info");
        changePatientInfoButton.setBounds(190, 180, 150, 20);
        changePatientInfoButton.addActionListener(this);
        this.add(changePatientInfoButton);

        changeViewButton = new JButton("Change View");
        changeViewButton.setBounds(600, 20, 150, 20);
        changeViewButton.addActionListener(this);
        this.add(changeViewButton);

        dischargePatientButton = new JButton("Discharge Patient");
        dischargePatientButton.setBounds(20, 180, 150, 20);
        dischargePatientButton.addActionListener(this);
        this.add(dischargePatientButton);
        dischargePatientButton.setVisible(false);

        doctorsNoteLabel = new JLabel("Doctor's Note: ");
        doctorsNoteLabel.setBounds(20, 50, 150, 20);
        this.add(doctorsNoteLabel);
        doctorsNoteLabel.setVisible(false);

        doctorsNoteTextField = new JTextField();
        doctorsNoteTextField.setBounds(160, 50, 100, 20);
        this.add(doctorsNoteTextField);
        doctorsNoteTextField.setVisible(false);

        currentView = "nurse";
    
        addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
     
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        String temp = "";
        if (currentView.equals("nurse")) {
            temp += "Waiting Patients:\n";
            Iterator<Patient> iter1 = waitingPatients.iterator();
            while (iter1.hasNext()) {
                temp += iter1.next().toString() + "\n";
            }
        } else {
            temp += "Top Priority Patient:\n";
            if (!waitingPatients.isEmpty()) {
                temp += waitingPatients.peek() + "\n";
            }
        }

        temp += "\nDischarged Patients:\n";
        Iterator<Patient> iter2 = dischargedPatients.iterator();
        while (iter2.hasNext()) {
            temp += iter2.next().toString() + "\n";
        }

        patientsTextField.setText(temp);

    }

    public void addPatient(String name, String illnessDescription, String medicalPriority, String ageGroup) {
        int _medicalPriority = m1.get(medicalPriority);
        int _ageGroup = m2.get(ageGroup);
        waitingPatients.add(new Patient(name, illnessDescription, _medicalPriority, _ageGroup, currentTimeStamp));

        currentTimeStamp++;
    }

    public void changeView() {
        if (currentView.equals("nurse")) {
            currentView = "doctor";
            viewTypeLabel.setText("Doctor's View");
        } else if (currentView.equals("doctor")) {
            currentView = "nurse";
            viewTypeLabel.setText("Nurse's View");
        }

        nameLabel.setVisible(!nameLabel.isVisible());
        nameTextField.setVisible(!nameTextField.isVisible());
        illnessDescriptionLabel.setVisible(!illnessDescriptionLabel.isVisible());
        illnessDescriptionTextField.setVisible(!illnessDescriptionTextField.isVisible());
        medicalPriorityLabel.setVisible(!medicalPriorityLabel.isVisible());
        medicalPriorityCombo.setVisible(!medicalPriorityCombo.isVisible());
        ageGroupLabel.setVisible(!ageGroupLabel.isVisible());
        ageGroupCombo.setVisible(!ageGroupCombo.isVisible());
        addPatientButton.setVisible(!addPatientButton.isVisible());
        changePatientInfoButton.setVisible(!changePatientInfoButton.isVisible());
        dischargePatientButton.setVisible(!dischargePatientButton.isVisible());
        doctorsNoteLabel.setVisible(!doctorsNoteLabel.isVisible());
        doctorsNoteTextField.setVisible(!doctorsNoteTextField.isVisible());
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        System.out.println("x: " + x + " | y: " + y);
    }

    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPatientButton) {
            String name = nameTextField.getText();
            String illnessDescription = illnessDescriptionTextField.getText();
            String medicalPriority = (String)medicalPriorityCombo.getSelectedItem();
            String ageGroup = (String)ageGroupCombo.getSelectedItem();

            addPatient(name, illnessDescription, medicalPriority, ageGroup);
        } 

        if (e.getSource() == changePatientInfoButton) {
            Iterator<Patient> iter = waitingPatients.iterator();
            Patient p = new Patient();
            while (iter.hasNext()) {
                Patient x = iter.next();
                if (x.getName().equals(nameTextField.getText())) {
                    p = x;
                    break;
                }
            }

            try {
                p.setIllnessDescription(illnessDescriptionTextField.getText());
                p.setMedicalPriority(m1.get((String)medicalPriorityCombo.getSelectedItem()));
                waitingPatients.remove(p);
                waitingPatients.add(p);
            } catch (Exception j) {

            }
        }

        if (e.getSource() == changeViewButton) {
            changeView();
        }

        if (e.getSource() == dischargePatientButton) {
            if (!waitingPatients.isEmpty()) {
                Patient p = waitingPatients.poll();
                p.setDoctorsNote(doctorsNoteTextField.getText());
                dischargedPatients.add(p);
                p.setDischarged(true);
            }
        }
    }
}