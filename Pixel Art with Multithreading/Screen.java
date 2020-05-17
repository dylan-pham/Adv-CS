import javax.swing.JPanel;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
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
 
public class Screen extends JPanel implements MouseListener, ActionListener {
    private Square[][] grid;
    private ColorPalette[][] selector;
    private JButton clearButton;
    private JButton saveButton;
    private JButton undoButton;
    private JButton redoButton;
    private int red;
    private int green;
    private int blue;
    private BufferedImage image;
    private MyHashMap<Pair<Integer, Integer>, Integer> cols;
    private MyHashMap<Pair<Integer, Integer>, Integer> rows;
    private Stack<Pair<Pair<Integer, Integer>, RGB>> previousStates; // keeping track of previous actions for undo
    private Stack<Pair<Pair<Integer, Integer>, RGB>> undoneStates; // keeping track of undone actions for redo
	private ObjectOutputStream out;
	private GameData gameData;

    public Screen() {
        setLayout(null);

        selector = new ColorPalette[8][1];
        selector[0][0] = new ColorPalette(255, 0, 0);
        selector[1][0] = new ColorPalette(0, 255, 0);
        selector[2][0] = new ColorPalette(0, 0, 255);
        selector[3][0] = new ColorPalette(255, 255, 0);
        selector[4][0] = new ColorPalette(128, 0, 128);
        selector[5][0] = new ColorPalette(255, 165, 0);
        selector[6][0] = new ColorPalette(255, 255, 255);
        selector[7][0] = new ColorPalette(255,192,203);

        clearButton = new JButton("Clear");
        clearButton.setBounds(700, 10, 90, 30);
        clearButton.addActionListener(this);
        this.add(clearButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(600, 10, 90, 30);
        saveButton.addActionListener(this);
        this.add(saveButton);

        undoButton = new JButton("Undo");
        undoButton.setBounds(400, 10, 90, 30);
        undoButton.addActionListener(this);
        this.add(undoButton);

        redoButton = new JButton("Redo");
        redoButton.setBounds(500, 10, 90, 30);
        redoButton.addActionListener(this);
        this.add(redoButton);

        red = 0;
        green = 255;
        blue = 0;

        cols = new MyHashMap<Pair<Integer, Integer>, Integer>();
        cols.put(new Pair(10, 30), 0);
        cols.put(new Pair(30, 50), 1);
        cols.put(new Pair(50, 70), 2);
        cols.put(new Pair(70, 90), 3);
        cols.put(new Pair(90, 110), 4);
        cols.put(new Pair(110, 130), 5);
        cols.put(new Pair(130, 150), 6);
        cols.put(new Pair(150, 170), 7);
        cols.put(new Pair(170, 190), 8);
        cols.put(new Pair(190, 210), 9);
        cols.put(new Pair(210, 230), 10);
        cols.put(new Pair(230, 250), 11);
        cols.put(new Pair(250, 270), 12);
        cols.put(new Pair(270, 290), 13);
        cols.put(new Pair(290, 310), 14);
        cols.put(new Pair(310, 330), 15);

        rows = new MyHashMap<Pair<Integer, Integer>, Integer>();
        rows.put(new Pair(10, 30), 0);
        rows.put(new Pair(30, 50), 1);
        rows.put(new Pair(50, 70), 2);
        rows.put(new Pair(70, 90), 3);
        rows.put(new Pair(90, 110), 4);
        rows.put(new Pair(110, 130), 5);
        rows.put(new Pair(130, 150), 6);
        rows.put(new Pair(150, 170), 7);
        rows.put(new Pair(170, 190), 8);
        rows.put(new Pair(190, 210), 9);
        rows.put(new Pair(210, 230), 10);
        rows.put(new Pair(230, 250), 11);
        rows.put(new Pair(250, 270), 12);
        rows.put(new Pair(270, 290), 13);
        rows.put(new Pair(290, 310), 14);
        rows.put(new Pair(310, 330), 15);

        addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
     
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

		if (gameData != null) {
			int x = 10;
			int y = 10;

			// drawing grid
			for (int r = 0; r < grid.length; r++) {
				for (int c = 0; c < grid[r].length; c++) {
					grid[r][c].drawMe(g, x, y);
					x += 20;
				}
				x = 10;
				y += 20;
			}
			
			// drawing color palette
			x = 350;
			y = 10;
			for (int r = 0; r < selector.length; r++) {
				selector[r][0].drawMe(g, x, y);
				y += 20;
			}
		}
    }

    public void saveImage() {
        if (image == null) {
            image = (BufferedImage)(createImage(320, 320));
        }

            //Create a graphics object that draws on the image
        Graphics gImage = image.createGraphics();

        int x = 0;
        int y = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c].drawMe(gImage, x, y);
                x += 20;
            }
            x = 0;
            y += 20;
        }

            // writing to file
        if (image != null) {
            try {
                File outputfile = new File("image.png");
                ImageIO.write(image, "png", outputfile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void undo() {
        Pair<Pair<Integer, Integer>, RGB> previousState = previousStates.pop();
		int row = previousState.getValue1().getValue1();
		int column = previousState.getValue1().getValue2();
        int red = previousState.getValue2().getRed();
        int green = previousState.getValue2().getGreen();
        int blue = previousState.getValue2().getBlue();

       undoneStates.push(new Pair<Pair<Integer, Integer>, RGB>(new Pair<Integer, Integer>(row, column), new RGB(grid[row][column].getRed(), grid[row][column].getGreen(), grid[row][column].getBlue()))); 
        grid[row][column].changeColor(red, green, blue);

        repaint();
    }

    public void redo() {
	    Pair<Pair<Integer, Integer>, RGB> undoneState = undoneStates.pop();
		int row = undoneState.getValue1().getValue1();
		int column = undoneState.getValue1().getValue2();
        int red = undoneState.getValue2().getRed();
        int green = undoneState.getValue2().getGreen();
        int blue = undoneState.getValue2().getBlue();

       previousStates.push(new Pair<Pair<Integer, Integer>, RGB>(new Pair<Integer, Integer>(row, column), new RGB(grid[row][column].getRed(), grid[row][column].getGreen(), grid[row][column].getBlue()))); 
        grid[row][column].changeColor(red, green, blue);

        repaint();
    }

    public void mousePressed(MouseEvent e) {
		if (gameData != null) {
			int row = -1;
			int column = -1;

			int x = e.getX();

			for (int i = 0; i < cols.getKeys().size(); i++) {
				Pair<Integer, Integer> c = cols.getKeys().get(i);
				int min = c.getValue1();
				int max = c.getValue2();

				if (x > min && x < max) {
					column = cols.get(cols.getKeys().get(i)).get(0);
				}
			}

			int y = e.getY();

			for (int j = 0; j < rows.getKeys().size(); j++) {
				Pair<Integer, Integer> c = rows.getKeys().get(j);
				int min = c.getValue1();
				int max = c.getValue2();

				if (y > min && y < max) {
					row = rows.get(rows.getKeys().get(j)).get(0);
				}
			}

			if (row != -1 && column != -1) {
				previousStates.push(new Pair<Pair<Integer, Integer>, RGB>(new Pair(row, column), new RGB(grid[row][column].getRed(), grid[row][column].getGreen(), grid[row][column].getBlue())));
				grid[row][column].changeColor(red, green, blue);
				
				repaint();
				try{
					out.reset();
					out.writeObject(gameData);
				} catch (IOException ex) {
					System.out.println(ex);
				}

			}

			if (e.getX() > 350 && e.getX() < 370 && e.getY() > 10 && e.getY() < 30) {
				red = 255;
				green = 0;
				blue = 0;
			} else if (e.getX() > 350 && e.getX() < 370 && e.getY() > 30 && e.getY() < 50) {
				red = 0;
				green = 255;
				blue = 0;
			} else if (e.getX() > 350 && e.getX() < 370 && e.getY() > 50 && e.getY() < 70) {
				red = 0;
				green = 0;
				blue = 255;
			} else if (e.getX() > 350 && e.getX() < 370 && e.getY() > 70 && e.getY() < 90) {
				red = 255;
				green = 255;
				blue = 0;
			} else if (e.getX() > 350 && e.getX() < 370 && e.getY() > 90 && e.getY() < 110) {
				red = 128;
				green = 0;
				blue = 128;
			} else if (e.getX() > 350 && e.getX() < 370 && e.getY() > 110 && e.getY() < 130) {
				red = 255;
				green = 165;
				blue = 0;
			} else if (e.getX() > 350 && e.getX() < 370 && e.getY() > 130 && e.getY() < 150) {
				red = 0;
				green = 128;
				blue = 128;
			} else if (e.getX() > 350 && e.getX() < 370 && e.getY() > 150 && e.getY() < 170) {
				red = 255;
				green = 192;
				blue = 203;
			}

			repaint();
		}	
    }

    private void clearScreen() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c].changeColor(255, 255, 255);
            }
        }

        repaint();
    }

    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            clearScreen();
        } 
        
        if (e.getSource() == saveButton) {
            saveImage();
        }

        if (e.getSource() == undoButton) {
            if (!previousStates.empty())
                undo();
        }

        if (e.getSource() == redoButton) {
            if (!undoneStates.empty())
                redo();
        }

		try {
			out.reset();
			out.writeObject(gameData);
		} catch (IOException ex) {
            System.out.println(ex);
        }

		repaint();
    }

	@SuppressWarnings("unchecked")
	public void poll() throws IOException{
		String hostName = "localhost";
        int portNumber = 1024;
		Socket serverSocket = new Socket(hostName, portNumber);

		out = new ObjectOutputStream(serverSocket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

		//listens for a stream
		try {

			//send String to server to test connection
			out.writeObject("New client connected");

			while (true) {
				//wait for gameData object
				gameData = (GameData) in.readObject();
				previousStates = gameData.getPreviousStates();
				undoneStates = gameData.getUndoneStates();
				grid = gameData.getGrid();

				repaint();
			}
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (ClassNotFoundException e){
			 System.err.println(e);
			 System.exit(1);
		}

	}
}





