
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSP {
    private int V, numOfColors;
    private int[] color;
    private int[][] graph;

    public void graphColor(int[][] g, int noc) {
        V = g.length;
        numOfColors = noc;
        color = new int[V];
        graph = g;

        try {
            solve(0);
            System.out.println("No solution");
        } catch (Exception e) {
            System.out.println("\nSolution exists ");
            display();
        }
    }

    public void solve(int v) throws Exception {
        /** base case − solution found **/
        if (v == V)
            throw new Exception("Solution found");
        /** try all colours **/
        for (int c = 1; c <= numOfColors; c++) {
            if (isPossible(v, c)) {
                /** assign and proceed with next vertex **/
                color[v] = c;
                solve(v + 1);

                color[v] = 0;
            }
        }
    }

    public boolean isPossible(int v, int c) {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    public void display() {

        String[] textColor = { "RED", "GREEN", "BLUE", "YELLOW", "ORANGE", "PINK", "BLACK", "BROWN", "WHITE", "PURPLE",
                "VIOLET" };
        System.out.print("\nColors : ");
        for (int i = 0; i < V; i++) {
            System.out.print(textColor[color[i]] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        int i, j, n, e;
        int graph[][];
        Scanner scan = new Scanner(System.in);
        System.out.println("Graph Coloring Algorithm Test\n");
        CSP gc = new CSP();
        File file = new File("C:\\n.txt");

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
                x++;
            }
        }
        System.out.print("\nEnter number of colors: ");
        int c = scan.nextInt();
        gc.graphColor(graph, c);
    }
}
