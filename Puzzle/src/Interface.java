import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {

    public Interface() {
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton playButton = new JButton("Play");
        JButton optionsButton = new JButton("Options");
        JButton exitButton = new JButton("Exit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for play button (e.g., start game)
                JOptionPane.showMessageDialog(Interface.this, "Starting game...");
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for options button (e.g., open options menu)
                JOptionPane.showMessageDialog(Interface.this, "Opening options menu...");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for exit button (e.g., close application)
                int option = JOptionPane.showConfirmDialog(Interface.this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose(); // Close the main menu window
                }
            }
        });

        panel.add(playButton);
        panel.add(optionsButton);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface();
            }
        });
    }
}

