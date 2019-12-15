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
import java.util.Stack;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    private Image pacman;
    private Image life;
    private Image player1wins;
    private Image player2wins;
    private int playerX;
    private int playerY;
    private HashMap<String, Image> itemImages;
    private Stack<Integer> lives;
    private int bombsPlaced;

    public ClientScreen() {
        setLayout(null);
        addMouseListener(this);
        this.setFocusable(true);
        addKeyListener(this);

        game = new Game();
        
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
            harold = ImageIO.read(new File("img/harold.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        } catch ( IOException e ){
            System.out.println(e);
        }

        try {
            pacman = ImageIO.read(new File("img/pacman.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        } catch ( IOException e ){
            System.out.println(e);
        }

        try {
            life = ImageIO.read(new File("img/life.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        } catch ( IOException e ){
            System.out.println(e);
        }

        try {
            player1wins = ImageIO.read(new File("img/player1wins.png")).getScaledInstance(1100, 800, Image.SCALE_DEFAULT);
        } catch ( IOException e ){
            System.out.println(e);
        }

        try {
            player2wins = ImageIO.read(new File("img/player2wins.png")).getScaledInstance(1100, 800, Image.SCALE_DEFAULT);
        } catch ( IOException e ){
            System.out.println(e);
        }

        playerX = 0;
        playerY = 0;

        itemImages = new HashMap<String, Image>();
        try {
            Image avocado = ImageIO.read(new File("img/avocado.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("avocado", avocado);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image ball = ImageIO.read(new File("img/ball.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("ball", ball);
        } catch ( IOException e ){
            System.out.println(e);
        }

        try {
            Image banana = ImageIO.read(new File("img/banana.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("banana", banana);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image book = ImageIO.read(new File("img/book.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("book", book);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image coin = ImageIO.read(new File("img/coin.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("coin", coin);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image fries = ImageIO.read(new File("img/fries.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("fries", fries);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image guitar = ImageIO.read(new File("img/guitar.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("guitar", guitar);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image hat = ImageIO.read(new File("img/hat.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("hat", hat);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image pokemon = ImageIO.read(new File("img/pokemon.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("pokemon", pokemon);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image socks = ImageIO.read(new File("img/socks.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("socks", socks);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image yeezys = ImageIO.read(new File("img/yeezys.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("yeezys", yeezys);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image bomb = ImageIO.read(new File("img/bomb.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("bomb", bomb);
        } catch ( IOException e ){
            System.out.println(e);
        }
        try {
            Image fire = ImageIO.read(new File("img/fire.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            itemImages.put("fire0", fire);
            itemImages.put("fire1", fire);
            itemImages.put("fire2", fire);
            itemImages.put("fire3", fire);
            itemImages.put("fire4", fire);
            itemImages.put("fire5", fire);
            itemImages.put("fire6", fire);
            itemImages.put("fire7", fire);
            itemImages.put("fire8", fire);
            itemImages.put("fire9", fire);
        } catch ( IOException e ){
            System.out.println(e);
        }

        lives = new Stack<Integer>();
        lives.push(0);
        lives.push(0);
        lives.push(0);

        bombsPlaced = 0;
    }

    public Dimension getPreferredSize() {
        return new Dimension(1100, 800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (game.checkWinner().equals("player1")) {
            g.drawImage(player1wins, 0, 0, null);
        } else if (game.checkWinner().equals("player2")) {
            g.drawImage(player2wins, 0, 0, null);
        } else {
            // drawing grid
            int x = 0;
            int y = 0;
            for (int i = 0; i < 9; i++) {
                g.fillRect(x, 0, 1, 800);
                g.fillRect(0, y, 800, 1);
                x += 100;
                y += 100;
            }
    
            // drawing lives
            for (int i = 0; i < lives.size(); i++) {
                g.drawImage(life, 1000, i * 100, null);
            }
    
            // drawing game items
            HashMap<String, String> gameItems = game.getPlacedGameItems();
            for (String each : gameItems.keySet()) {
                String itemName = gameItems.get(each);
                g.drawImage(itemImages.get(itemName), Integer.parseInt(each.split(" ")[0]) * 100, Integer.parseInt(each.split(" ")[1]) * 100, null);
            }
    
            try {
                outObj.reset();
                outObj.writeObject(game);
            } catch (IOException ef) {
                System.err.println("Couldn't get I/O for the connection");
                System.exit(1);
            }
            
            repaint();
    
            // drawing collected items
            ArrayList<String> collectedItems = game.getCollectedItems2();
            int y2 = 0;
            int x2 = 800;
            for (String each : collectedItems) {
                g.drawImage(itemImages.get(each), x2, y2, null);
                y2 += 100;
                repaint();
    
                if (y2 == 800) {
                    y2 = 0;
                    x2 = 900;
                }
            }
    
            // drawing player
            g.drawImage(harold, playerX, playerY, null);
            g.drawImage(pacman, Integer.parseInt(game.getPlayer1Coordinate().split(" ")[0]), Integer.parseInt(game.getPlayer1Coordinate().split(" ")[1]), null);
        }
    }

    public void checkCollision() {
        HashMap<String, String> gameItems = game.getPlacedGameItems();
        String coordinate = playerX/100 + " " + playerY/100;
        if (gameItems.containsKey(coordinate)) {
            String itemName = gameItems.get(coordinate);

            if (itemName != null && !itemName.contains("fire") && !itemName.equals("bomb")) {
                game.destroyItem(coordinate);
                game.addToCollectedItems2(itemName);

                // playGetItemSound();

                if (game.getCollectedItems2().size() > 5) {
                    game.setWinner("player2");
                }
            } else if (itemName != null) {
                playerX = 0;
                playerY = 0;

                if (lives.size() > 1) {
                    lives.pop();
                    playLoseLifeSound();
                } else {
                    game.setWinner("player1");
                }

            }
        }
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
        } else if (e.getKeyCode() == 32) {
            if (bombsPlaced < 6) {
                game.placeObstacle();
                bombsPlaced++;
            }
        }

        checkCollision();
        game.setPlayer2Coordinate(playerX + " " + playerY);

        try {
            outObj.reset();
            outObj.writeObject(game);
        } catch (IOException ef) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }

    public void playGetItemSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("audio/getitem.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void playLoseLifeSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("audio/loselife.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    
    public void mousePressed(MouseEvent e) {
        
    }

    public void actionPerformed(ActionEvent e) {}
    
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

    // required methods
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}