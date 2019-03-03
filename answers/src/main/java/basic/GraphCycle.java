package basic;

class Graph {

    private int[][] adjList; // (adjacency list)

    public int[] getAdjacentNodes(int v) {
        return adjList[v];
    }

    // number of vertices in a graph
    public int vSize() {
        return adjList.length;
    }

}

// Java code to detect cycles in an undirected graph:
class DFSCycle {

    private boolean visited[];
    private int s;
    private Graph g;

    // s - starting node
    public DFSCycle(Graph g, int s) {
        this.g = g;
        this.s = s;
        visited = new boolean[g.vSize()];
        findCycle(g, s, s);
    }

    public boolean findCycle(Graph g, int node, int parent) {

        visited[node] = true;

        for (int neighbor : g.getAdjacentNodes(node)) { // parent is also an adjacent node of node.
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                if (findCycle(g, neighbor, node)) {
                    return false;
                }
            } else if (neighbor != parent) { // the only valid visited neighbor is the parent node
                return true;
            }
        }
        return false;
    }
}
