import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface extends JPanel {
    private JPanel gridPanel;
    private int gridSize;

    public GameInterface(Interface inter, int GridSize, int[][] gameState) {

        gridSize = GridSize;

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

        updateButton(inter, gameState);
    }

    public void updateButton(Interface inter, int[][] gameState){
        // Create and add clickable buttons (cells) to the gridPanel
        gridPanel.removeAll();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++){
                JButton button = createClickableButton(inter, gameState[i][j]);
                gridPanel.add(button);
            }
        }

        add(gridPanel);
        setVisible(true);
    }

    // Create a clickable JButton representing a cell in the grid
    private JButton createClickableButton(Interface inter, int num) {
        //if (num == -1) num = gridSize*gridSize - 1;
        String filepath = "images/img" + num + ".jpg";
        ImageIcon icon = new ImageIcon(filepath);
        JButton button = new JButton(icon);

        button.setBackground(Color.WHITE); // Set background color
        button.setActionCommand(Integer.toString(num));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroInt = Integer.parseInt(button.getActionCommand());
                inter.movePiece(numeroInt);
                updateButton(inter, inter.getState());
            }
        });
        return button;
    }
}
