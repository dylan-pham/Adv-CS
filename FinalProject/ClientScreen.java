import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JTextArea;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
import java.io.*;
import java.net.*;
import java.awt.*;  
import java.util.*;
 
public class ClientScreen extends JPanel implements MouseListener, ActionListener, KeyListener {
    Game game;
    
    Socket serverSocket;
    Socket clientSocket;
    ObjectOutputStream outObj;
    ObjectInputStream inObj;

    private Image harold;

    public ClientScreen() {
        this.setLayout(null);
        setLayout(null);
        addMouseListener(this);
        this.setFocusable(true);
        addKeyListener(this);
        
        try {
            String hostName = "localhost";
            int portNumber = 1024;
            serverSocket = new Socket(hostName, portNumber);
            inObj = new ObjectInputStream(serverSocket.getInputStream());
            outObj = new ObjectOutputStream(serverSocket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }

        try {
            harold = ImageIO.read(new File("img/harold.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);;
        } catch ( IOException e ){
            System.out.println(e);
        }

        game = new Game();
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing grid
        int x = 0;
        int y = 0;
        for (int i = 0; i < 9; i++) {
            g.fillRect(x, 0, 1, 800);
            g.fillRect(0, y, 800, 1);
            x += 100;
            y += 100;
        }

        g.drawImage(harold, 0, 0, null);
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void mousePressed(MouseEvent e) {
        
    }
 
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        
    }

    public void poll() throws IOException{
        try {   
            while (true) {
                Game game2 = (Game)inObj.readObject(); 
                game = game2;
                repaint();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        } catch (IOException e) {
            System.exit(1);
        }
    }
}