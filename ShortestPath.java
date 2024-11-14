import java.util.*;

class ShortestPath {
    static final int V = 9; // Number of vertices in the graph

    // Method to find the vertex with the minimum distance value that hasn't been visited
    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE; // Start with the highest possible value
        int minIndex = -1; // To keep track of the vertex with minimum distance

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] <= min) { // If the vertex hasn't been visited and has the smallest distance
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Method to print the distance and path of each vertex from the source
    void printSolution(int dist[], int predecessor[], int src) {
        System.out.println("Vertex\t Distance from Source\t Path");
        for (int i = 0; i < V; i++) {
            System.out.print(i + "\t\t " + dist[i] + "\t\t");
            printPath(i, predecessor); // Print path from source to vertex i
            System.out.println();
        }
    }

    // Recursive method to print path from source to the given vertex
    void printPath(int vertex, int predecessor[]) {
        if (vertex == -1) {
            return; // Base case: source has no predecessor
        }
        printPath(predecessor[vertex], predecessor);
        System.out.print(vertex + " ");
    }

    // Method to calculate the shortest path from the source to all other vertices using Dijkstra's algorithm
    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // Holds the shortest distance from the source to each vertex
        boolean visited[] = new boolean[V]; // Tracks visited vertices
        int predecessor[] = new int[V]; // Tracks the predecessor of each vertex for path reconstruction

        // Initialize distances as infinity (a large value), mark all vertices as unvisited, and initialize predecessors as -1
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
            predecessor[i] = -1; // -1 indicates no predecessor
        }

        dist[src] = 0; // Distance of source to itself is 0

        // Process each vertex
        for (int count = 0; count < V - 1; count++) {
            // Find the unvisited vertex with the smallest distance
            int u = minDistance(dist, visited);
            visited[u] = true; // Mark this vertex as visited

            // Update distances of neighboring vertices
            for (int v = 0; v < V; v++) {
                // Update dist[v] if:
                // - v is not visited
                // - there is an edge from u to v (graph[u][v] != 0)
                // - the distance through u is shorter than the current distance
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    predecessor[v] = u; // Update predecessor to track path
                }
            }
        }

        // Print the final distances and paths
        printSolution(dist, predecessor, src);
    }

    // Main method to test the code
    public static void main(String[] args) {
        int graph[][] = new int[][] {
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };

        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0); // Calculate shortest paths from vertex 0
    }
}
