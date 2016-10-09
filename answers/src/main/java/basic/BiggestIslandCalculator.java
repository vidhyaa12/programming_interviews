package basic;

public class BiggestIslandCalculator {
    public int getBiggestIslandSize(char[][] grid) {
        int maxSize = 0;
        if (grid == null) {
            return 0;
        }

        int count = 0;
        int M = grid.length;
        int N = M == 0 ? 0 : grid[0].length;
        boolean[][] visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    int islandSize = dfs(grid, i, j, visited);
                    maxSize = Math.max(maxSize, islandSize);
                }
            }
        }

        return maxSize;
    }

    public int dfs(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) {
            return 0;
        }

        if (grid[i][j] == '0' || visited[i][j] == true) { // water or visited
            return 0;
        }

        visited[i][j] = true;
        return  1 +  dfs(grid, i - 1, j, visited) + dfs(grid, i + 1, j, visited) + dfs(grid, i, j - 1, visited) + dfs(grid, i, j + 1, visited);
    }
}
