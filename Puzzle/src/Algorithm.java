import java.util.*;

public class Algorithm {

    // Clase para representar un nodo en el grafo
    static class Node {
        Board board;
        double heuristicCost; // Coste heurístico (distancia estimada al nodo objetivo)
        double actualCost;
        int depth; // Coste real desde el nodo inicial
        List<Edge> neighbors; // Lista de aristas salientes desde este nodo

        Node(Board board, double heuristicCost, int depth) {
            this.board = board;
            this.heuristicCost = heuristicCost;
            this.depth = depth;
            this.neighbors = new ArrayList<>();
        }
    }

    // Clase para representar una arista en el grafo
    static class Edge {
        Node target;
        double cost;

        Edge(Node target, double cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    // Función para encontrar el camino más corto usando el algoritmo A*
    public static List<Node> aStar(Node start, Node goal) {
        Set<Node> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.actualCost + n.heuristicCost));
        Map<Node, Node> cameFrom = new HashMap<>();

        start.actualCost = 0.0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current == goal) {
                return reconstructPath(cameFrom, goal);
            }

            visited.add(current);

            for (Edge neighborEdge : current.neighbors) {
                Node neighbor = neighborEdge.target;
                double newCost = current.actualCost + neighborEdge.cost;

                if (!visited.contains(neighbor) && newCost < neighbor.actualCost) {
                    neighbor.actualCost = newCost;
                    cameFrom.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return null; // No se encontró un camino
    }

    // Función para reconstruir el camino desde el nodo objetivo hasta el nodo inicial
    private static List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
        List<Node> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }
        return path;
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        // Crear nodos
        Node nodeA = new Node(0, 5);
        Node nodeB = new Node(1, 3);
        Node nodeC = new Node(2, 2);
        Node nodeD = new Node(3, 1);
        Node nodeE = new Node(4, 0);

        // Definir aristas
        nodeA.neighbors.add(new Edge(nodeB, 2));
        nodeA.neighbors.add(new Edge(nodeC, 3));
        nodeB.neighbors.add(new Edge(nodeD, 4));
        nodeC.neighbors.add(new Edge(nodeD, 1));
        nodeC.neighbors.add(new Edge(nodeE, 7));
        nodeD.neighbors.add(new Edge(nodeE, 5));

        // Encontrar el camino más corto desde A hasta E
        List<Node> shortestPath = aStar(nodeA, nodeE);

        // Imprimir el camino más corto
        if (shortestPath != null) {
            System.out.println("Camino más corto:");
            for (Node node : shortestPath) {
                System.out.println("Nodo " + node.id);
            }
        } else {
            System.out.println("No hay camino desde el nodo inicial al nodo objetivo.");
        }
    }
}
