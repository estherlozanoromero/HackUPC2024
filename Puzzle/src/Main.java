import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {            
       // Controller controller = new Controller();
        Interface inter = new Interface();

        inter.createMenu();
        inter.iniMenuWindow();

        /*while (!controller.getSolved()) {

<<<<<<< HEAD
        SwingUtilities.invokeLater(() -> {
            new GameInterface(3, controller.getState()); // Create a 3x3 grid menu
        });

        while (!controller.getSolved()) {
=======
>>>>>>> esther
            controller.printBoard();

            controller.move();
        }

        controller.Score(); */
        
    }
}
