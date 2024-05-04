import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EscPanel extends JPanel {
    public EscPanel(Interface inter) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 50, 20, 50);

        // Title
        JLabel titleLabel = new JLabel("PAUSE");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        gbc.gridy++; // Move to the next row
        add(titleLabel, gbc);

        // Buttons
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
        JButton play = new JButton("Resume");
        JButton menu = new JButton("Menu");
        JButton exit = new JButton("Exit");


        // Button size
        Dimension buttonSize = new Dimension(300, 25);
        play.setMinimumSize(buttonSize);
        play.setPreferredSize(buttonSize);
        play.setMaximumSize(buttonSize);

        menu.setMinimumSize(buttonSize);
        menu.setPreferredSize(buttonSize);
        menu.setMaximumSize(buttonSize);

        exit.setMinimumSize(buttonSize);
        exit.setPreferredSize(buttonSize);
        exit.setMaximumSize(buttonSize);

        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(play);
        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(menu);
        panelButton.add(Box.createVerticalStrut(10));
        panelButton.add(exit);

        gbc.gridy++;
        add(panelButton, gbc);

        // Button listeners
        play.addActionListener(e -> inter.resume());

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

        play.addKeyListener(keyAdapter);
        menu.addKeyListener(keyAdapter);
        exit.addKeyListener(keyAdapter);
    }
}
