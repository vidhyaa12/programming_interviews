package uva;

import java.util.*;

/**
 * Given an M * N matrix of longegers, you are to write a program that computes a path of minimal weight.
 * A path starts anywhere in column 1 (the first column) and consists of a sequence of steps terminating in column n (the last column).
 * A step consists of traveling from column i to column i+1 in an adjacent (horizontal or diagonal) row.
 * The first and last rows (rows 1 and m) of a matrix are considered adjacent, i.e., the matrix ``wraps'' so that
 * it represents a horizontal cylinder.
 *
 */
class UnidirectionalTspUva116 {
    public static long computeMinWeightPath(int[][] arr, int M, int N) {
        long[][] best = new long[M][N];
        int[][] next = new int[M][N];
        // add code to initialize best

        for (int i = 0; i < M;i++)
            for(int j=0;j<N;j++)
                next[i][j] = -1;

        for (int i = 0; i < M; i++) {
            best[i][N-1] = arr[i][N-1];
        }

        /**
         * Once we compute best[0][0], best[1][0], ..., best[M][0]
         * We should solve column by column
         */
        for (int j = N-2; j >= 0; j--) {
            for (int i = 0; i < M; i++) {
                long currentBest = Math.min(best[(i - 1 + M) % M][j + 1],
                        Math.min(best[i][j + 1], best[(i + 1) % M][j + 1]));

                best[i][j] = currentBest + arr[i][j];

                if (currentBest == best[(i - 1 + M) % M][j + 1]) {
                    next[i][j] = (i - 1 + M) % M;
                }

                if (currentBest == best[i][j + 1] && (next[i][j] == -1 || next[i][j] > i) )  {
                    next[i][j] = i;
                }

                if (currentBest == best[(i + 1) % M][j + 1] && (next[i][j] == -1 || next[i][j] > (i + 1) % M)) {
                    next[i][j] = (i + 1) % M;
                }
            }
        }

        long minWeightPathResult = Long.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            if (best[i][0] < minWeightPathResult) {
                minWeightPathResult = best[i][0];
            }
        }

        // function that gets a List of rows which terminate in minWeightPathResult
        List<Integer> startRowIndices = getAllMinWeightPathRowStartIndices(M, N, minWeightPathResult, best);

        List<Integer> resultPathRowIndices = new ArrayList<Integer>();

//        for(int x=0;x<M;x++,System.out.println())
//            for(int y=0;y<N;y++)
//                System.out.print( "(" + best[x][y] + "," + next[x][y] + ") ");

        // Find the lexicographically first list from the list
//        Collections.reverse(startRowIndices);
//        for (Integer row : startRowIndices) {

            int index = startRowIndices.get(0);
            List<Integer> pathRowIndices = new ArrayList<Integer>();
            for(int currentColumn = 0 ; currentColumn < N; currentColumn++) {
                pathRowIndices.add(index + 1);
                index = next[index][currentColumn];
            }

            resultPathRowIndices = pathRowIndices;
//        }


        long ctr = 0;
        for (Integer idx : resultPathRowIndices) {
            if (ctr > 0) {
                System.out.print(" ");
            }
            System.out.print(idx);
            ctr++;
        }
        System.out.println();
        System.out.println(minWeightPathResult);
        return minWeightPathResult;
    }

    private static List<Integer> getAllMinWeightPathRowStartIndices(int M, int N, long minWeightPathResult, long[][] best) {
        List<Integer> rowIndices = new ArrayList<Integer>();
        for (int i = 0; i < M; i++) {
            if (best[i][0] == minWeightPathResult) {
                rowIndices.add(i);
            }
        }
        return rowIndices;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M, N;
        while (sc.hasNext()) {
            M = sc.nextInt();
            N = sc.nextInt();

            int[][] arr = new int[M][N];

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            computeMinWeightPath(arr, M, N);
        }
    }
}
