import java.util.*;

public class AStarAlgorithm {
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

    public static int encode(int[][] matrix) {
        int encoded = 0;
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                encoded = encoded * (numRows * numCols - 1) + (matrix[i][j] + 1);
            }
        }
        return encoded;
    }

    // Función para encontrar el camino más corto usando el algoritmo A*
    public static int aStar(Board boardIni) {
        Node start;
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.actualCost + node.heuristicCost));

        int calculated_heuristic = calculate_heuristic(boardIni);
        start = new Node (boardIni, calculated_heuristic, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.add(encode(current.board.state));

            System.out.println("Checking board:");
            current.board.printBoard();
            System.out.println("actualCost: "+current.actualCost+" heuristicCost: "+current.heuristicCost);

            if (current.board.solved()) {
                return current.actualCost;
            }

            int[][] moves = current.board.getMoves();

            for (int i = 0; i < moves.length; ++i) {
                Board board_neighbour = new Board(current.board);
                int actual_dist = calculate_dist(moves[i][0], moves[i][1], board_neighbour.state[moves[i][0]][moves[i][1]], board_neighbour.getSize());
                int[] blanked_pos = board_neighbour.getGapPos();
                board_neighbour.move(moves[i][0], moves[i][1]);
                int neighbour_dist = calculate_dist(blanked_pos[0], blanked_pos[1], board_neighbour.state[blanked_pos[0]][blanked_pos[1]], board_neighbour.getSize());

                Node neighbor;
                if (neighbour_dist > actual_dist)
                     neighbor = new Node(board_neighbour, current.heuristicCost+1, current.actualCost+1);
                else
                    neighbor = new Node(board_neighbour, current.heuristicCost-1, current.actualCost+1);

                if (!visited.contains(neighbor.board.state))
                    queue.add(neighbor);
            }
        }
        return -1;
    }

    private static int calculate_heuristic(Board board) {
        int heuristic = 0;
        for (int i = 0; i < board.getSize(); ++i) {
            for(int j = 0; j < board.getSize(); ++j) {
                heuristic += calculate_dist(i, j, board.state[i][j], board.getSize());
            }
        }
        return heuristic;
    }

    private static int calculate_dist(int x1, int y1, int id, int size) {
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
