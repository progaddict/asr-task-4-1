package de.rwth.sse.asr.task41;

public class Task41 {

    private static final int MAX_NUMBER_OF_MATRICES = 1000;
    private static final long[][] result = new long[MAX_NUMBER_OF_MATRICES][MAX_NUMBER_OF_MATRICES];
    private static final boolean[][] isMemorized = new boolean[MAX_NUMBER_OF_MATRICES][MAX_NUMBER_OF_MATRICES];
    private static final int[][] decision = new int[MAX_NUMBER_OF_MATRICES][MAX_NUMBER_OF_MATRICES];
    private static final long[] rows = {10, 100, 1000, 10, 1};
    private static final long[] columns = {100, 1000, 10, 1, 10000000};

    public static void main(String[] args) {
        for (int i = 0; i < rows.length; i++) {
            result[i][i] = 0;
            isMemorized[i][i] = true;
        }
        System.out.println("cost: " + multiplicationCost(0, rows.length - 1));
        printMultiplicationSequence(0, rows.length - 1);
    }

    private static long multiplicationCost(int l, int r) {
        if (l >= r) {
            return 0;
        }
        if (isMemorized[l][r]) {
            return result[l][r];
        }
        result[l][r] = Long.MAX_VALUE;
        for (int m = l + 1; m < r; m++) {
            long cost = multiplicationCost(l, m) + multiplicationCost(m, r) + rows[l] * columns[m] * rows[r];
            if (cost < result[l][r]) {
                result[l][r] = cost;
                decision[l][r] = m;
            }
        }
        isMemorized[l][r] = true;
        return result[l][r];
    }

    private static void printMultiplicationSequence(int l, int r) {
        if (l >= r) {
            return;
        }
        if (r == l + 1) {
            System.out.print("(M" + l + " * M" + r + ")");
            return;
        }
        System.out.print("(");
        printMultiplicationSequence(l, decision[l][r]);
        System.out.print(" * ");
        printMultiplicationSequence(decision[l][r], r);
        System.out.print(")");
    }
}
