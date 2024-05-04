import javax.swing.*;

public class Interface {

    private JFrame window;
    private MenuPanel menuPanel;


    public Interface() {
        this.window = new JFrame();
        window.setTitle("Puzzle Game");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menuPanel = new MenuPanel(this);
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

    }

    public void setLevel(int numLevel) {

    }

    public void closeApp() {

    }
}

