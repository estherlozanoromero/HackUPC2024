import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("What is the grid size?");
        int size = scanner.nextInt();
        
        Controller controller = new Controller(size);
        boolean exit = false;

        while (!exit) {
            controller.clearConsole();
            controller.printBoard();

            controller.move();
        }
        
    }
}
