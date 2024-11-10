package ungawen.matching.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

public class gameUngawen extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[4][4];  // 4x4 grid
    private ImageIcon[][] images = new ImageIcon[4][4];
    private JButton firstButton, secondButton;
    private int firstRow, firstCol, secondRow, secondCol;
    private boolean isCheckingPair = false;
    private ImageIcon lose;
    private JLabel gameLabel; // Label at the top
    private int catLives = 9;

    public gameUngawen() {
        // Setup the JFrame
        setTitle("Matching Ungawen");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the label
        gameLabel = new JLabel("Life=" + catLives, SwingConstants.CENTER);
        gameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Create a panel to hold the label and the button grid
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Add the label to the top of the panel
        mainPanel.add(gameLabel, BorderLayout.NORTH);

        // Create a panel for the 4x4 grid
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 4));

        // Initialize the game
        initializeGame(gridPanel);

        // Add the grid panel to the center of the main panel
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);
        pack();  // Fit the layout to components
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void initializeGame(JPanel gridPanel) {
        ArrayList<ImageIcon> imagePairs = new ArrayList<>();

        // Load images using getClass().getResource() and scale them
        ImageIcon image1 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen1.jpeg"));
        ImageIcon image2 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen2.jpeg"));
        ImageIcon image3 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen3.jpeg"));
        ImageIcon image4 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen4.jpeg"));
        ImageIcon image5 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen5.jpeg"));
        ImageIcon image6 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen6.jpeg"));
        ImageIcon image7 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen7.jpeg"));
        ImageIcon image8 = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/ungawen8.jpeg"));

        // Scale images
        Image img1 = image1.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new1 = new ImageIcon(img1);

        Image img2 = image2.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new2 = new ImageIcon(img2);

        Image img3 = image3.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new3 = new ImageIcon(img3);

        Image img4 = image4.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new4 = new ImageIcon(img4);

        Image img5 = image5.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new5 = new ImageIcon(img5);

        Image img6 = image6.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new6 = new ImageIcon(img6);

        Image img7 = image7.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new7 = new ImageIcon(img7);

        Image img8 = image8.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon new8 = new ImageIcon(img8);

        // Add each image twice to create pairs
        imagePairs.add(new1); imagePairs.add(new1);
        imagePairs.add(new2); imagePairs.add(new2);
        imagePairs.add(new3); imagePairs.add(new3);
        imagePairs.add(new4); imagePairs.add(new4);
        imagePairs.add(new5); imagePairs.add(new5);
        imagePairs.add(new6); imagePairs.add(new6);
        imagePairs.add(new7); imagePairs.add(new7);
        imagePairs.add(new8); imagePairs.add(new8);

        // Shuffle the pairs
        Collections.shuffle(imagePairs);

        // Create the buttons and assign shuffled values
        int counter = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setPreferredSize(new Dimension(180, 180));  // Set button size
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[row][col].setIcon(imagePairs.get(counter));  // Show images initially
                buttons[row][col].addActionListener(this);
                gridPanel.add(buttons[row][col]);  // Add to the grid panel
                images[row][col] = imagePairs.get(counter);
                counter++;
            }
        }

        // Hide images after 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        buttons[row][col].setIcon(null);  // Hide images after preview
                    }
                }
            }
        });
        timer.setRepeats(false);  // Only execute once
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isCheckingPair) return;  // Block clicks during pair checking
        
        JButton clickedButton = (JButton) e.getSource();

        // Find the position of the clicked button
        int clickedRow = -1, clickedCol = -1;
        outerloop:
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (buttons[row][col] == clickedButton) {
                    clickedRow = row;
                    clickedCol = col;
                    break outerloop;
                }
            }
        }

        if (clickedButton.getIcon() == null && firstButton == null) {
            // First button clicked
            firstButton = clickedButton;
            firstRow = clickedRow;
            firstCol = clickedCol;
            firstButton.setIcon(images[firstRow][firstCol]);
        } else if (clickedButton.getIcon() == null && firstButton != null) {
            // Second button clicked
            secondButton = clickedButton;
            secondRow = clickedRow;
            secondCol = clickedCol;
            secondButton.setIcon(images[secondRow][secondCol]);
            
            isCheckingPair = true;
            // Delay for checking
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    checkPair();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    
    int mistakes = 0;
    int corrects = 0;
    int attempts = 0;

    private void checkPair() {
        if (images[firstRow][firstCol].equals(images[secondRow][secondCol])) {
            // If values match, keep them revealed
            firstButton.setEnabled(false);
            secondButton.setEnabled(false);
            corrects++;
            attempts++;
        } else {
            // If values don't match, hide them again
            firstButton.setIcon(null);
            secondButton.setIcon(null);
            mistakes++;
            attempts++;
            catLives--;
            gameLabel.setText("Life=" + catLives);
        }

        firstButton = null;
        secondButton = null;
        isCheckingPair = false;
        
        if (attempts == 9) {
            new result(corrects, mistakes).setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new gameUngawen();
            }
        });
    }
}
