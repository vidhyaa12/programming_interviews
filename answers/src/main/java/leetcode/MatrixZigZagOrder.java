package leetcode;

public class MatrixZigZagOrder {
    public int[] printZMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return null;

        int m = matrix.length - 1, n = matrix[0].length - 1;
        int[] result = new int[(m + 1) * (n + 1)];
        int index = 0;
        for (int i = 0; i <= m + n; i++) {
            int upperBoundx = Math.min(i, m); // x <= m
            int lowerBoundx = Math.max(0, i - n); // lower bound i - x(y) <= n
            int upperBoundy = Math.min(i, n); // y <= n
            int lowerBoundy = Math.max(0, i - m); // i - y(x) <= m
            if (i % 2 == 0) {
                // column increment
                for (int y = lowerBoundy; y <= upperBoundy; y++) {
                    result[index] = matrix[i - y][y];
                    index++;
                }
            } else {
                // row increment
                for (int x = lowerBoundx; x <= upperBoundx; x++) {
                    result[index] = matrix[x][i - x];
                    index++;
                }
            }
        }

        return result;
    }
}
