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
    private HashMap<String, Integer> cols;
    private HashMap<String, Integer> rows;
    private Stack<String> previousStates; // keeping track of previous actions for undo
    private Stack<String> undoneStates; // keeping track of undone actions for redo

    public Screen() {
        setLayout(null);
        grid = new Square[16][16];
        selector = new ColorPalette[8][1];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = new Square(255, 255, 255);
            }
        }

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

        cols = new HashMap<String, Integer>();
        cols.put("10 30", 0);
        cols.put("30 50", 1);
        cols.put("50 70", 2);
        cols.put("70 90", 3);
        cols.put("90 110", 4);
        cols.put("110 130", 5);
        cols.put("130 150", 6);
        cols.put("150 170", 7);
        cols.put("170 190", 8);
        cols.put("190 210", 9);
        cols.put("210 230", 10);
        cols.put("230 250", 11);
        cols.put("250 270", 12);
        cols.put("270 290", 13);
        cols.put("290 310", 14);
        cols.put("310 330", 15);

        rows = new HashMap<String, Integer>();
        rows.put("10 30", 0);
        rows.put("30 50", 1);
        rows.put("50 70", 2);
        rows.put("70 90", 3);
        rows.put("90 110", 4);
        rows.put("110 130", 5);
        rows.put("130 150", 6);
        rows.put("150 170", 7);
        rows.put("170 190", 8);
        rows.put("190 210", 9);
        rows.put("210 230", 10);
        rows.put("230 250", 11);
        rows.put("250 270", 12);
        rows.put("270 290", 13);
        rows.put("290 310", 14);
        rows.put("310 330", 15);

        previousStates = new Stack<String>();
        undoneStates = new Stack<String>();

        addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
     
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 10;
        int y = 10;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c].drawMe(g, x, y);
                x += 20;
            }
            x = 10;
            y += 20;
        }

        x = 350;
        y = 10;
        for (int r = 0; r < selector.length; r++) {
            selector[r][0].drawMe(g, x, y);
            y += 20;
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
        String previousState = previousStates.pop();
        String[] arr = previousState.split(" "); // parsing query
        int row = Integer.parseInt(arr[0]);
        int column = Integer.parseInt(arr[1]);
        int red = Integer.parseInt(arr[2]);
        int green = Integer.parseInt(arr[3]);
        int blue = Integer.parseInt(arr[4]);

        
        undoneStates.push(row + " " + column + " " + grid[row][column].getRed() + " " + grid[row][column].getGreen() + " " + grid[row][column].getBlue());

        grid[row][column].changeColor(red, green, blue);

        repaint();
    }

    public void redo() {
        String undoneState = undoneStates.pop();
        String[] arr = undoneState.split(" "); // parsing query
        int row = Integer.parseInt(arr[0]);
        int column = Integer.parseInt(arr[1]);
        int red = Integer.parseInt(arr[2]);
        int green = Integer.parseInt(arr[3]);
        int blue = Integer.parseInt(arr[4]);

        previousStates.push(row + " " + column + " " + grid[row][column].getRed() + " " + grid[row][column].getGreen() + " " + grid[row][column].getBlue());

        grid[row][column].changeColor(red, green, blue);
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        int row = -1;
        int column = -1;

        int x = e.getX();

        for (String each : cols.keySet()) {
            String[] arr = each.split(" ");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);

            if (x > min && x < max) {
                column = cols.get(each);
            }
        }

        int y = e.getY();

        for (String each : rows.keySet()) {
            String[] arr = each.split(" ");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);

            if (y > min && y < max) {
                row = rows.get(each);
            }
        }

        if (row != -1 && column != -1) {
            previousStates.push(row + " " + column + " " + grid[row][column].getRed() + " " + grid[row][column].getGreen() + " " + grid[row][column].getBlue());
            grid[row][column].changeColor(red, green, blue);
            repaint();
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
    }
}