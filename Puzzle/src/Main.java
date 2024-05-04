public class Main {
    public static void main(String[] args) {            
        Controller controller = new Controller();

        while (!controller.getSolved()) {
            controller.printBoard();

            controller.move();
        }

        controller.Score();
        
    }
}
