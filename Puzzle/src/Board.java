import java.util.ArrayList;

public class Board {
    public int[][] state;
    
    public Board (int num) {
        state = new int[num][num];
        random();
    }
    public void move(int p) {
        //TODO hacer move si hay hueco al lado
    }
    private void random() {
        //TODO inicializar random pero con conciencia
    }
}
