
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class IDDFS_Stack {
    private Stack<Integer> stack;
    private int numberOfNodes;

    private int depth;
    private int maxDepth;
    private boolean goalFound = false;

    public static void main(String... arg) throws FileNotFoundException {
        int i, j, n, destination, source;
        int adjacency_matrix[][];
        // readig from file
        Scanner sc = new Scanner(System.in);
        File file = new File("n.txt");
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
        adjacency_matrix = new int[n + 1][n + 1];
        int x = 1;
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= n; j++) {
                adjacency_matrix[i][j] = input.get(x);
                x++;
            }
        }
        System.out.println("Enter the source for the graph");
        source = sc.nextInt();
        System.out.println("Enter the destination for the graph");
        destination = sc.nextInt();
        IDDFS_Stack iterativeDeepening = new IDDFS_Stack();
        iterativeDeepening.iterativeDeeping(adjacency_matrix, destination, source);
        sc.close();
    }

    public IDDFS_Stack() {
        stack = new Stack<Integer>();
    }

    public void iterativeDeeping(int adjacencyMatrix[][], int destination, int source) {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        while (!goalFound) {
            depthLimitedSearch(adjacencyMatrix, source, destination);
            maxDepth++;
        }
        System.out.println("\nGoal Found at depth " + depth);
    }

    private void depthLimitedSearch(int adjacencyMatrix[][], int source, int goal) {
        int element, destination = 1;
        int[] visited = new int[numberOfNodes + 1];
        stack.push(source);
        depth = 0;
        System.out.println("\nAt Depth " + maxDepth);
        System.out.print(source + "\t");
        while (!stack.isEmpty()) {
            element = stack.peek();
            while (destination <= numberOfNodes) {
                if (depth < maxDepth) {
                    if (adjacencyMatrix[element][destination] == 1) {
                        stack.push(destination);
                        visited[destination] = 1;
                        System.out.print(destination + "\t");
                        depth++;
                        if (goal == destination) {
                            goalFound = true;
                            return;
                        }
                        element = destination;
                        destination = 1;
                        continue;
                    }
                } else {
                    break;
                }
                destination++;
            }
            destination = stack.pop() + 1;
            depth--;
        }
    }
}