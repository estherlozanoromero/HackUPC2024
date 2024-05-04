import javax.swing.*;

public class Interface {

    private JFrame window;
    private MenuPanel menuPanel;
    private GameInterface gameInterface;


    public Interface() {
        this.window = new JFrame();
        window.setTitle("Puzzle Game");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menuPanel = new MenuPanel(this);
        //this.gameInterface = new GameInterface(int GridSize, int[][] gameState);
    }

    public void createMenu() {
        window.getContentPane().removeAll();
        window.add(menuPanel);
        window.setJMenuBar(null);
        window.revalidate();
        window.repaint();
    }

    public void iniMenuWindow() {
        window.add(menuPanel);
        window.setJMenuBar(null);
        window.setVisible(true);
    }

    public void play() {
        window.getContentPane().removeAll();
        window.add(gameInterface);
        window.setJMenuBar(null);
        window.revalidate();
        window.repaint();
    }

    public void setLevel(int numLevel) {

    }

    public void setImage() {
        ImageManager.getImageAndCrop(window.getHeight(), 3);
    }

    public void closeApp() {

    }
}

