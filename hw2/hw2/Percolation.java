package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int numberOpen;
    private boolean[] opened;
    // index N stores the top, N+1 store the bottom
    private WeightedQuickUnionUF uf;

    /** Convert (x, y) to an index of the grid. */
    private int convertXYToIndex(int x, int y) {
        return y + x * n;
    }

    /** Create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N)  {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.n = N;
        opened = new boolean[N * N + 2];
        for (int i = 0; i < N * N + 2; i += 1) {
            opened[i] = false;
        }
        uf = new WeightedQuickUnionUF(N * N + 1);
        numberOpen = 0;
    }

    /** Open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        /* special cases. */
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        opened[convertXYToIndex(row, col)] = true;
        numberOpen += 1;
        if (row == 0) {
            /* If is on the top */
            uf.union(convertXYToIndex(row, col), n*n);
        }
        /* connect up */
        if (row - 1 >= 0) {
            if (opened[convertXYToIndex(row - 1, col)]) {
                uf.union(convertXYToIndex(row - 1, col), convertXYToIndex(row, col));
            }
        }
        /* connect down */
        if (row + 1 < n) {
            if (opened[convertXYToIndex(row + 1, col)]) {
                uf.union(convertXYToIndex(row + 1, col), convertXYToIndex(row, col));
            }
        }
        /* connect left */
        if (col - 1 >= 0) {
            if (opened[convertXYToIndex(row, col - 1)]) {
                uf.union(convertXYToIndex(row, col - 1), convertXYToIndex(row, col));
            }
        }
        /* connect right */
        if (col + 1 < n) {
            if (opened[convertXYToIndex(row, col + 1)]) {
                uf.union(convertXYToIndex(row, col + 1), convertXYToIndex(row, col));
            }
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return opened[convertXYToIndex(row, col)];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return uf.connected(n * n, convertXYToIndex(row, col));
    }

    /** number of open sites. */
    public int numberOfOpenSites() {
        return numberOpen;
    }

    /** does the system percolate? */
    public boolean percolates() {
        for (int i = 0; i < n; i += 1) {
            if (uf.connected(n * n, convertXYToIndex(n - 1, i))) {
                return true;
            }
        }
        return false;
    }

    /** use for unit testing (not required) */
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 1);
        p.open(1, 1);
        p.open(2, 1);
        System.out.println(p.isOpen(0, 0));
        System.out.println(p.percolates());
    }
}
