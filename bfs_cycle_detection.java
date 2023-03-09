
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bfs_cycle_detection {

    public static int n, i, j, k, l = 0;
    public static int e;
    static int[][] graph;
    static int vis[], lev[], par[], cycle[];

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\n.txt");
        /**
         * Here is the file
         * 6
         * 0 1 1 1 0 0
         * 1 0 1 0 1 0
         * 1 1 0 0 0 0
         * 1 0 0 0 1 1
         * 0 1 0 1 0 0
         * 0 0 0 1 0 0
         **/
        Scanner scanner = new Scanner(file);
        List<Integer> input = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                input.add(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
        n = input.get(0);

        graph = new int[n][n];

        int x = 1;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                graph[i][j] = input.get(x);
                ;
                x++;
            }
        }
        System.out.println("Output:");
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println("BSF traversal sequence):");

        vis = new int[n];
        lev = new int[n];
        par = new int[n];
        cycle = new int[n];

        for (i = 0; i < n; i++) {
            vis[i] = 0;
            lev[i] = 999999;
            par[i] = -1;
        }
        int s = 0;
        vis[s] = 1;
        lev[s] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < n; v++) {
                if (graph[u][v] == 1 && vis[v] == 0) {
                    vis[v] = 1;
                    lev[v] = lev[u] + 1;
                    par[v] = u;
                    q.add(v);
                } else if (graph[u][v] == 1 && vis[v] == 1) {
                    cycle[l] = u;
                    l++;
                }
            }

            vis[u] = 2;
        }
        for (i = 0; i < n; i++) {
            System.out.println("Node = " + i + " Level = " + lev[i] + " Parent = " + par[i]);
        }
        if (l > 0) {
            System.out.print("There are " + l + " cycle detected at node:");
            for (k = 0; k < l; k++) {
                int h = cycle[k];
                System.out.print(h + " ");

            }
        }

    }
}
