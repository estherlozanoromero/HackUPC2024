import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {

    JButton level;
    int numLevel = 0;

    public MenuPanel(Interface inter) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 50, 5, 50);

        // Title
        JLabel titleLabel = new JLabel("Welcome to Puzzle Game");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        gbc.gridy++; // Move to the next row
        add(titleLabel, gbc);

        // Buttons
        JPanel panelButton1 = new JPanel();
        panelButton1.setLayout(new BoxLayout(panelButton1, BoxLayout.Y_AXIS));

        JPanel panelConfig = new JPanel();
        panelConfig.setLayout(new BoxLayout(panelConfig, BoxLayout.X_AXIS));

        JPanel panelButton2 = new JPanel();
        panelButton2.setLayout(new BoxLayout(panelButton2, BoxLayout.Y_AXIS));

        JButton play = new JButton("Play");
        JButton exit = new JButton("Exit");

        this.level = new JButton("Easy");
        JButton setImg = new JButton("Set Image");


        // Button size
        Dimension buttonSize = new Dimension(185, 25);
        play.setMinimumSize(buttonSize);
        play.setPreferredSize(buttonSize);
        play.setMaximumSize(buttonSize);

        Dimension buttonlevelSize = new Dimension(90, 25);

        this.level.setMinimumSize(buttonlevelSize);
        this.level.setPreferredSize(buttonlevelSize);
        this.level.setMaximumSize(buttonlevelSize);

        setImg.setMinimumSize(buttonlevelSize);
        setImg.setPreferredSize(buttonlevelSize);
        setImg.setMaximumSize(buttonlevelSize);

        exit.setMinimumSize(buttonSize);
        exit.setPreferredSize(buttonSize);
        exit.setMaximumSize(buttonSize);


        panelButton1.add(play);

        panelConfig.add(this.level);
        panelConfig.add(Box.createHorizontalStrut(5));
        panelConfig.add(setImg);

        panelButton2.add(exit);

        gbc.gridy++;
        add(panelButton1, gbc);
        gbc.gridy++;
        add(panelConfig, gbc);
        gbc.gridy++;
        add(panelButton2, gbc);

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
