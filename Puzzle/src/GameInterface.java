import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GameInterface extends JPanel {
    private JPanel gridPanel;
    private int gridSize;

    public GameInterface(Interface inter, int GridSize, int[][] gameState) {
        gridSize = GridSize;

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

        // Add a KeyListener to detect key presses
        setFocusable(true); // Enable focus for the panel
        requestFocusInWindow(); // Request focus to receive key events
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Check if the pressed key is Enter
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    inter.pause();
                }
            }
        });

        updateButton(inter, gameState);
    }

    public void updateButton(Interface inter, int[][] gameState) {
        // Create and add clickable buttons (cells) to the gridPanel
        gridPanel.removeAll();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                JButton button = createClickableButton(inter, gameState[i][j]);
                gridPanel.add(button);
            }
        }

        add(gridPanel);
        setVisible(true);
    }

    // Create a clickable JButton representing a cell in the grid
    private JButton createClickableButton(Interface inter, int num) {
        //if (num == -1) num = gridSize * gridSize - 1;
        String filepath = "images/img" + num + ".jpg";
        ImageIcon icon = new ImageIcon(filepath);
        JButton button = new JButton(icon);

        button.setBorder(BorderFactory.createEmptyBorder());

        button.setMargin(new Insets(0,0,0,0));
        button.setBackground(Color.WHITE); // Set background color
        button.setActionCommand(Integer.toString(num));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroInt = Integer.parseInt(button.getActionCommand());
                inter.movePiece(numeroInt);
                System.out.println(numeroInt);
                updateButton(inter, inter.getState());
                inter.printBoardMove();
            }
        });
        return button;
    }
}
