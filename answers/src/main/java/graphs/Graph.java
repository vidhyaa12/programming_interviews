package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by vidhyaa on 21/11/17.
 */
public class Graph {
    private int numVertices;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        numVertices = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // prints BFS traversal from a given source s
    public void bfs(int source) {
        // initialize all vertices to not visited
        boolean[] visited = new boolean[numVertices];

        // create a queue for bfs
        Queue<Integer> queue = new LinkedList<Integer>();

        // mark the current node as visited and enqueue it
        visited[source] = true;
        queue.add(source);

        while (queue.size() != 0) {
            // dequeue vertex from queue and print it
            int curr = queue.poll();
            System.out.println(curr + " ");

            // get all adjacent vertices of dequeued vertex curr
            // if an adjacent vertex has not been visited, then mark it as visited
            // and enqueue it.
            Iterator<Integer> iter = adj[curr].listIterator();
            while (iter.hasNext()) {
                int next = iter.next();

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }

        }

    }

    public static void main(String[] args) {
        System.out.println("Check");
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        g.bfs(2);
    }
}
