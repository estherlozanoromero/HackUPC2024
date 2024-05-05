public class Controller {
    private Board board;
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



    public void move(int id){

        int[] pos = board.searchPos(id);
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

    public int getTotalMoves() {
        return totalMoves;
    }

    public int Score(){
        double decScore = totalMoves / minMoves;
        int finalScore;

        if (decScore >= 2) finalScore = 0;
        else if (decScore >= 1.25) finalScore = 1;
        else if (decScore > 1) finalScore = 2;
        else finalScore = 3;

        return finalScore;
    }
}
