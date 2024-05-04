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
                    gapy = i;
                    gapx = j;
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
    private void initBoard() {
        int num = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                state[i][j] = num;
                ++num;
            }
        }
        state[size-1][size-1] = -1;

        Random rand = new Random();
        //for (int i = 0; i < 20; ++i) {
            int posx = rand.nextInt(size);
            int posy = rand.nextInt(size);
            if (posx+posy == (size-1)*2) --posx;
            swap(posx, posy);

            posx = rand.nextInt(size);
            posy = rand.nextInt(size);
            if (posx+posy == (size-1)*2) --posy;
            swap(posx, posy);
        //}
    }

    private void swap(int posx, int posy) {
        int aux = state[0][0];
        state[0][0] = state[posx][posy];
        state[posx][posy] = aux;
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
