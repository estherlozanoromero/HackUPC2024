import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface extends JFrame { // TODO Cambiar a JPanel
    private JPanel gridPanel;
    private int gridSize;

    public GameInterface(int GridSize, int[][] gameState) {// TODO Añadir interface
        setTitle("Puzzle Game"); // TODO eliminar
        setDefaultCloseOperation(EXIT_ON_CLOSE); // TODO eliminar
        setSize(300,300); // TODO eliminar

        gridSize = GridSize;

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

        updateButton(gameState);
    }

    public void updateButton(int[][] gameState){
        // Create and add clickable buttons (cells) to the gridPanel
        gridPanel.removeAll();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++){
                JButton button = createClickableButton(gameState[i][j]);
                gridPanel.add(button);
            }
        }

        add(gridPanel);
        setVisible(true);
    }

    // Create a clickable JButton representing a cell in the grid
    private JButton createClickableButton(int num) {
        //if (num == -1) num = gridSize*gridSize - 1;
        String filepath = "images/img" + num + ".jpg";
        ImageIcon icon = new ImageIcon(filepath);
        JButton button = new JButton(icon);

        button.setBackground(Color.WHITE); // Set background color
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] nullstate = {{0,0,0},{0,0,0},{0,0,0}}; // TODO Eliminar
                updateButton(nullstate); // TODO Añadir inter.getBoard()
            }
        });
        return button;
    }
}
