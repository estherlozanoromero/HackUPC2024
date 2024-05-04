import java.util.Scanner;

public class Controller {
    private Board board;
    private Scanner scanner = new Scanner(System.in);
    private int minMoves;
    private int totalMoves;
    
    public Controller(int level) {
        int size = 3;
        if(level == 0) {
            size = 3;
        } else if(level == 1) {
            size = 4;
        } else if (level == 2) {
            size = 5;
        }
        this.board = new Board(size);
    }

    public void executeAlgorithm() {
        AStarAlgorithm algorithm = new AStarAlgorithm();
        this.minMoves = algorithm.aStar(board);
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

    public void move(int id){

        int[] pos = board.searchPos(id);
        System.out.println(pos[0]);
        System.out.println(pos[1]);
        if(board.checkMove(pos[0], pos[1])) {
            board.move(pos[0], pos[1]);
        }
        updateMoves();
    }

    public boolean getSolved(){
        return board.solved();
    }

    public int getSize(){
        return board.getSize();
    }

    public int[][] getState(){
        return board.state;
    }
    
    public void updateMoves(){
        totalMoves++;
    }

    public int getMinMoves() {
        return minMoves;
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
