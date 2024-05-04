import javax.swing.*;

public class Interface {

    private Controller ctrl;
    private JFrame window;
    private MenuPanel menuPanel;
    private GameInterface gameInterface;

    private int level;


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
        this.ctrl = new Controller(this.level);
        this.gameInterface = new GameInterface(this, this.ctrl.getSize(), this.ctrl.getState());


        window.getContentPane().removeAll();
        window.add(gameInterface);
        window.setJMenuBar(null);
        window.revalidate();
        window.repaint();
    }

    public void setLevel(int numLevel) {
        this.level = numLevel;
    }

    public void closeApp() {

    }

    public int[][] getState() {
        return this.ctrl.getState();
    }

    public void movePiece(int numeroInt) {
        this.ctrl.move(numeroInt);
    }
}

