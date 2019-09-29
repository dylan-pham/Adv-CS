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

// for testing purposes
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Screen extends JPanel implements ActionListener, MouseListener {
    private String nameList;
    private JTextArea nameListTextArea;
    private JScrollPane scrollPane;
    private ArrayList<Student> students;
    private ListIterator<Student> studentsIterator;
    private JLabel statusBar;
    private JTextField searchBar;
    private JButton binarySearchButton;
    private JButton sequentialSearchButton;
    private JButton bubbleSortButton;
    private JButton mergeSortButton;
    private JButton scrambleButton;
    private int passes;

    public Screen() {
        setLayout(null);
        setFocusable(true);

        nameListTextArea = new JTextArea(760, 660);
        nameListTextArea.setEditable(false);
        this.add(nameListTextArea);

        scrollPane = new JScrollPane(nameListTextArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 20, 760, 660);
        this.add(scrollPane);

        students = new ArrayList<Student>();

        try {
            Scanner fileScanner = new Scanner(new File("names.txt"));

            boolean added = false;
            while (fileScanner.hasNext()){
                String firstName = fileScanner.next();
                String lastName = fileScanner.next();

                studentsIterator = students.listIterator();
                while (studentsIterator.hasNext()) {
                    if ((studentsIterator.next().getLastName().compareTo(lastName)) > 0) {
                        studentsIterator.previous();
                        studentsIterator.add(new Student(firstName, lastName, (int)(Math.random() * 5 + 14)));
                        added = true;
                        break;
                    }
                }   

                if (!added)
                    studentsIterator.add(new Student(firstName, lastName, (int)(Math.random() * 4 + 14)));
            }
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        updateNameList();

        statusBar = new JLabel("<status bar>");
        statusBar.setBounds(20, 680, 640, 20);
        this.add(statusBar);

        searchBar = new JTextField();
        searchBar.setBounds(20, 720, 140, 20);
        this.add(searchBar);

        binarySearchButton = new JButton("Binary Search");
        binarySearchButton.setBounds(170, 720, 140, 20);
        this.add(binarySearchButton);
        binarySearchButton.addActionListener(this);
    
        sequentialSearchButton = new JButton("Sequential Search");
        sequentialSearchButton.setBounds(320, 720, 140, 20);
        this.add(sequentialSearchButton);
        sequentialSearchButton.addActionListener(this);

        bubbleSortButton = new JButton("Bubble Sort");
        bubbleSortButton.setBounds(470, 720, 140, 20);
        this.add(bubbleSortButton);
        bubbleSortButton.addActionListener(this);

        mergeSortButton = new JButton("Merge Sort");
        mergeSortButton.setBounds(620, 720, 140, 20);
        this.add(mergeSortButton);
        mergeSortButton.addActionListener(this);

        scrambleButton = new JButton("Scramble");
        scrambleButton.setBounds(320, 760, 140, 20);
        this.add(scrambleButton);
        scrambleButton.addActionListener(this);

        addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == binarySearchButton) {
            binarySearch(searchBar.getText(), 0, students.size(), 0);
        } else if (e.getSource() == sequentialSearchButton) {
            sequentialSearch(searchBar.getText());
        } else if (e.getSource() == bubbleSortButton) {
            bubbleSort();
            updateNameList();
        } else if (e.getSource() == mergeSortButton) {
            mergeSort(0, students.size());
        } else if (e.getSource() == scrambleButton) {
            scramble();
            updateNameList();
        }

    }

    public void updateNameList() {
        nameList = "";

        for (Student each : students) {
            nameList += each + "\n";
        }

        nameListTextArea.setText(nameList);
    }
    
    public void binarySearch(String query, int start, int end, int passes) {
        bubbleSort(); // array needs to be in order

        passes++;
        boolean found = false;
        int middle = (start + end)/2;

        if (start <= end) {
            if (students.get(middle).getLastName().equals(query)) {
                found = true;
            } else if (students.get(middle).getLastName().compareTo(query) > 0) {
                binarySearch(query, start, middle-1, passes);
            } else if (students.get(middle).getLastName().compareTo(query) < 0) {
                binarySearch(query, middle+1, end, passes);
            }
        }

        if (found) {
            nameListTextArea.setText(students.get(middle).toString());
            statusBar.setText("Student found | Passes: " + passes + " | Index: " + middle);
        }
    }
    
    public void sequentialSearch(String query) {
        int passes = 0;

        for (int i = 0; i < students.size(); i++) {
            passes++;

            if (students.get(i).getLastName().equals(query)) {
                nameListTextArea.setText(students.get(i).toString());
                statusBar.setText("Student found | Passes: " + passes + " | Index: " + i);
                break;
            }
        }
    }

    public void bubbleSort() {
        boolean sorted = false;
        int passes = 0;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < students.size()-1; i++) {
                for (int j = i+1; j < students.size(); j++) {
                    passes++;

                    Student s1 = students.get(i);
                    Student s2 = students.get(j);

                    if (s1.getLastName().compareTo(s2.getLastName()) > 0) {
                        students.set(i, s2);
                        students.set(j, s1);
                        sorted = false;
                    }
                }
            }
        }

        statusBar.setText("Passes: " + passes);
    }

    public void mergeSort(int start, int end) {
        passes = 0;
        int middle = (start + end)/2;

        if (middle == start) {
            return;
        }

        mergeSort(start, middle);
        mergeSort(middle, end);
        merge(start, end);

        statusBar.setText("Passes: " + passes);
        updateNameList();
    }

    public void merge(int start, int end) {
        passes++;
        
        ArrayList<Student> temp = new ArrayList<Student>();
        for (int i = 0; i < end-start+1; i++) {
            temp.add(new Student("", "", 0));
        }

        int middle = (start+end)/2;

        int k = 0;
        int i = start;
        int j = middle;

        while (i < middle && j < end) {
            if (students.get(i).getLastName().compareTo(students.get(j).getLastName()) < 0) {
                temp.set(k, students.get(i));
                i++;
            } else {
                temp.set(k, students.get(j));
                j++;
            }
            k++;
        }

        while (i < middle) {
            temp.set(k, students.get(i));
            i++;
            k++;
        }
        
        while (j < end){
            temp.set(k, students.get(j));
            j++;
            k++;
        }
      
        for (i = 0; i < end-start; i++) {
            students.set(start+i, temp.get(i));
        }
    }

    public void scramble() {
        for (int i = 0; i < students.size(); i++) {
            int rand = (int)(Math.random() * students.size());

            Student s1 = students.get(i);
            Student s2 = students.get(rand);

            students.set(i, s2);
            students.set(rand, s1);
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