
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DFS {
    public static int n, i, j, k, u;
    public static int e;

    static int N;
    static char[] c = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };

    static int list2[][];
    static int list[][];
    static int[][] graph;
    static int[][] array;
    static int[] color = new int[N];
    static int[] prev = new int[N];
    static int f[] = new int[N];
    static int d[] = new int[N];
    static int time = 0;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("n.txt");
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

        list = new int[n][n];

        int x = 1;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                list[i][j] = input.get(x);
                ;
                x++;
            }
        }

        System.out.println("Output:");
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println("");
        }

        // converting to adjacent matrix
        int l = list[0].length;
        ArrayList<ArrayList<Integer>> adjListArray = new ArrayList<ArrayList<Integer>>(l);

        for (i = 0; i < l; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }

        for (i = 0; i < list[0].length; i++) {
            for (j = 0; j < list.length; j++) {
                if (list[i][j] == 1) {
                    adjListArray.get(i).add(j);
                }
            }
        }

        System.out.println("Adjacency List: ");

        for (int v = 0; v < adjListArray.size(); v++) {
            System.out.print(v);
            for (Integer u : adjListArray.get(v)) {
                System.out.print(" -> " + u);

            }
            System.out.println();
        }
        // CONVERTING ARRAYLIST TO 2D ARRAY
        list2 = new int[adjListArray.size()][];
        for (i = 0; i < list2.length; i++) {
            list2[i] = new int[adjListArray.get(i).size()];
        }
        for (i = 0; i < adjListArray.size(); i++) {
            for (j = 0; j < adjListArray.get(i).size(); j++) {
                list2[i][j] = adjListArray.get(i).get(j);
            }
        }

        System.out.println("2D array");
        System.out.println(Arrays.deepToString(list2));

        for (i = 0; i < N; i++) {
            color[i] = 0; // white;
            prev[i] = -1;
            d[i] = f[i] = 999999;
        }
        for (i = 0; i < N; i++) {
            if (color[i] == 0) {
                runDFS(i);
            }
        }
    }

    private static void runDFS(int p) {
        System.out.print(c[p] + " ");
        color[p] = 1; // gray
        time++;
        d[p] = time;
        for (int i = 0; i < list2[p].length; i++) {
            int o = list2[p][i];
            if (color[o] == 0) {
                prev[o] = p;
                runDFS(o);
            }
        }
        color[p] = 2; // black
        time++;
        f[p] = time;
    }
}
