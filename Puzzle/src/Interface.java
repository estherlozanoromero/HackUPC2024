import javax.swing.*;



public class Interface {

    private Controller ctrl;
    private JFrame window;
    private MenuPanel menuPanel;
    private GameInterface gameInterface;
    private EscPanel PausePanel;

    private ImageManager ImManager;

    //private VictoryPanel victory;


    private int level;


    public Interface() {
        this.window = new JFrame();
        window.setTitle("Puzzle Game");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImManager = new ImageManager(600);

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

    public void pause() {
        this.PausePanel = new EscPanel(this);


        window.getContentPane().removeAll();
        window.add(PausePanel);
        window.setJMenuBar(null);
        window.revalidate();
        window.repaint();
    }

    public void play() {
        ImManager.getImageAndCrop(600, this.level+3);
        this.ctrl = new Controller(this.level);
        this.gameInterface = new GameInterface(this, this.ctrl.getSize(), this.ctrl.getState());

        Runnable getMinMoves = () -> {
            ctrl.executeAlgorithm();
        };

        Thread minMovesThread = new Thread(getMinMoves);
        minMovesThread.start();

        window.getContentPane().removeAll();

        window.add(gameInterface);
        window.setJMenuBar(null);
        window.revalidate();
        window.repaint();
    }

    public void resume() {
        window.getContentPane().removeAll();
        window.add(gameInterface);
        window.setJMenuBar(null);
        window.revalidate();
        window.repaint();
    }

    public void printBoardMove() {
        window.getContentPane().removeAll();
        window.add(gameInterface);
        window.setJMenuBar(null);
        window.revalidate();
        window.repaint();
    }

    public void setLevel(int numLevel) {
        this.level = numLevel;
    }

    public void setImage() {
        ImManager.selectImage(600);
        ImManager.getImageAndCrop(600, this.level+3);
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

