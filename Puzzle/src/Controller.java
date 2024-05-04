//import Board;

public class Controller {
    Board board;

    public Controller(int size){
        // board.setSize(); Hay que hacer esta funcion
        board = new Board(size);
    }

    public void printBoard(){
        int i = 0, j = 0;
        int size = 3;//board.getSize(); DEFINIR ESTA FUNCION
        //Borrar esto
               
        int[][] state = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        
        //Hasta aqui

        for (i = 0; i < size; i++){ // cambiar la size por board.getSize()
            for (j = 0; j < size; j++){

            System.out.print(state[i][j] + " "); // poner board.state

            }

            System.out.println();
        }

    }

}
