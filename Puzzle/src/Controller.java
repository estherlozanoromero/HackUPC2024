import java.util.Scanner;

public class Controller {
    private Board board;

    public Controller(int size){
        board = new Board(size);
    }

    public void printBoard(){
        int size = board.getSize();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(board.state[i][j] + " ");

            }
            System.out.println();
        }

    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void move(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What tile do you want to move?");
        System.out.println("(Top left corner is 0,0)");
        System.out.println();
        System.out.println("Row position");
        int moveRow = scanner.nextInt();
        System.out.println("Column position");
        int moveCol = scanner.nextInt();

        if(board.checkMove(moveRow, moveCol)) {
            board.move(moveRow, moveCol);
        } else {
            move();
        }
    }

}
