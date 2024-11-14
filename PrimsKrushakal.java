import java.util.*;

class UnionFind {
    private int[] parent, rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int u) {
        if (parent[u] != u) parent[u] = find(parent[u]);
        return parent[u];
    }

    public void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) parent[rootV] = rootU;
            else if (rank[rootU] < rank[rootV]) parent[rootU] = rootV;
            else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}

class Kruskal {
    public static int kruskalMST(int n, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2])); // Sort by weight
        UnionFind uf = new UnionFind(n);
        int mstCost = 0;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];
            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                mstCost += weight;
            }
        }
        return mstCost;
    }
}



import java.util.*;

class Prim {
    public static int primMST(List<List<int[]>> graph) {
        int mstCost = 0;
        boolean[] visited = new boolean[graph.size()];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.add(new int[]{0, 0}); // {cost, vertex}

        while (!minHeap.isEmpty()) {
            int[] edge = minHeap.poll();
            int cost = edge[0], u = edge[1];
            if (visited[u]) continue;
            mstCost += cost;
            visited[u] = true;
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                if (!visited[v]) {
                    minHeap.add(new int[]{weight, v});
                }
            }
        }
        return mstCost;
    }
}
