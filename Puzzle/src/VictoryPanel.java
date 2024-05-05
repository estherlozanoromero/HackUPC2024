import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VictoryPanel extends JPanel {

    JButton level;
    int numLevel = 0;

    public VictoryPanel (Interface inter) {
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
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
        JButton play = new JButton("Play");
        JButton exit = new JButton("Menu");

        this.level = new JButton("Easy");
        JButton setImg = new JButton("Set Image");


        // Button size
        Dimension buttonSize = new Dimension(300, 25);
        play.setMinimumSize(buttonSize);
        play.setPreferredSize(buttonSize);
        play.setMaximumSize(buttonSize);

        Dimension buttonlevelSize = new Dimension(150, 25);

        this.level.setMinimumSize(buttonlevelSize);
        this.level.setPreferredSize(buttonlevelSize);
        this.level.setMaximumSize(buttonlevelSize);

        setImg.setMinimumSize(buttonlevelSize);
        setImg.setPreferredSize(buttonlevelSize);
        setImg.setMaximumSize(buttonlevelSize);

        exit.setMinimumSize(buttonSize);
        exit.setPreferredSize(buttonSize);
        exit.setMaximumSize(buttonSize);

        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(play);
        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(this.level);
        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(setImg);
        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(exit);

        gbc.gridy++;
        add(panelButton, gbc);

        // Button listeners
        play.addActionListener(e -> inter.play());

        this.level.addActionListener(e -> {
            changeLevel();
            inter.setLevel(numLevel);
        });

        setImg.addActionListener(e -> inter.setImage());

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

        play.addKeyListener(keyAdapter);
        this.level.addKeyListener(keyAdapter);
        setImg.addKeyListener(keyAdapter);
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
