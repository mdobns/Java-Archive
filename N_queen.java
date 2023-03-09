
import java.util.Scanner;

/* Java program to solve N Queen Problem using backtracking */
public class N_queen {
    public static int s = 1;
    int N;

    N_queen(int a) {
        N = a;
    }

    void printSolution(int board[][]) {
        s++;
        for (int g = 0; g < N; g++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + board[g][j] + " ");
            }
            System.out.println();
        }
    }

    boolean isSafe(int grid[][], int row, int col) {
        int i, j;
        for (i = 0; i < col; i++) {
            if (grid[row][i] == 1) {
                return false;
            }
        }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (grid[i][j] == 1) {
                return false;
            }
        }
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (grid[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    boolean solveNQUtil(int grid[][], int col) {
        /* base case: If all queens are placed then return true */
        if (col == N) {
            System.out.println("Solution " + s + " found for " + N + " queens");
            printSolution(grid);
            return true;
        }
        for (int i = 0; i < N; i++) {
            if (isSafe(grid, i, col)) {
                grid[i][col] = 1;
                solveNQUtil(grid, col + 1);
                grid[i][col] = 0; // BACKTRACK
            }
        }
        return false;
    }

    boolean solveNQ() {
        int grid[][] = new int[N][N];
        solveNQUtil(grid, 0);
        return false;
    }

    public static void main(String args[]) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of queen to place − ");
        n = sc.nextInt();
        N_queen Queen = new N_queen(n);
        Queen.solveNQ();
        System.out.println("Total solution found " + (s - 1));
    }
}
