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
import java.util.HashMap;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.ArrayList;
 
import java.io.*;
import java.net.*;
import java.awt.*;  
import java.util.*;
 
public class ServerScreen extends JPanel implements MouseListener, ActionListener, KeyListener {
    Game game;

    ObjectOutputStream outObj;
    ObjectInputStream inObj;
    ServerSocket serverSocket;
    Socket clientSocket;

    private Image pacman;
    private int playerX;
    private int playerY;
    private HashMap<String, Image> itemImages;
    
    public ServerScreen() {
        this.setLayout(null);
        setLayout(null);
        addMouseListener(this);
        this.setFocusable(true);
        addKeyListener(this);
        
        try {
            int portNumber = 1024;
            serverSocket = new ServerSocket(portNumber);
            clientSocket = serverSocket.accept();
            outObj = new ObjectOutputStream(clientSocket.getOutputStream());
            inObj = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }

        try {
            pacman = ImageIO.read(new File("img/pacman.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        } catch ( IOException e ){
            System.out.println(e);
        }

        playerX = 0;
        playerY = 0;

        itemImages = new HashMap<String, Image>();

        try {
            Image ball = ImageIO.read(new File("img/ball.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("ball", ball);
        } catch ( IOException e ){
            System.out.println(e);
        }

        try {
            Image hat = ImageIO.read(new File("img/hat.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("hat", hat);
        } catch ( IOException e ){
            System.out.println(e);
        }

        game = new Game();
    }

    public Dimension getPreferredSize() {
        return new Dimension(900, 800);
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

        // drawing game items
        HashMap<Coordinate, String> gameItems = game.getGameItems();
        for (Coordinate each : gameItems.keySet()) {
            String itemName = gameItems.get(each);
            g.drawImage(itemImages.get(itemName), each.getX() * 100, each.getY() * 100, null);
            
            // checking collision
            if (playerX == each.getX() * 100 && playerY == each.getY() * 100 && itemName != null) {
                game.destroyItem(each, itemName);
                repaint();
            }
        }

        // drawing collected items
        ArrayList<String> collectedItems = game.getCollectedItems();
        int y2 = 0;
        for (String each : collectedItems) {
            g.drawImage(itemImages.get(each), 800, y2, null);
            y2 += 100;
            repaint();
        }

        g.drawImage(pacman, playerX, playerY, null);
    } 
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39 && playerX != 700) {
            playerX += 100;
        } else if (e.getKeyCode() == 37 && playerX != 0) {
            playerX -= 100;
        } else if (e.getKeyCode() == 40 && playerY != 700) {
            playerY += 100;
        } else if (e.getKeyCode() == 38  && playerY != 0) {
            playerY -= 100;
        }

        repaint();
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
 
    public void poll() throws IOException {
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
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }
}