package basic;

/**
 * Problem: You are at (0, 0) in Manhattan - the city is structured in the form of a grid.
 * Your house is at (x, y). You are drunk. At every move, your x or y coordinate can increase by 1.
 * So only 2 moves are allowed / possible when you are at a given cell.
 * The probability of moving north (i.e y = y + 1) is p.
 * The probability of moving east (x = x + 1) is 1 - p.
 * Find the probability of reaching home
 *
 */

public class GridPath {
    public float calculate(int x, int y, float[][] arr, int m, int n, float p) {
        if (x < 0 || y < 0 || x > m || y > n) {
            return -1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = (1 - p) * arr[i - 1][j] + p * arr[i][j - 1];
            }
        }

        return arr[x][y];
    }

    private float[][] initialize(int m, int n, float p) {
        float[][] arr = new float[m][n];

        arr[0][0] = 1;

        for (int j = 1; j < n; j++) {
            arr[0][j] = arr[0][j - 1] * p;
        }

        for (int i = 1; i < m; i++) {
            arr[i][0] = arr[i - 1][0] * (1 - p);
        }
        return arr;
    }

    public static void main(String[] args) {
        int m = 100, n = 100;
        float p = 0.9f;
        GridPath gridPath = new GridPath();
        float[][] arr = gridPath.initialize(m, n, p);
        float result = gridPath.calculate(81, 81, arr, m, n, p);
        System.out.println(result);
    }
}
