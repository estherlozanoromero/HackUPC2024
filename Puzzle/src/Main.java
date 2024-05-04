import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {            
        Controller controller = new Controller();
        Interface inter = new Interface();

        inter.createMenu();
        inter.iniMenuWindow();

        Runnable getMinMoves = () -> {
            controller.executeAlgorithm();
        };

        Thread minMovesThread = new Thread(getMinMoves);
        minMovesThread.start();

        while (!controller.getSolved()) {

            while (!controller.getSolved()) {

                controller.printBoard();

                controller.move();
            }

        controller.Score();
        
        }
    }
}
