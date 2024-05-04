import java.util.ArrayList;

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
    
    private void initBoard() {
        int num = -1;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                state[i][j] = num;
                ++num;
            }
        }

    }

    public boolean checkMove(int posx, int posy) {
        boolean possible = (Math.abs(posx - gapx) == 1 && Math.abs(posy - gapy) == 0) || (Math.abs(posx - gapx) == 0 && Math.abs(posy - gapy) == 1);
        if (!possible) System.out.println("Movement not possible");
        return possible;
    }

    public int getSize() {return size;}
}
