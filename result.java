package ungawen.matching.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class result extends JFrame implements ActionListener {
    ImageIcon lose;
    JButton restartGame = new JButton("Restart Game");
    Container container;

    public result(int correctAttempts, int wrongAttempts) {
        setTitle("Matching Ungawen");
        setSize(1280, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Add padding around the content
        ((JComponent) container).setBorder(new EmptyBorder(20, 20, 20, 20));

        // Load and resize the image
        //lose = new ImageIcon("src/ungawen/matching/game/img/youLose.jpeg");
        
                 lose = new ImageIcon(getClass().getResource("/ungawen/matching/game/img/youLose.jpeg"));

        Image convLose = lose.getImage().getScaledInstance(380, 380, Image.SCALE_SMOOTH);
        ImageIcon youLose = new ImageIcon(convLose);

        JLabel label;
        if (wrongAttempts > 4) {
            label = new JLabel(
                    "You are a failure!!!!!! You have " + wrongAttempts + " wrong attempts",
                    youLose,
                    SwingConstants.CENTER
            );
        } else {
            label = new JLabel(
                    "You succeed! You have " + correctAttempts + " correct attempts",
                    youLose,
                    SwingConstants.CENTER
            );
        }

        // Style the label text
        label.setFont(new Font("Serif", Font.BOLD, 30));
        label.setAlignmentX(CENTER_ALIGNMENT);
        container.add(label);

        // Add some space between the label and the button
        container.add(Box.createVerticalStrut(20));

        // Style and add the restart button
        restartGame.setFont(new Font("Arial", Font.PLAIN, 20));
        restartGame.setAlignmentX(CENTER_ALIGNMENT);
        restartGame.addActionListener(this);
        container.add(restartGame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new gameUngawen().setVisible(true);
        dispose();
    }
}