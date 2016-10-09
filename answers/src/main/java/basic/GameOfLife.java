package basic;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.


 X O O X
 O X O O
 X O X X
 O X X X

 X = dead
 O = alive


- If the cell is alive: if it has 2 or 3 alive neighbors, it stays alive. Otherwise, it dies.
- If the cell is dead: if it has exactly 3 alive neighbors, it becomes alive. Otherwise, it stays dead.

nextState(state) -> state

board[y][x]
 */

class Solution {
    public static void main(String[] args) {
        int[][] board = new int[][] {{0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}};
        Solution solution = new Solution();
        int[][] boardResult = solution.playGameOfLife(board);

        for (int i = 0; i < boardResult.length; i++) {
            for (int j = 0; j < boardResult[0].length; j++) {
                System.out.print(boardResult[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * State - need 2 bits to represent state
     * [2nd bit, 1st bit] = [next state, current state]
     * 00 dead (next) - dead (current) -> 0
     * 01 dead (next) - live (current) -> 1
     * 10 live (next) - dead (current) -> 2
     * 11 live (next) - live (next)    -> 3
     * board[i][j] & 1 - Current State of a cell
     * board[i][j] >> 1
     */

    public void getNextState(int[][] board, int i, int j) {
        int currentState = board[i][j] & 1;
        int nextState = currentState >> 1;
        System.out.println(nextState);
    }

    public int[][] playGameOfLife(int[][] board) {

        if (board == null || board.length == 0) {
            return null;
        }

        int m = board.length;
        int n = m == 0 ? 0: board[0].length;
        int[][] boardResult = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = liveNeighbors(board, m, n, i, j);
                // alive stays alive if liveNeighbors is 2 or 3
                int currentCell = board[i][j];
                if (currentCell == 1 && liveNeighbors >= 2 && liveNeighbors <= 3) {
                    boardResult[i][j] = 1;
                }

                // change state from dead to alive for exactly 3 live neighbors
                if (currentCell == 0 && liveNeighbors == 3) {
                    boardResult[i][j] = 0;
                }
            }
        }
        return boardResult;
    }

    // m * n grid
    // X O X
    // O O O
    // 2 * 3

    // ---> x
    // A B C |
    // D E F |
    //       y
    // x = 1, y = 0 == B
    // m = num of rows
    //
    // board[x][y]
    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int liveNeighbors = 0;

        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                // get current state of board[x][y] and add to live neighbors if it is alive
                liveNeighbors += board[x][y] & 1;
            }
        }
        liveNeighbors -= board[i][j] & 1;
        return liveNeighbors;
    }
}

