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
 
import java.io.*;
import java.net.*;
import java.awt.*;  
import java.util.*;
 
public class ClientScreen extends JPanel implements MouseListener, ActionListener {
    Game game;
    private int playerXWins;
    private int playerOWins;
    private JLabel playerXWinsLabel;
    private JLabel playerOWinsLabel;
    private JTextArea winnerTextArea;
    private JTextArea player2TextArea;
    private JButton resetButton;
    
    Socket serverSocket;
    Socket clientSocket;
    ObjectOutputStream outObj;
    ObjectInputStream inObj;

    public ClientScreen() {
        this.setLayout(null);
         
        setLayout(null);

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

        game = new Game();

        playerXWins = 0;
        playerOWins = 0;

        playerXWinsLabel = new JLabel("Player 1 Wins: " + playerXWins);
        playerXWinsLabel.setBounds(800, 150, 110, 30);
        this.add(playerXWinsLabel);

        playerOWinsLabel = new JLabel("Player 2 Wins: " + playerOWins);
        playerOWinsLabel.setBounds(800, 200, 110, 30);
        this.add(playerOWinsLabel);

        winnerTextArea = new JTextArea("Select Mode");
        winnerTextArea.setBounds(300, 750, 200, 30);
        this.add(winnerTextArea);

        player2TextArea = new JTextArea("Player 2");
        player2TextArea.setBounds(300, 710, 200, 30);
        this.add(player2TextArea);

        addMouseListener(this);
        this.setFocusable(true);

        resetButton = new JButton("Reset");
        resetButton.setBounds(800, 300, 100, 30);
        this.add(resetButton);
        resetButton.addActionListener(this);
        resetButton.setVisible(false);

        initializeGame();
    }

    public void initializeGame() {
        winnerTextArea.setVisible(false);
        game.reset();
        repaint();
    }
 
 
    public Dimension getPreferredSize() {
        return new Dimension(1000, 800);
    }

    private void checkEndGameConditions(int winner) {
        if (winner == 1) {
            playerXWins++;
            playerXWinsLabel.setText("Player X Wins: " + playerXWins);
            playWinSound();
            winnerTextArea.setText("Player X Wins!");
        } else if (winner == 2) {
            playerOWins++;
            playerOWinsLabel.setText("Player O Wins: " + playerOWins);
            playWinSound();
            winnerTextArea.setText("Player O Wins!");
        } else {
            playTieSound();
            winnerTextArea.setText("Tie.");
        }
    }
     
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (game.getTurn() == 1) {
            playerOWinsLabel.setForeground(Color.BLACK);
            playerXWinsLabel.setForeground(Color.RED);
        } else if (game.getTurn() == 2) {
            playerXWinsLabel.setForeground(Color.BLACK);
            playerOWinsLabel.setForeground(Color.RED);
        }
        
        int[][] board = game.getBoard();

        // drawing board
        g.fillRect(100, 100, 600, 5);
        g.fillRect(100, 100, 5, 600);
        g.fillRect(100, 700, 600, 5);
        g.fillRect(700, 100, 5, 600);
        g.fillRect(300, 100, 5, 600);
        g.fillRect(500, 100, 5, 600);
        g.fillRect(100, 300, 600, 5);
        g.fillRect(100, 500, 600, 5);

        int x = 200;
        int y = 200;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 1) {
                    g.drawString("X", x, y);
                } else if (board[r][c] == 2) {
                    g.drawString("O", x, y);
                }
                x += 200;
            }
            x = 200;
            y += 200;
        }
        
        if (!game.getEndGame()) {
            checkEndGameConditions(game.checkTicTacToe());
            resetButton.setVisible(false);

        } else {
            resetButton.setVisible(true);
        }
    }

    public void mousePressed(MouseEvent e) {
        // x's and o's can only be placed during game
        if (game.getTurn() == 2) {
            if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 100 && e.getY() <= 300) {
                game.insertXO(0, 0);
            }

            else if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 100 && e.getY() <= 300) {
                game.insertXO(0, 1);
            }

            else if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 100 && e.getY() <= 300) {
                game.insertXO(0, 2);
            }

            else if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 301 && e.getY() <= 500) {
                game.insertXO(1, 0);
            }

            else if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 301 && e.getY() <= 500) {
                game.insertXO(1, 1);
            }

            else if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 301 && e.getY() <= 500) {
                game.insertXO(1, 2);
            }

            else if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 501 && e.getY() <= 700) {
                game.insertXO(2, 0);
            }

            else if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 501 && e.getY() <= 700) {
                game.insertXO(2, 1);
            }

            else if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 501 && e.getY() <= 700) {
                game.insertXO(2, 2);
            }
            try {
                outObj.reset();
                outObj.writeObject(game);
                playPlacingMarkerSound();
            } catch (IOException ef) {
                // System.err.println("Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            }
        }

        repaint();
    }
 
    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            initializeGame();
            repaint();

            try {
                outObj.reset();
                outObj.writeObject(game);
            } catch (IOException ef) {
                System.err.println("Couldn't get I/O for the connection");
                System.exit(1);
            }
        }
    }

    public void playTieSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("audio/tie.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void playWinSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("audio/win.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void playPlacingMarkerSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("audio/marker.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
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
            // System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}