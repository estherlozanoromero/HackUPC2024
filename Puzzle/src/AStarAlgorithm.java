import java.util.*;

public class AStarAlgorithm {
    private Node start;
    private Node goal;
    private static class Node {
        Board board;
        int heuristicCost; // Coste heurístico (distancia estimada al nodo objetivo)
        int actualCost;

        Node(Board board, int heuristicCost, int actualCost) {
            this.board = board;
            this.heuristicCost = heuristicCost;
            this.actualCost = actualCost;
        }
    }

    public  AStarAlgorithm() {

    }

    // Función para encontrar el camino más corto usando el algoritmo A*
    public int aStar(Board boardIni) {
        Set<Node> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.actualCost + n.heuristicCost));

        int calculated_heuristic = calculate_heuristic(boardIni);
        start = new Node (boardIni, calculated_heuristic, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.board.solved()) {
                return current.actualCost;
            }

            visited.add(current);

            int[][] moves = current.board.getMoves();

            for (int i = 0; i < moves.length; ++i) {
                Board board_neighbour = new Board(current.board);
                int actual_dist = calculate_dist(moves[i][0], moves[i][1], board_neighbour.state[moves[i][0]][moves[i][1]], board_neighbour.getSize());
                int[] blanked_pos = board_neighbour.getGapPos();
                board_neighbour.move(moves[i][0], moves[i][2]);
                int neighbour_dist = calculate_dist(moves[i][0], moves[i][1], board_neighbour.state[blanked_pos[0]][blanked_pos[1]], board_neighbour.getSize());

                Node neighbor;
                if (neighbour_dist > actual_dist)
                     neighbor = new Node(board_neighbour, current.heuristicCost+1, current.actualCost+1);
                else
                    neighbor = new Node(board_neighbour, current.heuristicCost-1, current.actualCost+1);
                queue.add(neighbor);
            }
        }
        return -1;
    }

    private int calculate_heuristic(Board board) {
        int heuristic = 0;
        for (int i = 0; i < board.getSize(); ++i) {
            for(int j = 0; j < board.getSize(); ++j) {
                heuristic += calculate_dist(i, j, board.state[i][j], board.getSize());
            }
        }
        return heuristic;
    }

    private int calculate_dist(int x1, int y1, int id, int size) {
        int x2, y2;
        if(id != -1) {
            x2 = id / size;
            y2 = id % size;
        } else {
            x2 = size - 1;
            y2 = size - 1;
        }
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
