import java.util.Scanner;

public class Controller {
    private Board board;
    private Scanner scanner = new Scanner(System.in);
    private int minMoves;
    private int totalMoves;

    public Controller(){
        System.out.println("What is the grid size?");
        int size = scanner.nextInt();
        
        board = new Board(size);
        AStarAlgorithm algorithm = new AStarAlgorithm();
        minMoves = algorithm.aStar(board); // TODO
    }

    public void printBoard(){
        int size = board.getSize();

        clearConsole();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(board.state[i][j] + " ");

            }
            System.out.println();
        }

    }

    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void move(){
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

        updateMoves();
    }

    public boolean getSolved(){
        return board.solved(); // TODO
    }

    public void updateMoves(){
        totalMoves++;
    }

    public void Score(){
        double decScore = totalMoves / minMoves;
        int finalScore;

        // 4 = perfect, the rest of states represent the number of stars
        if (decScore >= 2) finalScore = 0;
        else if (decScore >= 1.25) finalScore = 1;
        else if (decScore > 1) finalScore = 2;
        else finalScore = 3;

        String[] scoreStates = {"*","**","***","PERFECT"};

        clearConsole();
        System.out.println("Your score is: "+scoreStates[finalScore]);
    }

}
