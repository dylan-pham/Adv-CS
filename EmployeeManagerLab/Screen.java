import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener {
    private ArrayList<Employee> employees;
    private ArrayList<Employee> queriedEmployees;
    private JTextArea d0;
    private JTextArea d1;
    private JTextArea d2;
    private JTextArea d3;
    private JTextArea d4;
    private JTextArea d5;
    private JTextArea d6;
    private JTextArea d7;
    private JButton showTeachers;
    private JButton showPolice;
    private JButton showEngineers;
    private JButton showBankers;
    private JButton showEveryone;
    private JTextField searchBar;
    private JButton search;
    private ArrayList<JTextArea> descriptions;
    private ArrayList<JButton> delete;
    private JButton del0;
    private JButton del1;
    private JButton del2;
    private JButton del3;
    private JButton del4;
    private JButton del5;
    private JButton del6;
    private JButton del7;

    public Screen() {
        setLayout(null);
        setFocusable(true);
        
        employees = new ArrayList<Employee>();
        queriedEmployees = new ArrayList<Employee>();
        
        employees.add(new Government("Jane", "images/jane.jpg", "teacher", "San Jose", 150000.00)); // teacher
        employees.add(new Government("Jimmy", "images/jimmy.jpg", "teacher", "Oakland", 150000.00)); 
        employees.add(new Government("David", "images/david.jpg", "police officer", "Los Altos", 130000.00)); // police officer
        employees.add(new Government("Todd", "images/todd.jpg", "police officer", "Mountain View", 130000.00));
        employees.add(new Company("Leo", "images/leo.png", "engineer", "Uber", 300000.00)); // engineer
        employees.add(new Company("Chris", "images/chris.jpg", "engineer", "Apple", 350000.00));
        employees.add(new Company("Leonard", "images/leonard.jpg", "banker", "TD Bank", 500000.00)); // banker
        employees.add(new Company("DeAndre", "images/deandre.jpg", "banker", "Santander", 800000.00));
        
        d0 = new JTextArea();
        d0.setBounds(130, 100, 140, 80);
        d0.setEditable(false);
        this.add(d0);

        d1 = new JTextArea();
        d1.setBounds(390, 100, 140, 80);
        d1.setEditable(false);
        this.add(d1);

        d2 = new JTextArea();
        d2.setBounds(650, 100, 140, 80);
        d2.setEditable(false);
        this.add(d2);

        d3 = new JTextArea();
        d3.setBounds(130, 220, 140, 80);
        d3.setEditable(false);
        this.add(d3);

        d4 = new JTextArea();
        d4.setBounds(390, 220, 140, 80);
        d4.setEditable(false);
        this.add(d4);

        d5 = new JTextArea();
        d5.setBounds(650, 220, 140, 80);
        d5.setEditable(false);
        this.add(d5);

        d6 = new JTextArea();
        d6.setBounds(130, 340, 140, 80);
        d6.setEditable(false);
        this.add(d6);

        d7 = new JTextArea();
        d7.setBounds(390, 340, 140, 80);
        d7.setEditable(false);
        this.add(d7);

        descriptions = new ArrayList<JTextArea>();
        descriptions.add(d0);
        descriptions.add(d1);
        descriptions.add(d2);
        descriptions.add(d3);
        descriptions.add(d4);
        descriptions.add(d5);
        descriptions.add(d6);
        descriptions.add(d7);

        showTeachers = new JButton("Teachers");
        showTeachers.setBounds(20, 20, 100, 50);
        this.add(showTeachers);
        showTeachers.addActionListener(this);

        showPolice = new JButton("Police officers");
        showPolice.setBounds(140, 20, 100, 50);
        this.add(showPolice);
        showPolice.addActionListener(this);
        
        showEngineers = new JButton("Engineers");
        showEngineers.setBounds(260, 20, 100, 50);
        this.add(showEngineers);
        showEngineers.addActionListener(this);

        showBankers = new JButton("Bankers");
        showBankers.setBounds(380, 20, 100, 50);
        this.add(showBankers);
        showBankers.addActionListener(this);

        showEveryone = new JButton("Everyone");
        showEveryone.setBounds(500, 20, 100, 50);
        this.add(showEveryone);
        showEveryone.addActionListener(this);

        searchBar = new JTextField();
        searchBar.setBounds(620, 20, 100, 25);
        this.add(searchBar);

        search = new JButton("Search");
        search.setBounds(620, 45, 100, 25);
        this.add(search);
        search.addActionListener(this);

        queriedEmployees = employees;

        delete = new ArrayList<JButton>();

        del0 = new JButton("delete profile");
        del0.setBounds(140, 180, 100, 20);
        this.add(del0);
        del0.addActionListener(this);

        del1 = new JButton("delete profile");
        del1.setBounds(400, 180, 100, 20);
        this.add(del1);
        del1.addActionListener(this);

        del2 = new JButton("delete profile");
        del2.setBounds(660, 180, 100, 20);
        this.add(del2);
        del2.addActionListener(this);

        del3 = new JButton("delete profile");
        del3.setBounds(140, 300, 100, 20);
        this.add(del3);
        del3.addActionListener(this);

        del4 = new JButton("delete profile");
        del4.setBounds(400, 300, 100, 20);
        this.add(del4);
        del4.addActionListener(this);

        del5 = new JButton("delete profile");
        del5.setBounds(660, 300, 100, 20);
        this.add(del5);
        del5.addActionListener(this);

        del6 = new JButton("delete profile");
        del6.setBounds(140, 420, 100, 20);
        this.add(del6);
        del6.addActionListener(this);

        del7 = new JButton("delete profile");
        del7.setBounds(400, 420, 100, 20);
        this.add(del7);
        del7.addActionListener(this);

        delete.add(del0);
        delete.add(del1);
        delete.add(del2);
        delete.add(del3);
        delete.add(del4);
        delete.add(del5);
        delete.add(del6);
        delete.add(del7);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 700);
    }

    public void adjustDescriptions() {
        for (int i = 0; i < descriptions.size(); i++) {
            if (i > queriedEmployees.size() - 1) {
                descriptions.get(i).setVisible(false);
            } else {
                descriptions.get(i).setVisible(true);
            }
        }

        for (int i = 0; i < delete.size(); i++) {
            if (i > queriedEmployees.size() - 1) {
                delete.get(i).setVisible(false);
            } else {
                delete.get(i).setVisible(true);
            }
        }

        for (int i = 0; i < queriedEmployees.size(); i++) {
            descriptions.get(i).setText(queriedEmployees.get(i).toString());
        }
    }

    public void queryByJobTitle(String jobTitle) {
        ArrayList<Employee> temp = new ArrayList<Employee>();

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getJobTitle().equals(jobTitle)) {
                temp.add(employees.get(i));
            }
        }

        queriedEmployees = temp;

        repaint();
    }

    public void queryByName(String name) {
        ArrayList<Employee> temp = new ArrayList<Employee>();

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(name)) {
                temp.add(employees.get(i));
            }
        }

        queriedEmployees = temp;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

		g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 700);
        
        adjustDescriptions();
        
        int x = 20;
        int y = 100;

        int counter = 0;
		for (int i = 0; i < queriedEmployees.size(); i++) {

            if (counter == 3) {
                x = 20;
                y += 120;
                counter = 0;
            }

            queriedEmployees.get(i).drawPhoto(g, x, y);

            x += 260;
            counter++;
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            try {
                String name = searchBar.getText();
                queryByName(name);
			} catch (Exception exception) {
				;
			}
        }

        if (e.getSource() == showTeachers) {
            queryByJobTitle("teacher");
        }

        if (e.getSource() == showPolice) {
            queryByJobTitle("police officer");            
        }

        if (e.getSource() == showEngineers) {
            queryByJobTitle("engineer");            
        }

        if (e.getSource() == showBankers) {
            queryByJobTitle("banker");            
        }

        if (e.getSource() == showEveryone) {
            queriedEmployees = employees;
            repaint();
        }

        if (e.getSource() == del0) {
            employees.remove(queriedEmployees.get(0));
            queriedEmployees.remove(0);
            repaint();
        }

        if (e.getSource() == del1) {
            employees.remove(queriedEmployees.get(1));
            queriedEmployees.remove(1);
        }

        if (e.getSource() == del2) {
            employees.remove(queriedEmployees.get(2));        
            queriedEmployees.remove(2);
        }

        if (e.getSource() == del3) {
            employees.remove(queriedEmployees.get(3));
            queriedEmployees.remove(3);
        }

        if (e.getSource() == del4) {
            employees.remove(queriedEmployees.get(4));
            queriedEmployees.remove(4);
        }

        if (e.getSource() == del5) {
            employees.remove(queriedEmployees.get(5));            
            queriedEmployees.remove(5);
        }

        if (e.getSource() == del6) {
            employees.remove(queriedEmployees.get(6));
            queriedEmployees.remove(6);
        }

        if (e.getSource() == del7) {
            employees.remove(queriedEmployees.get(7));        
            queriedEmployees.remove(7);
        }
     }
}