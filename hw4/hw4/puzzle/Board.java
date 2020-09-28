package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int N;
    private int[][] tile;
    /** Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j. */
    public Board(int[][] tiles) {
        this.N = tiles[0].length;
        this.tile = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                this.tile[i][j] = tiles[i][j];
            }
        }
    }

    /** Returns value of tile at row i, column j (or 0 if blank). */
    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return this.tile[i][j];
    }

    /** Returns the board size N. */
    public int size() {
        return N;
    }

    /** Returns the neighbors of the current board.
     *  from http://joshh.ug/neighbors.html */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    /** Hamming estimate described below. */
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (i == N - 1 && j == N - 1) {
                    break;
                }
                int realJ = this.tile[i][j] % N - 1;
                if (realJ < 0) {
                    realJ += N;
                }
                int realI = (this.tile[i][j] - (realJ + 1)) / N;
                if (i != realI || j != realJ) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    /** Manhattan estimate described below.*/
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                int realI, realJ;
                if (this.tile[i][j] == 0) {
                    continue;
                } else {
                    realJ = this.tile[i][j] % N - 1;
                    if (realJ < 0) {
                        realJ += N;
                    }
                    realI = (this.tile[i][j] - (realJ + 1)) / N;
                }
                sum += (Math.abs(i - realI) + Math.abs(j - realJ));
            }
        }
        return sum;
    }

    /**  Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope. */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns true if this board's tile values are the same
     position as y's. */
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }else if (this.getClass() != y.getClass()) {
            return false;
        } else if (this == y) {
            return true;
        } else if (this.N != ((Board) y).N) {
            return false;
        } else {
            for (int i = 0; i < N; i += 1) {
                for (int j = 0; j < N; j += 1) {
                    if (this.tile[i][j] != ((Board) y).tile[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = size();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
