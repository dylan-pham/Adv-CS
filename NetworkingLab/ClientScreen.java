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
 
public class ClientScreen extends JPanel implements ActionListener, MouseListener {
 
    Game game;
    private int playerXWins;
    private int playerOWins;
    private JButton twoPlayerButton;
    private JButton onePlayerButton;
    private JLabel playerXWinsLabel;
    private JLabel playerOWinsLabel;
    private boolean endGame;
    private int mode;
    private JTextArea winnerTextArea;
     
    public ClientScreen() {
        this.setLayout(null);
         
        setLayout(null);

        game = new Game();

        playerXWins = 0;
        playerOWins = 0;

        twoPlayerButton = new JButton("2 Player");
        twoPlayerButton.setBounds(740, 100, 100, 30);
        twoPlayerButton.addActionListener(this);
        this.add(twoPlayerButton);

        onePlayerButton = new JButton("1 Player");
        onePlayerButton.setBounds(860, 100, 100, 30);
        onePlayerButton.addActionListener(this);
        this.add(onePlayerButton);

        playerXWinsLabel = new JLabel("Player X Wins: " + playerXWins);
        playerXWinsLabel.setBounds(800, 150, 110, 30);
        this.add(playerXWinsLabel);

        playerOWinsLabel = new JLabel("Player O Wins: " + playerOWins);
        playerOWinsLabel.setBounds(800, 200, 110, 30);
        this.add(playerOWinsLabel);

        endGame = true;

        winnerTextArea = new JTextArea("Select Mode");
        winnerTextArea.setBounds(300, 750, 200, 30);
        this.add(winnerTextArea);

        addMouseListener(this);
        this.setFocusable(true);
    }

    private void initializeGame() {
        endGame = false;
        twoPlayerButton.setVisible(false);
        onePlayerButton.setVisible(false);
        winnerTextArea.setVisible(false);
        game.reset();
        repaint();
    }
 
 
    public Dimension getPreferredSize() {
        return new Dimension(1000, 600);
    }

    private void endGame() {
        endGame = true;
        twoPlayerButton.setVisible(true);        
        onePlayerButton.setVisible(true);
        winnerTextArea.setVisible(true);
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

        if (game.getTurn() == 1 && !endGame) {
            playerOWinsLabel.setForeground(Color.BLACK);
            playerXWinsLabel.setForeground(Color.RED);
        } else if (game.getTurn() == 2 && !endGame) {
            playerXWinsLabel.setForeground(Color.BLACK);
            playerOWinsLabel.setForeground(Color.RED);
        }
        
        game.drawMe(g);
        
        if ((game.checkTicTacToe() != 0 || game.checkFull() == true) && !endGame) {
            checkEndGameConditions(game.checkTicTacToe());
            endGame();
        }
    } 

    public void blockPlayer() {
        if (game.getSpot(0, 0) == 1 && game.getSpot(0, 1) == 1 && game.getSpot(0, 2) == 0) {
            game.insertXO(0, 2);
        } else if (game.getSpot(0, 1) == 1 && game.getSpot(0, 2) == 1 && game.getSpot(0, 0) == 0) {
            game.insertXO(0, 0);
        } else if (game.getSpot(1, 0) == 1 && game.getSpot(1, 1) == 1 && game.getSpot(1, 2) == 0) {
            game.insertXO(1, 2);
        } else if (game.getSpot(1, 1) == 1 && game.getSpot(1, 2) == 1 && game.getSpot(1, 0) == 0) {
            game.insertXO(1, 0);
        } else if (game.getSpot(2, 0) == 1 && game.getSpot(2, 1) == 1 && game.getSpot(2, 2) == 0) {
            game.insertXO(2, 2);
        } else if (game.getSpot(2, 1) == 1 && game.getSpot(2, 2) == 1 && game.getSpot(2, 0) == 0) {
            game.insertXO(2, 0);
        } else if (game.getSpot(0, 0) == 1 && game.getSpot(1, 0) == 1 && game.getSpot(2, 0) == 0) {
            game.insertXO(2, 0);
        } else if (game.getSpot(1, 0) == 1 && game.getSpot(2, 0) == 1 && game.getSpot(0, 0) == 0) {
            game.insertXO(0, 0);
        } else if (game.getSpot(0, 1) == 1 && game.getSpot(1, 1) == 1 && game.getSpot(2, 1) == 0) {
            game.insertXO(2, 1);
        } else if (game.getSpot(2, 1) == 1 && game.getSpot(1, 1) == 1 && game.getSpot(0, 1) == 0) {
            game.insertXO(0, 1);
        } else if (game.getSpot(0, 2) == 1 && game.getSpot(1, 2) == 1 && game.getSpot(2, 2) == 0) {
            game.insertXO(2, 2);
        } else if (game.getSpot(2, 2) == 1 && game.getSpot(1, 2) == 1 && game.getSpot(0, 2) == 0) {
            game.insertXO(0, 2);
        } else if (game.getSpot(0, 0) == 1 && game.getSpot(1, 1) == 1 && game.getSpot(2, 2) == 0) {
            game.insertXO(2, 2);
        } else if (game.getSpot(1, 1) == 1 && game.getSpot(2, 2) == 1 && game.getSpot(0, 0) == 0) {
            game.insertXO(0, 0);
        } else if (game.getSpot(0, 2) == 1 && game.getSpot(1, 1) == 1 && game.getSpot(2, 0) == 0) {
            game.insertXO(2, 0);
        } else if (game.getSpot(2, 0) == 1 && game.getSpot(1, 1) == 1 && game.getSpot(0, 2) == 0) {
            game.insertXO(0, 2);
        } else {
            if ((game.checkTicTacToe() != 0 || game.checkFull() == true) && !endGame) {
                checkEndGameConditions(game.checkTicTacToe());
                endGame();
            }

            int row = (int)(Math.random() * 3);
            int column = (int)(Math.random() * 3);

            while (game.getSpot(row, column) != 0 && !endGame) {
                row = (int)(Math.random() * 3);
                column = (int)(Math.random() * 3);
                System.out.println("stuck");
            }

            game.insertXO(row, column);
        }
    }

    public void mousePressed(MouseEvent e) {
        // x's and o's can only be placed during game
        if (!endGame) {
            if (mode == 2) {
                if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 100 && e.getY() <= 300) {
                    game.insertXO(0, 0);
                }

                if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 100 && e.getY() <= 300) {
                    game.insertXO(0, 1);
                }

                if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 100 && e.getY() <= 300) {
                    game.insertXO(0, 2);
                }

                if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 301 && e.getY() <= 500) {
                    game.insertXO(1, 0);
                }

                if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 301 && e.getY() <= 500) {
                    game.insertXO(1, 1);
                }

                if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 301 && e.getY() <= 500) {
                    game.insertXO(1, 2);
                }

                if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 501 && e.getY() <= 700) {
                    game.insertXO(2, 0);
                }

                if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 501 && e.getY() <= 700) {
                    game.insertXO(2, 1);
                }

                if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 501 && e.getY() <= 700) {
                    game.insertXO(2, 2);
                }

                playPlacingMarkerSound();
            } else {
                if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 100 && e.getY() <= 300) {
                    game.insertXO(0, 0);
                }

                if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 100 && e.getY() <= 300) {
                    game.insertXO(0, 1);
                }

                if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 100 && e.getY() <= 300) {
                    game.insertXO(0, 2);
                }

                if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 301 && e.getY() <= 500) {
                    game.insertXO(1, 0);
                }

                if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 301 && e.getY() <= 500) {
                    game.insertXO(1, 1);
                }

                if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 301 && e.getY() <= 500) {
                    game.insertXO(1, 2);
                }

                if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 501 && e.getY() <= 700) {
                    game.insertXO(2, 0);
                }

                if (e.getX() >= 301 && e.getX() <= 500 && e.getY() >= 501 && e.getY() <= 700) {
                    game.insertXO(2, 1);
                }

                if (e.getX() >= 501 && e.getX() <= 700 && e.getY() >= 501 && e.getY() <= 700) {
                    game.insertXO(2, 2);
                }

                playPlacingMarkerSound();
                blockPlayer();
            }
        }
 
        repaint();
    }
 
    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == twoPlayerButton) {
            mode = 2;
            initializeGame();
        }

        if (e.getSource() == onePlayerButton) {
            mode = 1;
            initializeGame();
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
        String hostName = "localhost";
        int portNumber = 1024;
         
        try {
            Socket serverSocket = new Socket(hostName, portNumber);
             
            ObjectInputStream inObj = new ObjectInputStream(serverSocket.getInputStream());
            ObjectOutputStream outObj = new ObjectOutputStream(serverSocket.getOutputStream());
             
            String serverMessage = (String) inObj.readObject(); 
            System.out.println(serverMessage);
            
            while (true) {
                Game game2 = (Game)inObj.readObject(); 
                game = game2;  
                repaint();
                
                outObj.reset();
                outObj.writeObject(game);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}