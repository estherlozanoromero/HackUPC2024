import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VictoryPanel extends JPanel {

    JButton level;
    int numLevel = 0;

    public VictoryPanel (Interface inter, int score) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 50, 20, 50);

        // Title
        JLabel titleLabel = new JLabel("VICTORY");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        gbc.gridy++; // Move to the next row
        add(titleLabel, gbc);

        // Buttons
        JPanel panelStars = new JPanel();
        panelStars.setLayout(new BoxLayout(panelStars, BoxLayout.X_AXIS));
        String path_star = "icon/star.png";
        ImageIcon icon = new ImageIcon(path_star);

        // Scale the image to half its original size
        Image scaledImage = icon.getImage().getScaledInstance(icon.getIconWidth() / 2, icon.getIconHeight() / 2, Image.SCALE_DEFAULT);
        icon = new ImageIcon(scaledImage);


        JLabel star1 = new JLabel(icon);
        panelStars.add(Box.createHorizontalStrut(10));
        panelStars.add(star1);

        if (score > 0) {
            JLabel star2 = new JLabel(icon);
            panelStars.add(Box.createHorizontalStrut(10));
            panelStars.add(star2);
        }

        if (score > 1) {
            JLabel star3 = new JLabel(icon);
            panelStars.add(Box.createHorizontalStrut(10));
            panelStars.add(star3);
        }
        add(panelStars);

        // Buttons
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
        JButton menu = new JButton("Menu");
        JButton exit = new JButton("Exit");


        // Button size
        Dimension buttonSize = new Dimension(300, 25);

        menu.setMinimumSize(buttonSize);
        menu.setPreferredSize(buttonSize);
        menu.setMaximumSize(buttonSize);

        exit.setMinimumSize(buttonSize);
        exit.setPreferredSize(buttonSize);
        exit.setMaximumSize(buttonSize);

        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(menu);
        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(exit);

        gbc.gridy++;
        add(panelButton, gbc);

        // Button listeners
        menu.addActionListener(e -> inter.createMenu());

        exit.addActionListener(e -> inter.closeApp());

        // Add KeyListener to capture "Enter" key press
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JButton sourceButton = (JButton) e.getSource();
                    sourceButton.doClick();
                }
            }
        };

        menu.addKeyListener(keyAdapter);
        exit.addKeyListener(keyAdapter);
    }

    private void changeLevel() {
        if(this.numLevel == 2) {
            this.numLevel = 0;
            this.level.setText("Easy");
        } else if(this.numLevel == 0) {
            this.numLevel = 1;
            this.level.setText("Normal");
        } else if(numLevel == 1) {
            this.numLevel = 2;
            this.level.setText("Hard");
        }
    }

}
