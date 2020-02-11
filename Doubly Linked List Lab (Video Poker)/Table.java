import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 
public class Table extends JPanel implements ActionListener {
    private DLList<Card> playerHand;
    private int playerTotal;
    private JLabel playerTotalLabel;
    
    private JButton newGameButton;
    private JButton drawButton;
    private DLList<Card> deck;
    
    private JLabel messageJLabel;

    private JButton holdButton1;
    private JButton holdButton2;
    private JButton holdButton3;
    private JButton holdButton4;
    private JButton holdButton5;

    public Table() {
        setLayout(null);

        playerHand = new DLList<Card>();
        playerTotal = 50;
        playerTotalLabel = new JLabel("Player total: " + playerTotal);
        playerTotalLabel.setBounds(400, 10, 100, 50);
        this.add(playerTotalLabel);
        
        newGameButton = new JButton("New Game");
        newGameButton.setBounds(850, 20, 100, 50);
        newGameButton.addActionListener(this);
        this.add(newGameButton);

        drawButton = new JButton("DRAW");
        drawButton.setBounds(200, 20, 100, 50);
        drawButton.addActionListener(this);
        this.add(drawButton);
        drawButton.setVisible(false);
        
        deck = new DLList<Card>();
        deck.add(new Card(2, "2", "diamonds"));
        deck.add(new Card(3, "3", "diamonds"));
        deck.add(new Card(4, "4", "diamonds"));
        deck.add(new Card(5, "5", "diamonds"));
        deck.add(new Card(6, "6", "diamonds"));
        deck.add(new Card(7, "7", "diamonds"));
        deck.add(new Card(8, "8", "diamonds"));
        deck.add(new Card(9, "9", "diamonds"));
        deck.add(new Card(10, "10", "diamonds"));
        deck.add(new Card(11, "J", "diamonds"));
        deck.add(new Card(12, "Q", "diamonds"));
        deck.add(new Card(13, "K", "diamonds"));
        deck.add(new Card(14, "A", "diamonds"));
        deck.add(new Card(2, "2", "hearts"));
        deck.add(new Card(3, "3", "hearts"));
        deck.add(new Card(4, "4", "hearts"));
        deck.add(new Card(5, "5", "hearts"));
        deck.add(new Card(6, "6", "hearts"));
        deck.add(new Card(7, "7", "hearts"));
        deck.add(new Card(8, "8", "hearts"));
        deck.add(new Card(9, "9", "hearts"));
        deck.add(new Card(10, "10", "hearts"));
        deck.add(new Card(11, "J", "hearts"));
        deck.add(new Card(12, "Q", "hearts"));
        deck.add(new Card(13, "K", "hearts"));
        deck.add(new Card(14, "A", "hearts"));
        deck.add(new Card(2, "2", "spades"));
        deck.add(new Card(3, "3", "spades"));
        deck.add(new Card(4, "4", "spades"));
        deck.add(new Card(5, "5", "spades"));
        deck.add(new Card(6, "6", "spades"));
        deck.add(new Card(7, "7", "spades"));
        deck.add(new Card(8, "8", "spades"));
        deck.add(new Card(9, "9", "spades"));
        deck.add(new Card(10, "10", "spades"));
        deck.add(new Card(11, "J", "spades"));
        deck.add(new Card(12, "Q", "spades"));
        deck.add(new Card(13, "K", "spades"));
        deck.add(new Card(14, "A", "spades"));
        deck.add(new Card(2, "2", "clubs"));
        deck.add(new Card(3, "3", "clubs"));
        deck.add(new Card(4, "4", "clubs"));
        deck.add(new Card(5, "5", "clubs"));
        deck.add(new Card(6, "6", "clubs"));
        deck.add(new Card(7, "7", "clubs"));
        deck.add(new Card(8, "8", "clubs"));
        deck.add(new Card(9, "9", "clubs"));
        deck.add(new Card(10, "10", "clubs"));
        deck.add(new Card(11, "J", "clubs"));
        deck.add(new Card(12, "Q", "clubs"));
        deck.add(new Card(13, "K", "clubs"));
        deck.add(new Card(14, "A", "clubs"));

        messageJLabel = new JLabel("Press \"New Game\" to begin.");
        messageJLabel.setBounds(150, 400, 200, 200);
        this.add(messageJLabel);

        holdButton1 = new JButton("HOLD");
        holdButton1.setBounds(110, 300, 100, 50);
        this.add(holdButton1);
        holdButton1.addActionListener(this);
        holdButton1.setVisible(false);

        holdButton2 = new JButton("HOLD");
        holdButton2.setBounds(230, 300, 100, 50);
        this.add(holdButton2);
        holdButton2.addActionListener(this);
        holdButton2.setVisible(false);

        holdButton3 = new JButton("HOLD");
        holdButton3.setBounds(350, 300, 100, 50);
        this.add(holdButton3);
        holdButton3.addActionListener(this);
        holdButton3.setVisible(false);

        holdButton4 = new JButton("HOLD");
        holdButton4.setBounds(470, 300, 100, 50);
        this.add(holdButton4);
        holdButton4.addActionListener(this);
        holdButton4.setVisible(false);

        holdButton5 = new JButton("HOLD");
        holdButton5.setBounds(590, 300, 100, 50);
        this.add(holdButton5);
        holdButton5.addActionListener(this);
        holdButton5.setVisible(false);
    }
     
    public Dimension getPreferredSize() {
        return new Dimension(1000, 600);
    }
 
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1000, 600);

        int x = 100;
        int y = 100;
        for ( int i = 0; i < playerHand.size(); i++ ) {
            playerHand.get(i).drawMe(g, x, y);
            x += 120;
        }
    }
    
 
    private void shuffle() {
        for (int i = 0; i < deck.size(); i++) {
            int randomIndex = (int)(Math.random() * deck.size());

            Card card1 = deck.get(i);
            Card card2 = deck.get(randomIndex);

            deck.set(randomIndex, card1);
            deck.set(i, card2);
        }
    }

    private void draw() {
        if (holdButton1.isVisible()) {
            deck.add(playerHand.get(0));
            playerHand.set(0, deck.get(0));
            deck.remove(0);
        } 
        
        if (holdButton2.isVisible()) {
            deck.add(playerHand.get(1));
            playerHand.set(1, deck.get(0));
            deck.remove(0);
        }

        if (holdButton3.isVisible()) {
            deck.add(playerHand.get(2));
            playerHand.set(2, deck.get(0));
            deck.remove(0);
        }
        
        if (holdButton4.isVisible()) {
            deck.add(playerHand.get(3));
            playerHand.set(3, deck.get(0));
            deck.remove(0);
        }
        
        if (holdButton5.isVisible()) {
            deck.add(playerHand.get(4));
            playerHand.set(4, deck.get(0));
            deck.remove(0);
        }

        holdButton1.setVisible(false);
        holdButton2.setVisible(false);
        holdButton3.setVisible(false);
        holdButton4.setVisible(false);
        holdButton5.setVisible(false);

        repaint();
        checkEndGameConditions();
    }

    public void checkEndGameConditions() {
        DLList<Card> tempPlayerHand = new DLList<Card>();
        for (int i = 0; i < playerHand.size(); i++) {
            tempPlayerHand.add(playerHand.get(i));
        }
        
        for (int i = 0; i < tempPlayerHand.size() - 1; i++) {
            for (int j = i + 1; j < tempPlayerHand.size(); j++) {
                Card c1 = tempPlayerHand.get(i);
                Card c2 = tempPlayerHand.get(j);

                if (c1.getValue() > c2.getValue()) {
                    tempPlayerHand.set(i, c2);
                    tempPlayerHand.set(j, c1);
                }
            }
        }

        boolean next = true;
        boolean win = false;

        if (tempPlayerHand.get(0).getName().equals("10") && tempPlayerHand.get(1).getName().equals("J") && tempPlayerHand.get(2).getName().equals("Q") && tempPlayerHand.get(3).getName().equals("K") && tempPlayerHand.get(4).getName().equals("A") && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(1).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(2).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(3).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(4).getSuit())) {
            messageJLabel.setText("ROYAL FLUSH +250"); // working
            playerTotal += 250;
            win = true;
        } else if (tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(1).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(2).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(3).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(4).getSuit())) {
            if (tempPlayerHand.get(0).isNextCard(tempPlayerHand.get(1)) && tempPlayerHand.get(1).isNextCard(tempPlayerHand.get(2)) && tempPlayerHand.get(2).isNextCard(tempPlayerHand.get(3)) && tempPlayerHand.get(3).isNextCard(tempPlayerHand.get(4))) {
                messageJLabel.setText("STRAIGHT FLUSH +50"); // working
                playerTotal += 50;
                win = true;
            }
        } else if (tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(1).getName()) && tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(2).getName()) && tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(3).getName()) || tempPlayerHand.get(1).getName().equals(tempPlayerHand.get(1).getName()) && tempPlayerHand.get(1).getName().equals(tempPlayerHand.get(3).getName()) && tempPlayerHand.get(1).getName().equals(tempPlayerHand.get(4).getName())) {
            messageJLabel.setText("FOUR OF A KIND +25"); // working
            playerTotal += 25;
            win = true;
        } else if (tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(1).getName()) && tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(2).getName()) && tempPlayerHand.get(3).getName().equals(tempPlayerHand.get(4).getName()) || tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(1).getName()) && tempPlayerHand.get(2).getName().equals(tempPlayerHand.get(3).getName()) && tempPlayerHand.get(3).getName().equals(tempPlayerHand.get(4).getName())) {
            messageJLabel.setText("FULL HOUSE +9"); // working
            playerTotal += 9;
            win = true;
        } else if (tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(1).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(2).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(3).getSuit()) && tempPlayerHand.get(0).getSuit().equals(tempPlayerHand.get(4).getSuit())) {
            messageJLabel.setText("FLUSH +6"); // working
            playerTotal += 6;
            win = true;
        } else if (tempPlayerHand.get(0).isNextCard(tempPlayerHand.get(1)) && tempPlayerHand.get(1).isNextCard(tempPlayerHand.get(2)) && tempPlayerHand.get(2).isNextCard(tempPlayerHand.get(3)) && tempPlayerHand.get(3).isNextCard(tempPlayerHand.get(4))) {
            messageJLabel.setText("STRAIGHT +4"); // working
            playerTotal += 4;
            win = true;
        } else if (tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(1).getName()) && tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(2)) || tempPlayerHand.get(1).getName().equals(tempPlayerHand.get(2).getName()) && tempPlayerHand.get(1).getName().equals(tempPlayerHand.get(3).getName()) || tempPlayerHand.get(2).getName().equals(tempPlayerHand.get(3).getName()) && tempPlayerHand.get(2).getName().equals(tempPlayerHand.get(4).getName())) {
            messageJLabel.setText("THREE OF A KIND +3"); // working
            playerTotal += 3;
            win = true;
        } else if (true) {
            int count = 0;

            if (tempPlayerHand.get(0).getName().equals(tempPlayerHand.get(1).getName())) {
                count++;
            } else if (tempPlayerHand.get(1).getName().equals(tempPlayerHand.get(2).getName())) {
                count++;
            }

            if (tempPlayerHand.get(2).getName().equals(tempPlayerHand.get(3).getName())) {
                count++;
            } else if (tempPlayerHand.get(3).getName().equals(tempPlayerHand.get(4).getName())) {
                count++;
            }

            if (count >= 2) {
                messageJLabel.setText("TWO PAIRS +2"); // working
                playerTotal += 2;
                next = false;
                win = true;
            }
        }
        
        if (next && (tempPlayerHand.get(4).getName().equals(tempPlayerHand.get(3).getName()) && tempPlayerHand.get(4).getValue() > 10 || tempPlayerHand.get(3).getName().equals(tempPlayerHand.get(2).getName()) && tempPlayerHand.get(3).getValue() > 10 || tempPlayerHand.get(2).getName().equals(tempPlayerHand.get(1).getName()) && tempPlayerHand.get(2).getValue() > 10 || tempPlayerHand.get(1).getName().equals(tempPlayerHand.get(0).getName()) && tempPlayerHand.get(1).getValue() > 10)) {
            messageJLabel.setText("PAIR OF JACKS OR HIGHER +1"); // working
            playerTotal += 1;
            win = true;
        }

        if (win) {
            playWinSound();
        } else {
            playLoseSound();
        }

        playerTotalLabel.setText("Player total: " + playerTotal);
        newGameButton.setVisible(true);

        repaint();
    }

    private void initializeGame() {
        messageJLabel.setText("");
        newGameButton.setVisible(false);
        drawButton.setVisible(true);

        for (int i = 0; i < playerHand.size(); i++) {
            deck.add(playerHand.get(i));
        }
        playerHand = new DLList<Card>();

        shuffle();

        playerHand.add(deck.get(0));
        deck.remove(0);
        playerHand.add(deck.get(0));
        deck.remove(0);
        playerHand.add(deck.get(0));
        deck.remove(0);
        playerHand.add(deck.get(0));
        deck.remove(0);
        playerHand.add(deck.get(0));
        deck.remove(0);

        holdButton1.setVisible(true);
        holdButton2.setVisible(true);
        holdButton3.setVisible(true);
        holdButton4.setVisible(true);
        holdButton5.setVisible(true);

        drawButton.setVisible(true);

        repaint();
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

    public void playLoseSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("audio/lose.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drawButton) {
            draw();
            drawButton.setVisible(false);
        } else if (e.getSource() == newGameButton) {
            playerTotal--;
            playerTotalLabel.setText("Player total: " + playerTotal);
            initializeGame();
        } else if (e.getSource() == holdButton1) {
            holdButton1.setVisible(false);
        } else if (e.getSource() == holdButton2) {
            holdButton2.setVisible(false);
        } else if (e.getSource() == holdButton3) {
            holdButton3.setVisible(false);
        } else if (e.getSource() == holdButton4) {
            holdButton4.setVisible(false);
        } else if (e.getSource() == holdButton5) {
            holdButton5.setVisible(false);
        }

        repaint();
     }
}