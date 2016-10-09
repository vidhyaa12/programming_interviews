package basic;

/**
 * Grid of 0s and 1s where 0s are obstacles. Given the grid and a point (x, y). Check if there is a path to (x, y)
 * Backtracking / DFS will help solve this problem
 * Note: This problem cannot be solved using dynamic programming with a 2D solution array.
 * To get to cell (0, 0), the valid moves are going left from (0, 1) and going up from (1, 0). We cannot initialize the number of ways to get to (0,0) to 1
 * since we do not know the number of ways to get to cells (0, 1) and (1, 0). Dynamic programming would be useful only when the allowed directions are down and right.
 * In that case, it would be possible to initialize dp[0][0] = 1.
 *
 * This class has a backtracking based solution to solve the obstacle grid problem
 */
public class ObstacleGridAllDirections {
    private final int M;
    private final int N;
    private final int[][] grid;

    public ObstacleGridAllDirections(int[][] grid) {
        this.grid = grid;
        if (grid == null) {
            M = 0;
            N = 0;
        } else {
            M = grid.length;
            N = grid.length == 0 ? 0 : grid[0].length;
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 1;
    }

    public boolean dfs(int i, int j, int destinationX, int destinationY, boolean[][] visited) {
        if (i == destinationX && j == destinationY) {
            visited[i][j] = true;
            return true;
        }

        if (isValid(i, j) && !visited[i][j]) {
            visited[i][j] = true;

            if (dfs(i + 1, j, destinationX, destinationY, visited)) {
                return true;
            }

            if (dfs(i - 1, j, destinationX, destinationY, visited)) {
                return true;
            }

            if (dfs(i, j - 1, destinationX, destinationY, visited)) {
                return true;
            }

            if (dfs(i, j + 1, destinationX, destinationY, visited)) {
                return true;
            }

            return false;
        }
        return false;
    }
}
