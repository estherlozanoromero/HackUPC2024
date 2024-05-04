import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {            
        Controller controller = new Controller();

        SwingUtilities.invokeLater(() -> {
            new GameInterface(3, controller.getState()); // Create a 3x3 grid menu
        });

        while (!controller.getSolved()) {
            controller.printBoard();

            controller.move();
        }

        controller.Score();
        
    }
}
