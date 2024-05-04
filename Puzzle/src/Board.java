import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Board {
    public int[][] state;
    private int gapx, gapy;
    private int size;

    public Board (int num) {
        size = num;
        state = new int[size][size];

        initBoard();

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (state[i][j] == -1) {
                    gapx = i;
                    gapy = j;
                }
            }
        }
    }

    public Board(Board b) {
        gapx = b.gapx;
        gapy = b.gapy;
        size = b.size;
        state = new int[b.state.length][];
        for (int i = 0; i < b.state.length; i++) {
            state[i] = b.state[i].clone();
        }
    }

    public void move(int posx, int posy) {
        state[gapx][gapy] = state[posx][posy];
        gapx = posx;
        gapy = posy;
        state[gapx][gapy] = -1;
    }

    private int[] idxToPos(int idx) {
        int[] pos = new int[2];
        pos[0] = idx/size;
        pos[1] = idx%size;
        return pos;
    }

    private int posToIdx(int posx, int posy) {
        return posx*size+posy;
    }

    public void randMove() {
        int idx = posToIdx(gapx, gapy);
        List<Integer> poss = new ArrayList<>();

        if (gapy > 0) {
            poss.add(idx - 1);
        }
        if (gapy < size - 1) {
            poss.add(idx + 1);
        }
        if (gapx > 0) {
            poss.add(idx - size);
        }
        if (gapx < size - 1) {
            poss.add(idx + size);
        }

        Random rand = new Random();
        int swi = poss.get(rand.nextInt(poss.size()));
        move(idxToPos(swi));
    }

    private void initBoard() {
        int num = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                state[i][j] = num;
                ++num;
            }
        }
        state[size-1][size-1] = -1;
        gapx = gapy = size-1;

        for (int i = 0; i < 100; ++i) {
            randMove();
        }
    }

    public boolean checkMove(int posx, int posy) {
        if (posx >= size || posy >= size) return false;
        return (Math.abs(posx - gapx) == 1 && Math.abs(posy - gapy) == 0) || (Math.abs(posx - gapx) == 0 && Math.abs(posy - gapy) == 1);
    }

    public int getSize() {return size;}

    public boolean solved() {
        int num = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (num == size*size - 1) return true;
                if (state[i][j] != num) return false;
                ++num;
            }
        }
        return true;
    }

    public int[][] getMoves() {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        if (gapx < size - 1) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(gapx + 1);
            row.add(gapy);
            moves.add(row);
        }
        if (gapx > 0) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(gapx -1);
            row.add(gapy);
            moves.add(row);
        }
        if (gapy < size - 1) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(gapx);
            row.add(gapy + 1);
            moves.add(row);
        }
        if (gapy > 0) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(gapx);
            row.add(gapy - 1);
            moves.add(row);
        }

        int[][] movesArray = new int[moves.size()][2];
        for (int i = 0; i < moves.size(); i++) {
            ArrayList<Integer> move = moves.get(i);
            movesArray[i][0] = move.get(0);
            movesArray[i][1] = move.get(1);
        }

        return movesArray;
    }

    public int[] getGapPos() {
        return new int[]{gapx, gapy};
    }

    public void printBoard(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
    }
}
